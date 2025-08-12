package com.ortega.resource_manager;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CloudRunnerTaskDto {

    private String taskId;
    private int duration;
    private int cpuRequired;
    private int memoryRequired;

}
