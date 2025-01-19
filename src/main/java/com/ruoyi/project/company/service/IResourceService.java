package com.ruoyi.project.company.service;

import com.ruoyi.project.company.domain.Resource;
import java.util.List;

public interface IResourceService {
  /**
   * 查询资源列表
   * 
   * @param keyword 搜索关键词
   * @return 资源列表
   */
  public List<Resource> selectResourceList(String keyword);
}