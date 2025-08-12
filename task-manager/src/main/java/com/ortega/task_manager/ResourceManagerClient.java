package com.ortega.task_manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "resource-manager", url = "${task-manager.resource-manager}")
public interface ResourceManagerClient {

    @GetMapping("/api/v1/resource-manager")
    ResourceManagerResponse getClouds();

}
