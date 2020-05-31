package cn.zhanyiping.domain.exception;


import com.alibaba.fastjson.JSON;

/**
 * 自定义异常工厂
 */
public class CustomExceptionFactory {

    //===========================================dao层业务异常======================================================
    /**
     * 创建带有描述的dao层业务异常
     *
     * @param message 异常描述
     * @return dao层业务异常
     */
    public static DaoBusinessException dao(String message) {
        return new DaoBusinessException(message);
    }

    /**
     * 创建带有描述的dao层业务异常
     *
     * @param message 异常描述
     * @param object  数据对象
     * @return dao层业务异常
     */
    public static DaoBusinessException dao(String message, Object object) {
        return new DaoBusinessException(message + ", result:【 " + JSON.toJSONString(object)+" 】");
    }

    /**
     * 包装异常，创建带有描述的dao层业务异常
     *
     * @param message 异常描述
     * @param cause   需要包装的异常
     * @return dao层业务异常
     */
    public static DaoBusinessException dao(String message, Throwable cause) {
        return new DaoBusinessException(message, cause);
    }



    //===========================================service层业务异常======================================================
    /**
     * 创建带有描述的service层业务异常
     *
     * @param message 异常描述
     * @return service层业务异常
     */
    public static ServiceBusinessException service(String message) {
        return new ServiceBusinessException(message);
    }

    /**
     * 创建带有描述的service层业务异常
     *
     * @param message 异常描述
     * @param object  数据对象
     * @return service层业务异常
     */
    public static ServiceBusinessException service(String message, Object object) {
        return new ServiceBusinessException(message + ", result:【 " + JSON.toJSONString(object)+" 】");
    }

    /**
     * 包装异常，创建带有描述的service层业务异常
     *
     * @param message 异常描述
     * @param cause   需要包装的异常
     * @return service层业务异常
     */
    public static ServiceBusinessException service(String message, Throwable cause) {
        return new ServiceBusinessException(message, cause);
    }
}
