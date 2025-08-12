package com.ortega.task_manager;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;

@Component("cost")
public class CostBasedStrategy implements SchedulingStrategy {
    @Override
    public ResourceManagerStatusDto selectedCloud(
            Collection<ResourceManagerStatusDto> clouds,
            CloudRunnerTaskDto task
    ) {
        return clouds.stream()
                .filter(
                        c -> c.getCpu() >= task.getCpuRequired()
                                && c.getMemory() >= task.getMemoryRequired()
                )
                .min(Comparator.comparingDouble(ResourceManagerStatusDto::getCost))
                .orElseThrow(() -> new RuntimeException("Aucun cloud disponible selon le coût"));
    }
}
