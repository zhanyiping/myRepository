package cn.zhanyiping.domain.exception;

/**
 * Rpc层统一抛出的异常，个性化扩展在此类中进行
 */
public class DaoBusinessException extends RuntimeException {

    private static String rpcExceptionDesc = "RPC层业务异常：";

    /**
     * 有参构造函数
     *
     * @param message 异常描述
     */
    public DaoBusinessException(String message) {
        super(rpcExceptionDesc + message);
    }

    /**
     * 有参构造函数
     *
     * @param message 异常描述
     * @param cause   可抛出的异常
     */
    public DaoBusinessException(String message, Throwable cause) {
        super(rpcExceptionDesc + message, cause);
    }
}
