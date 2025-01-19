package com.ruoyi.project.task.mapper;

import java.util.List;
import com.ruoyi.project.task.domain.Task;
import com.ruoyi.project.task.domain.TaskResource;

/**
 * taskMapper接口
 * 
 * @author ruoyi
 * @date 2025-01-15
 */
public interface TaskMapper 
{
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
     * 删除task
     * 
     * @param id task主键
     * @return 结果
     */
    public int deleteTaskById(Long id);

    /**
     * 批量删除task
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskByIds(Long[] ids);

    List<TaskResource> selectResourcesByTaskId(Long taskId);

    TaskResource selectTaskResource(Long taskId, Long resourceId);

    int insertTaskResource(TaskResource taskResource);

    int updateTaskResource(TaskResource taskResource);

    int deleteTaskResource(Long taskId, Long resourceId);
}
