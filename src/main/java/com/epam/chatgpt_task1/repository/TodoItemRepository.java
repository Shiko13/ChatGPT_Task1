package com.epam.chatgpt_task1.repository;

import com.epam.chatgpt_task1.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
