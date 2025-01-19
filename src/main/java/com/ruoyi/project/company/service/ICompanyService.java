package com.ruoyi.project.company.service;

import java.util.List;
import com.ruoyi.project.company.domain.Company;

/**
 * companyService接口
 * 
 * @author cl
 * @date 2025-01-14
 */
public interface ICompanyService 
{
    /**
     * 查询company
     * 
     * @param id company主键
     * @return company
     */
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
     * 批量删除company
     * 
     * @param ids 需要删除的company主键集合
     * @return 结果
     */
    public int deleteCompanyByIds(Long[] ids);

    /**
     * 删除company信息
     * 
     * @param id company主键
     * @return 结果
     */
    public int deleteCompanyById(Long id);
}
