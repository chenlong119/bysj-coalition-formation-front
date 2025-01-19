package com.ruoyi.project.task.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * task对象 task
 * 
 * @author ruoyi
 * @date 2025-01-15
 */
public class Task extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务编号 */
    private Long id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;

    /** 任务所属产业链编号 */
    @Excel(name = "任务所属产业链编号")
    private Long taskChain;

    /** 任务预算 */
    @Excel(name = "任务预算")
    private Double budget;

    /** 到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date arrivalTime;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long taskStatus;

    /** 任务所属企业编号 */
    @Excel(name = "任务所属企业编号")
    private Long companyId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTaskChain(Long taskChain) 
    {
        this.taskChain = taskChain;
    }

    public Long getTaskChain() 
    {
        return taskChain;
    }
    public void setBudget(Double budget) 
    {
        this.budget = budget;
    }

    public Double getBudget() 
    {
        return budget;
    }
    public void setArrivalTime(Date arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() 
    {
        return arrivalTime;
    }
    public void setTaskStatus(Long taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() 
    {
        return taskStatus;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("taskChain", getTaskChain())
            .append("budget", getBudget())
            .append("arrivalTime", getArrivalTime())
            .append("taskStatus", getTaskStatus())
            .append("companyId", getCompanyId())
            .toString();
    }
}
