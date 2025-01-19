package com.ruoyi.project.company.service.impl;

import com.ruoyi.project.company.domain.Resource;
import com.ruoyi.project.company.mapper.ResourceMapper;
import com.ruoyi.project.company.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements IResourceService {
  @Autowired
  private ResourceMapper resourceMapper;

  @Override
  public List<Resource> selectResourceList(String keyword) {
    return resourceMapper.selectResourceList(keyword);
  }
}