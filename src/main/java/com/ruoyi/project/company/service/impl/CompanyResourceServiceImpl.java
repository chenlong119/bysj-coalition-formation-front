package com.ruoyi.project.company.service.impl;

import com.ruoyi.project.company.domain.CompanyResource;
import com.ruoyi.project.company.domain.Resource;
import com.ruoyi.project.company.mapper.CompanyResourceMapper;
import com.ruoyi.project.company.mapper.ResourceMapper;
import com.ruoyi.project.company.service.ICompanyResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyResourceServiceImpl implements ICompanyResourceService {
  @Autowired
  private CompanyResourceMapper companyResourceMapper;

  @Autowired
  private ResourceMapper resourceMapper;

  @Override
  public List<CompanyResource> selectResourcesByCompanyId(Long companyId) {
    List<CompanyResource> resources = companyResourceMapper.selectByCompanyId(companyId);
    // 填充资源详细信息
    for (CompanyResource cr : resources) {
      Resource resource = resourceMapper.selectById(cr.getResourceId());
      cr.setResource(resource);
    }
    return resources;
  }

  @Override
  public int insertCompanyResource(CompanyResource companyResource) {
    return companyResourceMapper.insert(companyResource);
  }

  @Override
  public int updateCompanyResource(CompanyResource companyResource) {
    return companyResourceMapper.update(companyResource);
  }

  @Override
  public int deleteCompanyResource(Long companyId, Long resourceId) {
    return companyResourceMapper.delete(companyId, resourceId);
  }
}