package com.ortega.resource_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resource-manager")
public class ResourceManagerController {

    private final ResourceManagerService resourceManagerService;

    @GetMapping
    public ResponseEntity<ResourceManagerResponse> getClouds() {
        return ResponseEntity.ok(resourceManagerService.getClouds());
    }

    @PostMapping("/update-status")
    public ResponseEntity<String> updateStatus(@RequestBody ResourceManagerStatusDto status) {
        resourceManagerService.updateCloudStatus(status);
        return ResponseEntity.ok("Cloud status updated successfully.");
    }

}
