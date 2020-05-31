package cn.zhanyiping.service;

import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

public class JmxMBeanFactory {

    /**
     * 获取MBean对象
     * @param ipAndPort
     * @param jmxName
     * @return
     * @throws Exception
     */
    public static  <T> T getThreadMbean(String ipAndPort, String jmxName , Class<T> clazz) throws Exception {
        return MBeanServerInvocationHandler.newProxyInstance(JmxConnectionService.getConnection(ipAndPort),
                new ObjectName(jmxName), clazz, false);
    }

}
