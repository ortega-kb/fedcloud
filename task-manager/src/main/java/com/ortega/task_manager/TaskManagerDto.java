package com.ortega.task_manager;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskManagerDto {
    private String taskId = "task_" + UUID.randomUUID();
    private int duration;
    private int cpuRequired;
    private int memoryRequired;
}
