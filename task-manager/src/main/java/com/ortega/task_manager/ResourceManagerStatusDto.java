package com.ortega.task_manager;

import lombok.Data;

import java.util.List;

@Data
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
