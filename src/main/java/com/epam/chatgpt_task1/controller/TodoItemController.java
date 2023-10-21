package com.epam.chatgpt_task1.controller;

import com.epam.chatgpt_task1.model.TodoItem;
import com.epam.chatgpt_task1.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todo-items")
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoItemRepository.findById(id);
        if (todoItem.isPresent()) {
            return ResponseEntity.ok(todoItem.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem updatedItem) {
        Optional<TodoItem> todoItem = todoItemRepository.findById(id);
        if (todoItem.isPresent()) {
            updatedItem.setId(id);
            TodoItem updated = todoItemRepository.save(updatedItem);
            return ResponseEntity.ok(updated); // Ensure the updated item is returned
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        if (todoItemRepository.existsById(id)) {
            todoItemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
