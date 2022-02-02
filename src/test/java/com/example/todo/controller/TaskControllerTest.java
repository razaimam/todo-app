package com.example.todo.controller;

import com.example.todo.models.Task;
import com.example.todo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Tag("integration")
public class TaskControllerTest {
    @SpyBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGet() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setDesc("Desc1");
        doReturn(task).when(taskService).get(anyInt());
        mockMvc.perform(get("/api/v1/todo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desc").value(task.getDesc()))
                .andExpect(jsonPath("$.id").value(task.getId()));
        verify(taskService).get(any());
    }

    @Test
    void testGetAllEmpty() throws Exception {
        doReturn(Collections.emptyList()).when(taskService).getAll();
        mockMvc.perform(get("/api/v1/todo/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
        verify(taskService).getAll();
    }

    @Test
    void testGetAll() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setDesc("Desc1");
        doReturn(Lists.newArrayList(task)).when(taskService).getAll();
        mockMvc.perform(get("/api/v1/todo/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(task.getId())))
                .andExpect(jsonPath("$[*].desc", containsInAnyOrder(task.getDesc())));
        verify(taskService).getAll();
    }

    @Test
    void testPost() throws Exception {
        Task task = new Task();
        task.setDesc("Desc1");
        doReturn(task).when(taskService).addTask(any(Task.class));
        mockMvc.perform(post("/api/v1/todo/addTask/")
                        .contentType(APPLICATION_JSON).content(mapper.writeValueAsBytes(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desc").value(task.getDesc()));
        verify(taskService).addTask(any(Task.class));
    }

    @Test
    void testPut() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setDesc("DescTest");
        doReturn(task).when(taskService).updateTask(any(Task.class));
        mockMvc.perform(put("/api/v1/todo/updateTask/")
                        .contentType(APPLICATION_JSON).content(mapper.writeValueAsBytes(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desc").value(task.getDesc()))
                .andExpect(jsonPath("$.id").value(task.getId()));
        verify(taskService).updateTask(any(Task.class));
    }

    @Test
    void testDelete() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setDesc("DescTest");
        doReturn(task).when(taskService).deleteTask(anyInt());
        mockMvc.perform(delete("/api/v1/todo/deleteTask/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desc").value(task.getDesc()))
                .andExpect(jsonPath("$.id").value(task.getId()));
        verify(taskService).deleteTask(anyInt());
    }
}
