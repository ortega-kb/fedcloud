package com.ortega.resource_manager;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CloudRunnerTask {

    private String taskId;
    private int duration;
    private int cpuRequired;
    private int memoryRequired;
    private double cost;

}
