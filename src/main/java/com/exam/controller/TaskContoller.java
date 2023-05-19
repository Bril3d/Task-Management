package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.model.Task;
import com.exam.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class TaskContoller {
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/")
	public String accueil(HttpSession session, Model model) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			List<Task> tasks = taskService.listAllTasks(loggedInUser);
			model.addAttribute("tasks", tasks);
			return "index";
		} else {
			return "login";
		}
	}

	@GetMapping("/addTask")
	public String addTask(HttpSession session,Model model) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			Task task = new Task();
			model.addAttribute("task", task);
			return "add_task";
		} else {
			return "login";
		}
	}

	@PostMapping("/addTask")
	public String saveTask(HttpSession session,@ModelAttribute("task") Task task, BindingResult result) {
		if (result.hasErrors()) {
			return "add_task";
		}
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		User user = userRepository.findByUsername(loggedInUser);
		task.setUser(user);
		taskService.saveTask(task);
		return "redirect:/";
	}

	@GetMapping("/updateTask/{id}")
	public String updateTask(HttpSession session,@PathVariable("id") Long id, Model model) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			Task task = taskService.getTaskById(id);
			model.addAttribute("task", task);
			return "update_task";
		} else {
			return "login";
		}
	}

	@PostMapping("/update")
	public String update(HttpSession session,@ModelAttribute("task") Task task, BindingResult result) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		User user = userRepository.findByUsername(loggedInUser);
		task.setUser(user);
		taskService.saveTask(task);
		return "redirect:/";
	}

	@GetMapping("/deleteTask/{id}")
	public String deleteTask(HttpSession session,@PathVariable("id") Long id) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			taskService.deleteTask(id);
			return "redirect:/";
		} else {
			return "login";
		}
	}
	
	@GetMapping("/doneTask/{id}")
	public String doneTask(HttpSession session,@PathVariable("id") Long id) {
		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			taskService.doneTask(id);
			return "redirect:/";
		} else {
			return "login";
		}
	}
}