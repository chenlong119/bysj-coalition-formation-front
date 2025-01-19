package com.ruoyi.project.company.mapper;

import java.util.List;
import java.util.Map;
import com.ruoyi.project.company.domain.Company;
import com.ruoyi.project.task.domain.TransPortCost;
import org.apache.ibatis.annotations.Param;

/**
 * companyMapper接口
 * 
 * @author cl
 * @date 2025-01-14
 */
public interface CompanyMapper 
{
    /**
     * 查询company
     * 
     * @param id company主键
     * @return company
     */
    public List<Company> getAllCompany();

    public Company selectCompanyById(Long id);

    /**
     * 查询company列表
     * 
     * @param company company
     * @return company集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增company
     * 
     * @param company company
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改company
     * 
     * @param company company
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 删除company
     * 
     * @param id company主键
     * @return 结果
     */
    public int deleteCompanyById(Long id);

    /**
     * 批量删除company
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyByIds(Long[] ids);

    public Integer getTransportCost(Long fromCompany,Long toCompany);

    /**
     * 获取所有企业间的运输成本
     * 
     * @return 运输成本列表
     */
    List<TransPortCost> getAllTransportCosts();

    /**
     * 更新企业状态
     * 
     * @param companyId 企业ID
     * @param status 状态值
     * @return 结果
     */
    int updateCompanyStatus(@Param("companyId") Long companyId, @Param("status") Integer status);
}
