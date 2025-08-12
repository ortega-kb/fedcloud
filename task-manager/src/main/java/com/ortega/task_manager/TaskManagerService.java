package com.ortega.task_manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskManagerService {

    private final Map<String, SchedulingStrategy> strategies;
    private final ResourceManagerClient resourceManagerClient;
    private final CloudRunnerClientFactory cloudRunnerClientFactory;

    @Value("${task-manager.scheduling.strategy}")
    private String selectedStrategy;

    public TaskManagerResponse createTask(TaskManagerDto taskManagerDto) {
        log.info("Creating new task");

        CloudRunnerTaskDto cloudRunnerTaskDto = CloudRunnerTaskDto.builder()
                .taskId(taskManagerDto.getTaskId())
                .memoryRequired(taskManagerDto.getMemoryRequired())
                .cpuRequired(taskManagerDto.getCpuRequired())
                .duration(taskManagerDto.getDuration())
                .build();

        // Recuperation des ressources des clouds
        Collection<ResourceManagerStatusDto> clouds = resourceManagerClient.getClouds().getClouds();
        // Selection de la stratégie d'ordonnancement voulue
        SchedulingStrategy strategy = strategies.get(selectedStrategy);

        // Application de l'algorithme selon la stratégie
        ResourceManagerStatusDto selectedCloud = strategy.selectedCloud(clouds, cloudRunnerTaskDto);
        // Envoi de la tache au cloud optimal
        sendToRunner(cloudRunnerTaskDto, selectedCloud.getHost());

        return TaskManagerResponse.builder()
                .taskId(taskManagerDto.getTaskId())
                .memoryRequired(taskManagerDto.getMemoryRequired())
                .cpuRequired(taskManagerDto.getCpuRequired())
                .duration(taskManagerDto.getDuration())
                .build();
    }


    private void sendToRunner(CloudRunnerTaskDto cloudRunnerTaskDto, String host) {
        CloudRunnerClient cloudRunnerClient = cloudRunnerClientFactory.getClient(host);

        cloudRunnerClient.runTask(cloudRunnerTaskDto);
    }

}
