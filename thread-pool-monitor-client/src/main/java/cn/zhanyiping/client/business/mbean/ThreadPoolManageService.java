package cn.zhanyiping.client.business.mbean;

import cn.zhanyiping.client.business.DealThreadPoolUtil;
import cn.zhanyiping.client.domain.dto.ThreadPoolInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolManageService implements ThreadPoolManageServiceMBean {

    private static final String MBEAN_NAME = "com.jd.o2o.thread.pool.monitor:name=ThreadPoolManageService";

    private final static Logger log = LoggerFactory.getLogger(ThreadPoolManageService.class);

    /**
     * 当前的单利对象
     */
    private static final ThreadPoolManageService INSTANCE = new ThreadPoolManageService();


    /**
     * 获取当前类的单利对象
     */
    public static ThreadPoolManageService getInstance(){
        return INSTANCE;
    }

    private final static Map<String , ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();

    public synchronized void setExecutor(ThreadPoolExecutor executor , String threadPoolName) {
        registerMBean();
        executorMap.put(threadPoolName , executor);
    }

    private static void registerMBean() {
        log.debug("registerMBean() init");
        if (executorMap.size() == 0) {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            try {
                log.debug("registerMBean() start");
                ObjectName objectName = new ObjectName(MBEAN_NAME);
                if (!mbeanServer.isRegistered(objectName)) {
                    mbeanServer.registerMBean(INSTANCE, objectName);
                    log.debug("registerMBean() end");
                }
            } catch (JMException ex) {
                log.error("register mbean error", ex);
            }
        }
    }

    private static void unregisterMBean() {
        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mbeanServer.unregisterMBean(new ObjectName(MBEAN_NAME));
        } catch (JMException ex) {
            log.error("unregister mbean error", ex);
        }
    }

    /**
     * 设置线程池，根据标识看是选择重置还是动态修改
     */
    @Override
    public void updateThreadPoolConfig(ThreadPoolInfoDTO dto) {
        try {
            DealThreadPoolUtil.modifyPoolConfig(dto , executorMap.get(dto.getThreadPoolName()));
        } catch (Throwable t){
            log.error("The connection pool setting is abnormal, the data is incorrect, please check" , t);
        }
    }

    /**
     * 获取当前线程池的信息
     * @return
     */
    @Override
    public List<ThreadPoolInfoDTO> showThreadPoolConfig(){
        List<ThreadPoolInfoDTO> dtoList = new ArrayList<>(executorMap.size());
        try {
            for (Map.Entry<String , ThreadPoolExecutor> entry : executorMap.entrySet()) {
                dtoList.add(DealThreadPoolUtil.getPoolInfo(entry.getValue(), entry.getKey()));
            }
        } catch(Throwable t){
            log.error("Display thread pool information is abnormal" , t);
        }
        return dtoList;
    }
}
