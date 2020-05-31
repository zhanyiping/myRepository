package cn.zhanyiping.client.domain.dto;

public class RejectedHandlerDTO {

    /**
     * 拒绝策略ID
     */
    private Integer rejectedHandlerId;
    /**
     * 拒绝策略名称
     */
    private String rejectedHandlerName;


    public Integer getRejectedHandlerId() {
        return rejectedHandlerId;
    }

    public void setRejectedHandlerId(Integer rejectedHandlerId) {
        this.rejectedHandlerId = rejectedHandlerId;
    }

    public String getRejectedHandlerName() {
        return rejectedHandlerName;
    }

    public void setRejectedHandlerName(String rejectedHandlerName) {
        this.rejectedHandlerName = rejectedHandlerName;
    }
}
