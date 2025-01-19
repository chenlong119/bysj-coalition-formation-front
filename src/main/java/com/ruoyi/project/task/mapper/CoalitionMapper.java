package com.ruoyi.project.task.mapper;

import com.ruoyi.project.task.domain.Coalition;

import java.util.List;

public interface CoalitionMapper {
    /**
     * 新增联盟
     * 
     * @param coalition 联盟信息
     * @return 结果
     */
    int insertCoalition(Coalition coalition);

    int insertCoalitionCompany(List<Long> coalitionCompany, Long coalitionId);
} 