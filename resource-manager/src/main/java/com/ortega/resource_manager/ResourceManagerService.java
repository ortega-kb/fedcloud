package com.ortega.resource_manager;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ResourceManagerService {

    private final Map<String, ResourceManagerStatusDto> cloudStates = new ConcurrentHashMap<>();

    public void updateCloudStatus(ResourceManagerStatusDto status) {
        cloudStates.compute(status.getName(), (name, existing) -> {
            if (existing == null) {
                return status;
            } else {
                existing.setCost(status.getCost());
                existing.setPort(status.getPort());
                existing.setHost(status.getHost());
                existing.setCpu(status.getCpu());
                existing.setMemory(status.getMemory());
                existing.setSpeed(status.getSpeed());
                existing.setActiveTasks(status.getActiveTasks());
                return existing;
            }
        });
    }

    public ResourceManagerResponse getClouds() {
        log.info("Getting cloud informations");

        return ResourceManagerResponse.builder()
                .clouds(cloudStates.values())
                .build();
    }

}
