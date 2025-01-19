package com.ruoyi.project.company.domain;

import lombok.Data;

@Data
public class CompanyResource {
  private Long companyId;
  private Long resourceId;
  private Integer number;

  // 关联资源信息（非数据库字段）
  private Resource resource;
}