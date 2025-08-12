package com.ortega.resource_manager;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class ResourceManagerResponse {

    private Collection<ResourceManagerStatusDto> clouds;

}

