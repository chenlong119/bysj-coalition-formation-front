package com.ruoyi.project.company.service;

import com.ruoyi.project.company.domain.CompanyResource;
import java.util.List;

public interface ICompanyResourceService {
  /**
   * 查询企业的资源列表
   */
  List<CompanyResource> selectResourcesByCompanyId(Long companyId);

  /**
   * 新增企业资源
   */
  int insertCompanyResource(CompanyResource companyResource);

  /**
   * 修改企业资源
   */
  int updateCompanyResource(CompanyResource companyResource);

  /**
   * 删除企业资源
   */
  int deleteCompanyResource(Long companyId, Long resourceId);
}