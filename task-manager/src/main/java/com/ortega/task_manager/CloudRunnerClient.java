package com.ortega.task_manager;

import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

public interface CloudRunnerClient {

    @RequestLine("POST /api/v1/cloud-runner/run-task")
    @Headers("Content-Type: application/json")
    void runTask(@RequestBody CloudRunnerTaskDto cloudRunnerTaskDto);

}
