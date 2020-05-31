package cn.zhanyiping.domain.exception;

/**
 * service层统一抛出的异常，个性化扩展在此类中进行
 *
 */
public class ServiceBusinessException extends RuntimeException {

    private static String serviceExceptionDesc = "Service层业务异常：";

    /**
     * 有参构造函数
     *
     * @param message 异常描述
     */
    public ServiceBusinessException(String message) {
        super(serviceExceptionDesc + message);
    }

    /**
     * 有参构造函数
     *
     * @param message 异常描述
     * @param cause   可抛出的异常
     */
    public ServiceBusinessException(String message, Throwable cause) {
        super(serviceExceptionDesc+ message, cause);
    }
}
