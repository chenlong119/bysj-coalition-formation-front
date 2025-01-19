package com.ruoyi.project.task.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.company.domain.Company;
import com.ruoyi.project.company.domain.CompanyResource;
import com.ruoyi.project.task.domain.CoalitionExecuteParams;
import com.ruoyi.project.task.domain.CoalitionFormationResults;
import com.ruoyi.project.task.domain.Task;
import com.ruoyi.project.task.domain.TaskResource;

/**
 * taskService接口
 * 
 * @author ruoyi
 * @date 2025-01-15
 */
public interface ITaskService {
    /**
     * 查询task
     * 
     * @param id task主键
     * @return task
     */
    public Task selectTaskById(Long id);

    /**
     * 查询task列表
     * 
     * @param task task
     * @return task集合
     */
    public List<Task> selectTaskList(Task task);

    /**
     * 新增task
     * 
     * @param task task
     * @return 结果
     */
    public int insertTask(Task task);

    /**
     * 修改task
     * 
     * @param task task
     * @return 结果
     */
    public int updateTask(Task task);

    /**
     * 批量删除task
     * 
     * @param ids 需要删除的task主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    /**
     * 删除task信息
     * 
     * @param id task主键
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 获取异常任务所属企业信息
     * 
     * @param companyId 企业ID
     * @return 企业信息
     */
    public Company selectCompanyByTaskAbnormal(Long companyId);

    List<TaskResource> selectResourcesByTaskId(Long taskId);

    int insertTaskResource(TaskResource taskResource);

    int updateTaskResource(TaskResource taskResource);

    int deleteTaskResource(Long taskId, Long resourceId);

    CoalitionFormationResults coalitionFormation(Long taskId);

    int executeCoalitionAllocation(CoalitionExecuteParams params);

    Map<String, Object> getCoalitionDetails(Long taskId);
}
