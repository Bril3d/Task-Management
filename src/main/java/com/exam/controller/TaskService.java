package com.exam.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Task;
import com.exam.repository.TaskRepository;
@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	public List<Task> listAllTasks(String loggedInUser) {
	List<Task> userTasks = taskRepository.findByUserUsername(loggedInUser);
	return userTasks;
	}
	public void saveTask(Task task) {
	taskRepository.save(task);
	}
	public Task getTaskById(Long id) {
	return taskRepository.findById(id).get();
	}
	public void deleteTask(Long id) {
	taskRepository.deleteById(id);
	}
	public void doneTask(Long id) {
	    Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setEtat("Done");
            taskRepository.save(task);
        }
	}

}
