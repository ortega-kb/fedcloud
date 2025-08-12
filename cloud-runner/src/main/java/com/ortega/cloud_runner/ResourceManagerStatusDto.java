package com.ortega.cloud_runner;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResourceManagerStatusDto {

    private String name;
    private String host;
    private int port;
    private int cpu;
    private int memory;
    private double cost;
    private double speed;
    private List<CloudRunnerTask> activeTasks;

}
