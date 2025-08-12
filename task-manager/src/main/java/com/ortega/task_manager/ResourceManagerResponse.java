package com.ortega.task_manager;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ResourceManagerResponse {

    private Collection<ResourceManagerStatusDto> clouds;

}

