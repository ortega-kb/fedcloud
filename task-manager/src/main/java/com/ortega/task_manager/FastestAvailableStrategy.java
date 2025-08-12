package com.ortega.task_manager;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;

@Component("fast")
public class FastestAvailableStrategy implements SchedulingStrategy {
    @Override
    public ResourceManagerStatusDto selectedCloud(Collection<ResourceManagerStatusDto> clouds, CloudRunnerTaskDto task) {
        return clouds.stream()
                .filter(
                        c -> c.getCpu() >= task.getCpuRequired()
                                && c.getMemory() >= task.getMemoryRequired()
                )
                .min(Comparator.comparingDouble(c -> task.getDuration() * c.getSpeed()))
                .orElseThrow(() -> new RuntimeException("Aucun cloud disponible selon la rapidité"));
    }
}