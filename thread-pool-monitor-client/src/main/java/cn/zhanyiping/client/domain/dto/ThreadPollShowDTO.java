package cn.zhanyiping.client.domain.dto;

import java.util.List;

public class ThreadPollShowDTO {

    /**
     * 线程池信息列表
     */
    private List<ThreadPoolInfoDTO> dtoList;
    /**
     * 线程池策略列表
     */
    private List<RejectedHandlerDTO> rejectedHandlerList;

    public List<ThreadPoolInfoDTO> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<ThreadPoolInfoDTO> dtoList) {
        this.dtoList = dtoList;
    }

    public List<RejectedHandlerDTO> getRejectedHandlerList() {
        return rejectedHandlerList;
    }

    public void setRejectedHandlerList(List<RejectedHandlerDTO> rejectedHandlerList) {
        this.rejectedHandlerList = rejectedHandlerList;
    }
}
