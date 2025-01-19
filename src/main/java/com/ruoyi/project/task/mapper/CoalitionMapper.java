package com.ruoyi.project.task.mapper;

import com.ruoyi.project.company.domain.Company;
import com.ruoyi.project.task.domain.Coalition;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface CoalitionMapper {
    /**
     * 插入联盟记录
     */
    int insertCoalition(Coalition coalition);

    /**
     * 批量插入联盟企业关系
     */
    int insertCoalitionCompany(@Param("companyIds") Set<Long> companyIds, @Param("coalitionId") Long coalitionId);

    /**
     * 根据任务ID查询联盟信息
     */
    Coalition selectCoalitionByTaskId(Long taskId);

    /**
     * 查询联盟内的企业信息
     */
    List<Company> selectCoalitionCompanies(Long coalitionId);
} 