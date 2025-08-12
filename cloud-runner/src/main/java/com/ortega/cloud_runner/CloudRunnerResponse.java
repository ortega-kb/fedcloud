package com.ortega.cloud_runner;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CloudRunnerResponse {

    private String name;
    private int cpu;
    private int memory;
    private double cost;
    private double speed;

}
