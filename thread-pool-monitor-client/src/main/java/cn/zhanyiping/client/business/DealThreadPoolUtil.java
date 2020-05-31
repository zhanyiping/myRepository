package cn.zhanyiping.client.business;


import cn.zhanyiping.client.domain.dto.ThreadPoolInfoDTO;
import cn.zhanyiping.client.domain.enums.RejectedHandlerTypeEnum;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DealThreadPoolUtil {

    /**
     * 设置线程池，修改线程池的核心线程数以及最大线程数等信息
     */
    public static void modifyPoolConfig(ThreadPoolInfoDTO dto , ThreadPoolExecutor executor) {
        if (dto.getCorePoolSize() != 0 &&
                executor.getCorePoolSize() != dto.getCorePoolSize()){
            executor.setCorePoolSize(dto.getCorePoolSize());
        }
        if (dto.getMaximumPoolSize() != 0 &&
                executor.getMaximumPoolSize() != dto.getMaximumPoolSize()){
            executor.setMaximumPoolSize(dto.getMaximumPoolSize());
        }
        if (dto.getKeepAliveTime() != 0 &&
                executor.getKeepAliveTime(TimeUnit.SECONDS) != dto.getKeepAliveTime()){
            executor.setKeepAliveTime(dto.getKeepAliveTime() , TimeUnit.SECONDS);
        }
        if (Objects.nonNull(dto.getRejectedHandlerType()) &&
                !dto.getRejectedHandlerType().equals(RejectedHandlerTypeEnum.getRejectedHandlerTypeId(executor.getRejectedExecutionHandler()))){
            executor.setRejectedExecutionHandler(RejectedHandlerTypeEnum.getRejectedExecutionHandler(dto.getRejectedHandlerType()));
        }
    }

    /**
     * 获取当前线程池的信息
     * @return
     */
    public static ThreadPoolInfoDTO getPoolInfo(ThreadPoolExecutor executor, String threadPoolName){
        ThreadPoolInfoDTO info = new ThreadPoolInfoDTO();
        info.setThreadPoolName(threadPoolName);
        info.setActiveThreadCount(executor.getActiveCount());
        info.setCompletedTaskCount(executor.getCompletedTaskCount());
        info.setCorePoolSize(executor.getCorePoolSize());
        info.setCurrentPoolThreadCount(executor.getPoolSize());
        info.setMaximumPoolSize(executor.getMaximumPoolSize());
        info.setQueueTaskCount(executor.getQueue().size());
        info.setTerminatedFlag(executor.isTerminated());
        info.setTotalTaskCount(executor.getTaskCount());
        info.setRemainingCapacity(executor.getQueue().remainingCapacity());
        info.setCapacity(info.getQueueTaskCount() + info.getRemainingCapacity());
        info.setKeepAliveTime((int)executor.getKeepAliveTime(TimeUnit.SECONDS));
        info.setRejectedHandlerType(RejectedHandlerTypeEnum.getRejectedHandlerTypeId(executor.getRejectedExecutionHandler()));
        return info;
    }

}
