package com.ruoyi.project.company.mapper;

import com.ruoyi.project.company.domain.CompanyResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyResourceMapper {
  List<CompanyResource> selectByCompanyId(Long companyId);

  int insert(CompanyResource companyResource);

  int update(CompanyResource companyResource);

  int delete(@Param("companyId") Long companyId, @Param("resourceId") Long resourceId);

  long getResourceSum(Long resourceId);

  long getCompanyCount(Long resourceId);

  CompanyResource selectByCompanyIdAndResourceId(@Param("companyId") Long companyId, @Param("resourceId") Long resourceId);

  /**
   * 批量查询所有公司资源
   * 
   * @return 公司资源列表
   */
  List<CompanyResource> selectBatchCompanyResources();

  void updateResourceAmount(@Param("companyId") Long companyId, 
                          @Param("resourceId") Long resourceId, 
                          @Param("amount") Integer amount);
}