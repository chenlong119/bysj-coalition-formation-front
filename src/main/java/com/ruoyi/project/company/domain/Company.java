package com.ruoyi.project.company.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * company对象 company
 * 
 * @author cl
 * @date 2025-01-14
 */
public class Company extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 企业编号 */
    private Long id;

    /** 所属产业链编号 */
    @Excel(name = "所属产业链编号")
    private Long chainId;

    /** 企业类型 */
    @Excel(name = "企业类型")
    private Long companyType;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String name;

    /** 企业所在地 */
    @Excel(name = "企业所在地")
    private String address;

    /** 企业声誉 */
    @Excel(name = "企业声誉")
    private Double reputation;

    /** 企业状态 */
    @Excel(name = "企业状态")
    private Long status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setChainId(Long chainId) {
        this.chainId = chainId;
    }

    public Long getChainId() {
        return chainId;
    }

    public void setCompanyType(Long companyType) {
        this.companyType = companyType;
    }

    public Long getCompanyType() {
        return companyType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("chainId", getChainId())
                .append("companyType", getCompanyType())
                .append("name", getName())
                .append("address", getAddress())
                .append("reputation", getReputation())
                .append("status", getStatus())
                .toString();
    }
}
