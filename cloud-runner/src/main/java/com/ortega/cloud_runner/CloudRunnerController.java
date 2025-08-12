package com.ortega.cloud_runner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cloud-runner")
public class CloudRunnerController {

    private final CloudRunnerService cloudRunnerService;

    @PostMapping("/run-task")
    public void runTask(@RequestBody CloudRunnerTaskDto cloudRunnerTaskDto) {
        cloudRunnerService.runTask(cloudRunnerTaskDto);
    }
}
