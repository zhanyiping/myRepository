package cn.zhanyiping.client.domain.enums;

import cn.zhanyiping.client.domain.exception.BusinessException;

import java.util.*;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝策略枚举类型
 */
public enum RejectedHandlerTypeEnum {

    CUSTOM_HANDLER(0  , null , "自定义拒绝策略"),
    ABORT_POLICY_HANDLER(1 , new ThreadPoolExecutor.AbortPolicy(),"丢弃任务抛出异常"),
    DISCARD_POLICY_HANDLER(2 , new ThreadPoolExecutor.DiscardPolicy() ,"丢弃任务不抛出异常"),
    DISCARD_OLDEST_POLICY_HANDLER(3 , new ThreadPoolExecutor.DiscardOldestPolicy()  ,"丢弃最先入队的任务"),
    CALLER_RUNS_POLICY_HANDLER(4 , new ThreadPoolExecutor.CallerRunsPolicy() ,"由调用线程处理该任务"),
    ;

    private Integer rejectedHandlerTypeId;

    private RejectedExecutionHandler handler;

    private String rejectedHandlerTypeName;

    RejectedHandlerTypeEnum(Integer rejectedHandlerTypeId, RejectedExecutionHandler handler, String rejectedHandlerTypeName) {
        this.rejectedHandlerTypeId = rejectedHandlerTypeId;
        this.handler = handler;
        this.rejectedHandlerTypeName = rejectedHandlerTypeName;
    }

    private static final Map<Integer, RejectedHandlerTypeEnum> map = new HashMap<>();

    static {
        for (RejectedHandlerTypeEnum rejectedHandlerTypeEnum : RejectedHandlerTypeEnum.values()) {
            map.put(rejectedHandlerTypeEnum.getRejectedHandlerTypeId(), rejectedHandlerTypeEnum);
        }
    }

    public static RejectedExecutionHandler getRejectedExecutionHandler(Integer rejectedHandlerTypeId) {
        RejectedHandlerTypeEnum rejectedEnum = map.get(rejectedHandlerTypeId);
        if (Objects.nonNull(rejectedEnum) && Objects.nonNull(rejectedEnum.getHandler()) ) {
            return rejectedEnum.getHandler();
        }
        throw new BusinessException("Only support setting the rejection policy supported by JDK");
    }

    public static Integer getRejectedHandlerTypeId(RejectedExecutionHandler handler) {
        if (handler instanceof ThreadPoolExecutor.CallerRunsPolicy) {
            return CALLER_RUNS_POLICY_HANDLER.getRejectedHandlerTypeId();
        }
        if (handler instanceof ThreadPoolExecutor.DiscardOldestPolicy) {
            return DISCARD_OLDEST_POLICY_HANDLER.getRejectedHandlerTypeId();
        }
        if (handler instanceof ThreadPoolExecutor.AbortPolicy) {
            return ABORT_POLICY_HANDLER.getRejectedHandlerTypeId();
        }
        if (handler instanceof ThreadPoolExecutor.DiscardPolicy) {
            return DISCARD_POLICY_HANDLER.getRejectedHandlerTypeId();
        }
        return CUSTOM_HANDLER.getRejectedHandlerTypeId();
    }


    public Integer getRejectedHandlerTypeId() {
        return rejectedHandlerTypeId;
    }

    public String getRejectedHandlerTypeName() {
        return rejectedHandlerTypeName;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }
}
