package com.exam.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Task;
public interface TaskRepository extends JpaRepository<Task, Long> {
	 List<Task> findByUserUsername(String username);
}