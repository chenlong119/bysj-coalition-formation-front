package com.ruoyi.project.task.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Coalition extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 企业联盟编号 */
    private Long id;

    /** 联盟名称 */
    @Excel(name = "联盟名称")
    private String name;

    /** 联盟对应任务编号 */
    @Excel(name = "任务编号")
    private Long taskId;

    /** 联盟形成时间 */
    @Excel(name = "联盟形成时间")
    private Date createTime;

    /** 联盟状态 */
    @Excel(name = "联盟状态")
    private Integer coalitionStatus;

    @Excel(name = "运输成本")
    private Long transportCost;

    @Excel(name = "联盟声誉")
    private Double coalitionReputation;
} 