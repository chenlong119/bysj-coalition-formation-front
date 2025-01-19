package com.ruoyi.project.task.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.project.company.domain.CompanyResource;
import com.ruoyi.project.company.domain.Resource;
import com.ruoyi.project.company.service.IResourceService;
import com.ruoyi.project.task.domain.TaskResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.task.domain.Task;
import com.ruoyi.project.task.service.ITaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.task.domain.CoalitionExecuteParams;

/**
 * taskController
 * 
 * @author ruoyi
 * @date 2025-01-15
 */
@RestController
@RequestMapping("/task/management")
public class TaskController extends BaseController {
    @Autowired
    private ITaskService taskService;

    @Autowired
    private IResourceService resourceService;
    /**
     * 查询task列表
     */
    @PreAuthorize("@ss.hasPermi('task:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(Task task) {
        startPage();
        List<Task> list = taskService.selectTaskList(task);
        return getDataTable(list);
    }

    /**
     * 导出task列表
     */
    @PreAuthorize("@ss.hasPermi('task:management:export')")
    @Log(title = "task", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Task task) {
        List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        util.exportExcel(response, list, "task数据");
    }

    /**
     * 获取task详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:management:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(taskService.selectTaskById(id));
    }

    /**
     * 新增task
     */
    @PreAuthorize("@ss.hasPermi('task:management:add')")
    @Log(title = "task", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Task task) {
        return toAjax(taskService.insertTask(task));
    }

    /**
     * 修改task
     */
    @PreAuthorize("@ss.hasPermi('task:management:edit')")
    @Log(title = "task", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Task task) {
        return toAjax(taskService.updateTask(task));
    }

    /**
     * 删除task
     */
    @PreAuthorize("@ss.hasPermi('task:management:remove')")
    @Log(title = "task", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(taskService.deleteTaskByIds(ids));
    }

    /**
     * 获取异常任务所属企业信息
     */
    @PreAuthorize("@ss.hasPermi('task:management:query')")
    @GetMapping("/abnormal/company/{companyId}")
    public AjaxResult getTaskAbnormalInfo(@PathVariable("companyId") Long companyId) {
        return success(taskService.selectCompanyByTaskAbnormal(companyId));
    }

    @GetMapping("/resources/{taskId}")
    public AjaxResult getResources(@PathVariable("taskId") Long taskId) {
        List<TaskResource> list = taskService.selectResourcesByTaskId(taskId);
        return success(list);
    }

    @GetMapping("/resources/list/{taskId}")
    public TableDataInfo listResources(
            @PathVariable("taskId") Long taskId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        startPage();
        List<TaskResource> list = taskService.selectResourcesByTaskId(taskId);
        return getDataTable(list);
    }

//    新增资源
    @PostMapping("/resources")
    public AjaxResult addResource(@RequestBody TaskResource taskResource) {
        return toAjax(taskService.insertTaskResource(taskResource));
    }

    @GetMapping("/resource/options")
    public AjaxResult getResourceOptions(@RequestParam(required = false) String keyword) {
        List<Resource> resources = resourceService.selectResourceList(keyword);
        return success(resources);
    }

    @PutMapping("/resources")
    public AjaxResult updateResource(@RequestBody TaskResource taskResource) {
        return toAjax(taskService.updateTaskResource(taskResource));
    }

    @DeleteMapping("/resources/{taskId}/{resourceId}")
    public AjaxResult removeResource(@PathVariable("taskId") Long taskId,
                                     @PathVariable("resourceId") Long resourceId) {
        return toAjax(taskService.deleteTaskResource(taskId, resourceId));
    }

    @GetMapping("/coalition/formation/{taskId}")
    public AjaxResult coalitionFormation(@PathVariable("taskId") Long taskId) {
        return success(taskService.coalitionFormation(taskId));
    }

    @PostMapping("/coalition/execute")
    public AjaxResult executeCoalitionAllocation(@RequestBody CoalitionExecuteParams params) {
        return toAjax(taskService.executeCoalitionAllocation(params));
    }
}
