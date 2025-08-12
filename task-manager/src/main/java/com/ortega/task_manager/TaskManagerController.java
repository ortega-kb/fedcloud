package com.ortega.task_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task-manager")
public class TaskManagerController {

    private final TaskManagerService taskManagerService;

    @GetMapping
    public String index() {
        return "task-manager";
    }

    @PostMapping
    public ResponseEntity<TaskManagerResponse> createTask(@RequestBody TaskManagerDto taskManagerDto) {
        TaskManagerResponse taskManagerResponse = taskManagerService.createTask(taskManagerDto);
        return ResponseEntity.ok(taskManagerResponse);
    }

}
