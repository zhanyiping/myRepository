package cn.zhanyiping.domain.wrapper;

import lombok.Data;

import javax.management.remote.JMXConnector;
import java.time.LocalDateTime;

/**
 * jmx连接和连接时间的时间封装类
 * create by zhanyiping
 */
@Data
public class JmxConnectionWrapper {

    /**
     * jmx的连接对象
     */
    private JMXConnector jmxConnector;
    /**
     * 连接初始化时的时间
     */
    private LocalDateTime connectionInitDate;
}
