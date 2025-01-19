package com.ruoyi.project.task.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CoalitionExecuteParams {
    private Long taskId;
    private List<Long> coalition;
    private Map<Long, Map<Long, Integer>> resourceAllocation;
    private Long transportCost;
    private Double coalitionReputation;
}