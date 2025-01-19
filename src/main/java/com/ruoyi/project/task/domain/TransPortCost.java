package com.ruoyi.project.task.domain;

import lombok.Data;

@Data
public class TransPortCost {
    private Long fromCompanyId;
    private Long toCompanyId;
    private Integer cost;
}
