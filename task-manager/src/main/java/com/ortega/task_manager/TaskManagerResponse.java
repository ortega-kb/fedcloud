package com.ortega.task_manager;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskManagerResponse {
    private String taskId;
    private int duration;
    private int cpuRequired;
    private int memoryRequired;
}
