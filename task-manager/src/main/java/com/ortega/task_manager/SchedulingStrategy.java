package com.ortega.task_manager;


import java.util.Collection;

public interface SchedulingStrategy {

    ResourceManagerStatusDto selectedCloud(Collection<ResourceManagerStatusDto> clouds, CloudRunnerTaskDto cloudRunnerTaskDto);

}
