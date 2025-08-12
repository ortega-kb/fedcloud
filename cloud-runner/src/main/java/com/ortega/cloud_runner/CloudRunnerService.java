package com.ortega.cloud_runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudRunnerService {

    @Value("${cloud-runner.name}")
    private String name;

    @Value("${cloud-runner.cpu}")
    private int cpu;

    @Value("${cloud-runner.memory}")
    private int memory;

    @Value("${cloud-runner.cost}")
    private double cost;

    @Value("${cloud-runner.speed}")
    private double speed;

    @Value("${server.port}")
    private int port;

    @Value("${cloud-runner.host}")
    private String host;



    private final ResourceManagerClient resourceManagerClient;
    private final List<CloudRunnerTask> activeTasks = new CopyOnWriteArrayList<>();


    public void runTask(CloudRunnerTaskDto cloudRunnerTaskDto) {
        log.info("Adding new {}", cloudRunnerTaskDto.getTaskId());

        CloudRunnerTask task = CloudRunnerTask.builder()
                .taskId(cloudRunnerTaskDto.getTaskId())
                .cost(cloudRunnerTaskDto.getDuration() * cost)
                .cpuRequired(cloudRunnerTaskDto.getCpuRequired())
                .duration(cloudRunnerTaskDto.getDuration())
                .memoryRequired(cloudRunnerTaskDto.getMemoryRequired())
                .build();

        activeTasks.add(task);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            activeTasks.remove(task);
            notifyResourceManager();
        }, (long)(task.getDuration() * speed), TimeUnit.SECONDS);
    }

    @Scheduled(fixedRate = 1000)
    public void periodicStatusUpdate() {
        notifyResourceManager();
    }

    private void notifyResourceManager() {
        log.info("Notify resource manager");

        int availableCpu = getAvailableCpu();
        int availableMemory = getAvailableMemory();

        ResourceManagerStatusDto status = ResourceManagerStatusDto.builder()                .cost(cost)
                .cpu(availableCpu)
                .port(port)
                .memory(availableMemory)
                .name(name)
                .host(host)
                .speed(speed)
                .activeTasks(activeTasks)
                .build();

        resourceManagerClient.updateCloudStatus(status);
    }

    private int getAvailableMemory() {
        int taskMemories = 0;
        for (CloudRunnerTask task: activeTasks) {
            taskMemories += task.getMemoryRequired();
        }

        return memory - taskMemories;
    }

    private int getAvailableCpu() {
        int taskCpus = 0;
        for (CloudRunnerTask task: activeTasks) {
            taskCpus += task.getCpuRequired();
        }

        return cpu - taskCpus;
    }
}
