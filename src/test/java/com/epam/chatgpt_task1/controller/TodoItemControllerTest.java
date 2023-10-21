package com.epam.chatgpt_task1.controller;

import com.epam.chatgpt_task1.model.TodoItem;
import com.epam.chatgpt_task1.repository.TodoItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoItemController.class)
public class TodoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoItemRepository todoItemRepository;

    @Test
    public void testGetAllTodoItems() throws Exception {
        List<TodoItem> todoItems = new ArrayList<>();
        todoItems.add(new TodoItem(1L, "Task 1", "Description 1"));
        todoItems.add(new TodoItem(2L, "Task 2", "Description 2"));

        when(todoItemRepository.findAll()).thenReturn(todoItems);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo-items")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].title").value("Task 1"))
               .andExpect(jsonPath("$[1].title").value("Task 2"));
    }


    @Test
    public void testGetTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem(1L, "Task 1", "Description 1");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo-items/1")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.title").value("Task 1"));
    }

    @Test
    public void testGetTodoItem_IfNotFound() throws Exception {
        TodoItem todoItem = new TodoItem(1L, "Task 1", "Description 1");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo-items/2")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem(null, "Task 3", "Description 3");
        TodoItem createdItem = new TodoItem(3L, "Task 3", "Description 3");

        when(todoItemRepository.save(todoItem)).thenReturn(createdItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo-items")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"title\":\"Task 3\",\"description\":\"Description 3\"}"))
               .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem(1L, "Task 1", "Description 1");
        TodoItem updatedItem = new TodoItem(1L, "Updated Task", "Updated Description");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));
        when(todoItemRepository.save(updatedItem)).thenReturn(updatedItem);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo-items/1")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"title\":\"Updated Task\",\"description\":\"Updated Description\"}"))
               .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTodoItem_IfNotFound() throws Exception {
        TodoItem todoItem = new TodoItem(1L, "Task 1", "Description 1");
        TodoItem updatedItem = new TodoItem(1L, "Updated Task", "Updated Description");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));
        when(todoItemRepository.save(updatedItem)).thenReturn(updatedItem);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo-items/2")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content("{\"title\":\"Updated Task\",\"description\":\"Updated Description\"}"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTodoItem() throws Exception {
        TodoItem todoItem = new TodoItem(1L, "Task 1", "Description 1");

        when(todoItemRepository.findById(1L)).thenReturn(Optional.of(todoItem));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo-items/1"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteTodoItem_NotFound() throws Exception {
        when(todoItemRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo-items/1"))
               .andExpect(status().isNotFound());
    }
}