package cn.zhanyiping.client.domain.dto;

import java.io.Serializable;

public class ThreadPoolInfoDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 线程池名称
     */
    private String threadPoolName;
    /**
     * 当前线程池的线程数
     */
    private Integer currentPoolThreadCount;
    /**
     * 核心线程数
     */
    private Integer corePoolSize;
    /**
     * 最大线程数
     */
    private Integer maximumPoolSize;
    /**
     * 活动线程数
     */
    private Integer activeThreadCount;
    /**
     * 执行完的任务数量
     */
    private Long completedTaskCount;
    /**
     * 任务总数
     */
    private Long totalTaskCount;
    /**
     * 是否停止
     */
    private Boolean terminatedFlag;
    /**
     * 队列中的任务数量
     */
    private Integer queueTaskCount;
    /**
     * 队列容量
     */
    private Integer capacity;
    /**
     * 当前队列的剩余容量
     */
    private Integer remainingCapacity;
    /**
     * 连接维持的时间 单位秒
     */
    private Integer keepAliveTime;
    /**
     * 拒绝策略
     */
    private Integer rejectedHandlerType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getThreadPoolName() {
        return threadPoolName;
    }

    public void setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
    }

    public Integer getCurrentPoolThreadCount() {
        return currentPoolThreadCount;
    }

    public void setCurrentPoolThreadCount(Integer currentPoolThreadCount) {
        this.currentPoolThreadCount = currentPoolThreadCount;
    }

    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Integer getActiveThreadCount() {
        return activeThreadCount;
    }

    public void setActiveThreadCount(Integer activeThreadCount) {
        this.activeThreadCount = activeThreadCount;
    }

    public Long getCompletedTaskCount() {
        return completedTaskCount;
    }

    public void setCompletedTaskCount(Long completedTaskCount) {
        this.completedTaskCount = completedTaskCount;
    }

    public Long getTotalTaskCount() {
        return totalTaskCount;
    }

    public void setTotalTaskCount(Long totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }

    public Boolean getTerminatedFlag() {
        return terminatedFlag;
    }

    public void setTerminatedFlag(Boolean terminatedFlag) {
        this.terminatedFlag = terminatedFlag;
    }

    public Integer getQueueTaskCount() {
        return queueTaskCount;
    }

    public void setQueueTaskCount(Integer queueTaskCount) {
        this.queueTaskCount = queueTaskCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRemainingCapacity() {
        return remainingCapacity;
    }

    public void setRemainingCapacity(Integer remainingCapacity) {
        this.remainingCapacity = remainingCapacity;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getRejectedHandlerType() {
        return rejectedHandlerType;
    }

    public void setRejectedHandlerType(Integer rejectedHandlerType) {
        this.rejectedHandlerType = rejectedHandlerType;
    }
}
