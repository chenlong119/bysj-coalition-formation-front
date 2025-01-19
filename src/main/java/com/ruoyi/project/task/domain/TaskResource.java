package com.ruoyi.project.task.domain;

import com.ruoyi.project.company.domain.Resource;
import lombok.Data;

@Data
public class TaskResource {
    private Long taskId;
    private Long resourceId;
    private Integer number;

    // 关联资源信息（非数据库字段）
    private Resource resource;
}
