package com.ruoyi.project.company.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.task.domain.TaskResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.company.domain.Company;
import com.ruoyi.project.company.service.ICompanyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.company.domain.Resource;
import com.ruoyi.project.company.domain.CompanyResource;
import com.ruoyi.project.company.service.IResourceService;
import com.ruoyi.project.company.service.ICompanyResourceService;

/**
 * companyController
 * 
 * @author cl
 * @date 2025-01-14
 */
@RestController
@RequestMapping("/company/management")
public class CompanyController extends BaseController {
    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private ICompanyResourceService companyResourceService;

    /**
     * 查询company列表
     */
    @PreAuthorize("@ss.hasPermi('company:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(Company company) {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * 导出company列表
     */
    @PreAuthorize("@ss.hasPermi('company:management:export')")
    @Log(title = "company", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Company company) {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        util.exportExcel(response, list, "company数据");
    }

    /**
     * 获取company详细信息
     */
    @PreAuthorize("@ss.hasPermi('company:management:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(companyService.selectCompanyById(id));
    }

    /**
     * 新增company
     */
    @PreAuthorize("@ss.hasPermi('company:management:add')")
    @Log(title = "company", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Company company) {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改company
     */
    @PreAuthorize("@ss.hasPermi('company:management:edit')")
    @Log(title = "company", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Company company) {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除company
     */
    @PreAuthorize("@ss.hasPermi('company:management:remove')")
    @Log(title = "company", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(companyService.deleteCompanyByIds(ids));
    }


    @GetMapping("/resource/options")
    public AjaxResult getResourceOptions(@RequestParam(required = false) String keyword) {
        List<Resource> resources = resourceService.selectResourceList(keyword);
        return success(resources);
    }

    /**
     * 获取企业资源列表,分页
     */
    @GetMapping("/resources/list/{companyId}")
    public TableDataInfo listResources(
            @PathVariable("companyId") Long companyId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        startPage();
        List<CompanyResource> list = companyResourceService.selectResourcesByCompanyId(companyId);
        return getDataTable(list);
    }

    @GetMapping("/resources/{companyId}")
    public AjaxResult getResources(@PathVariable("companyId") Long companyId) {
        List<CompanyResource> list = companyResourceService.selectResourcesByCompanyId(companyId);
        return success(list);
    }
    /**
     * 新增企业资源
     */
    @PostMapping("/resources")
    public AjaxResult addResource(@RequestBody CompanyResource companyResource) {
        return toAjax(companyResourceService.insertCompanyResource(companyResource));
    }

    /**
     * 修改企业资源
     */
    @PutMapping("/resources")
    public AjaxResult updateResource(@RequestBody CompanyResource companyResource) {
        return toAjax(companyResourceService.updateCompanyResource(companyResource));
    }

    /**
     * 删除企业资源
     */
    @DeleteMapping("/resources/{companyId}/{resourceId}")
    public AjaxResult removeResource(@PathVariable("companyId") Long companyId,
            @PathVariable("resourceId") Long resourceId) {
        return toAjax(companyResourceService.deleteCompanyResource(companyId, resourceId));
    }
}
