package cn.zhanyiping.client.business.mbean;

import cn.zhanyiping.client.domain.dto.ThreadPoolInfoDTO;

import java.util.List;

public interface ThreadPoolManageServiceMBean {

    /**
     * 修改线程池配置
     * @param dto
     */
    void updateThreadPoolConfig(ThreadPoolInfoDTO dto);

    /**
     * 获取当前线程池的配置信息
     */
    List<ThreadPoolInfoDTO> showThreadPoolConfig();

}
