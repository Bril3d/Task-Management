package com.exam.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
            return "registration";
        }

        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "User registered successfully! Please log in.");

        return "redirect:/login";
    }
        @GetMapping("/login")
        public String showLoginForm(Model model) {
            model.addAttribute("user", new User());
            return "login";
        }
        
        @PostMapping("/login")
        public String login(HttpSession session,@ModelAttribute("user") User user, Model model) {
            User existingUser = userRepository.findByUsername(user.getUsername());
            if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            	 session.setAttribute("loggedInUser", user.getUsername());
                // Login successful, redirect to home page
            	return "redirect:/";
            } else {
                // Login failed, display error message
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }
        }
        
        @GetMapping("/logout")
        public String logout(HttpSession session) {
        	session.invalidate();
            return "redirect:/login"; 
        }
        
        


    
}

