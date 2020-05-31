package cn.zhanyiping.service;

import cn.zhanyiping.domain.exception.CustomExceptionFactory;
import cn.zhanyiping.domain.wrapper.JmxConnectionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class JmxConnectionService {

    /**
     * 缓存连接信息的map
     */
    private static final ConcurrentHashMap<String , JmxConnectionWrapper> connectionMap = new ConcurrentHashMap<String , JmxConnectionWrapper>();

    /**
     * 清除jmx连接的线程休眠时间五分钟
     */
    private static final long CLEAR_THREAD_SLEEP_TIME = 5 * 60 *1000;
    /**
     * jmx连接持续时间为五分钟
     */
    private static final int CONNECTION_KEEP_TIME = 5;
    /**
     * jmx被使用时连接的时间加一分钟
     */
    private static final int CONNECTION_ADD_TIME = 1;

    /**
     * 获取连接
     *
     * @throws IOException
     */
    public static MBeanServerConnection getConnection(String ipAndPort) throws Exception {
        JmxConnectionWrapper wrapper = getConnectionWrapper(ipAndPort);
        //每次获取连接后，如果连接快被回收就加一分钟，防止回收
        if (wrapper.getConnectionInitDate().plusMinutes(CONNECTION_KEEP_TIME).isBefore(LocalDateTime.now())) {
            wrapper.setConnectionInitDate(wrapper.getConnectionInitDate().plusMinutes(CONNECTION_ADD_TIME));
        }
        return wrapper.getJmxConnector().getMBeanServerConnection();
    }

    /**
     * 从缓存中获取wrapper对象，没有就重新连接jmx服务端
     * @param ipAndPort
     * @return
     * @throws IOException
     */
    private static JmxConnectionWrapper getConnectionWrapper(String ipAndPort) throws IOException {
        if (StringUtils.isBlank(ipAndPort)) {
            throw CustomExceptionFactory.service("IP和端口号必须存在！");
        }
        String jmxUrl = "service:jmx:rmi:///jndi/rmi://"+ipAndPort+"/jmxrmi";
        log.info("连接JMX开始：jmxUrl=" +jmxUrl);
        JmxConnectionWrapper wrapper = connectionMap.get(jmxUrl);
        if (null != wrapper) {
            log.info("连接JMX，连接已经被缓存，直接返回");
            return wrapper;
        } else {
            log.info("连接JMX，缓存中没有连接，重新连接");
            return initJmxConnection(jmxUrl);
        }
    }

    /**
     * 连接jmx服务端，构造wrapper对象
     * @param jmxUrl
     * @return
     * @throws IOException
     */
    private static JmxConnectionWrapper initJmxConnection(String jmxUrl) throws IOException{
        JmxConnectionWrapper wrapper = connectionMap.get(jmxUrl);
        if (null != wrapper){
            try {
                wrapper.getJmxConnector().close();
                connectionMap.remove(jmxUrl);
            } catch (Throwable t){
                log.error("关闭JMX连接异常！" , t);
            }
        }
        JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL(jmxUrl), null);
        if (null == connector) {
            throw CustomExceptionFactory.service("连接jmx服务器异常，请重新尝试！");
        }
        JmxConnectionWrapper connectionWrapper = new JmxConnectionWrapper();
        connectionWrapper.setJmxConnector(connector);
        connectionWrapper.setConnectionInitDate(LocalDateTime.now());
        connectionMap.put(jmxUrl , connectionWrapper);
        log.info("连接JMX，初始化连接，放入缓存后，直接返回");
        return connectionMap.get(jmxUrl);
    }

    /**
     * 获取连接初始化时的时间
     * @param ipAndPort
     * @return
     * @throws IOException
     */
    public static String getConnectionInitDate(String ipAndPort) throws IOException {
        return getConnectionWrapper(ipAndPort)
                .getConnectionInitDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 清除并关闭超时的连接
     */
    @SuppressWarnings("all")
    public static void clearConnection() {
        for (; ;) {
            log.info("清除线程正在执行");
            //每次执行结束后都休眠五分钟
            try {
                Thread.sleep(CLEAR_THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
            }
            if (CollectionUtils.isEmpty(connectionMap)) {
                continue;
            }
            for (Map.Entry entry : connectionMap.entrySet()) {
                JmxConnectionWrapper wrapper = (JmxConnectionWrapper) entry.getValue();
                if (null == wrapper || null == wrapper.getConnectionInitDate()) {
                    continue;
                }
                //超过五分钟直接清除并关闭连接
                if (wrapper.getConnectionInitDate().plusMinutes(CONNECTION_KEEP_TIME).isBefore(LocalDateTime.now())) {
                    log.info("满足清除条件，开始清除并关闭连接，当前连接为："+entry.getKey());
                    connectionMap.remove(entry.getKey());
                    try {
                        wrapper.getJmxConnector().close();
                    } catch (Throwable t){
                        log.error("关闭JMX连接异常！" , t);
                    }
                } else {
                    log.info("未达到清除条件");
                }
            }
        }
    }

    public static void main(String[] args) {
        JmxConnectionWrapper wrapper = new JmxConnectionWrapper();
        wrapper.setConnectionInitDate(LocalDateTime.now());
        System.out.println(wrapper.getConnectionInitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1567440000076L)));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.now().plusMinutes(1).isBefore(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().minusMinutes(3).plusMinutes(1).isBefore(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().isBefore(LocalDateTime.now().plusMinutes(1)));
        System.out.println(LocalDateTime.now().plusMinutes(5).isAfter(LocalDateTime.now()));
    }
}
