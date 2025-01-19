package com.ruoyi.project.company.mapper;

import com.ruoyi.project.company.domain.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {
  /**
   * 查询资源列表
   * 
   * @param keyword 搜索关键词
   * @return 资源列表
   */
  public List<Resource> selectResourceList(@Param("keyword") String keyword);

  Resource selectById(Long id);
}