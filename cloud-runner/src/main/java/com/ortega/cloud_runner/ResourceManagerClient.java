package com.ortega.cloud_runner;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "resource-manager", url = "${cloud-runner.resource-manager}")
public interface ResourceManagerClient {

    @PostMapping("/api/v1/resource-manager/update-status")
    void updateCloudStatus(@RequestBody ResourceManagerStatusDto status);

}
