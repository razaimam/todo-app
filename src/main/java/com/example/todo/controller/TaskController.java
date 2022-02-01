package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Todo", value = "Todo")
@RestController
@RequestMapping("api/v1/todo")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation(value = "Get all tasks", response = Task.class, responseContainer = "Array")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(tasks);
    }

    @ApiOperation(value = "Get a task", response = Task.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task get(@PathVariable Integer taskId) {
        return taskService.get(taskId);
    }

    @ApiOperation(value = "Add task", response = Task.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping(value = "/addTask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
    }

    @ApiOperation(value = "Update task", response = Task.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping(value = "/updateTask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
    }

    @ApiOperation(value = "Update task", response = Task.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping(value = "/deleteTask/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> deleteTask(@PathVariable Integer taskId) {
        Task task = taskService.deleteTask(taskId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(task);
    }
}
