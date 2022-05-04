package com.codingdojo.joybundler.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.joybundler.models.LoginUser;
import com.codingdojo.joybundler.models.User;
import com.codingdojo.joybundler.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	private String index(@ModelAttribute("newUser") User user, @ModelAttribute("newLogin") LoginUser login) {
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser, BindingResult result, 
	        Model model, HttpSession session) 
	{
		User user = userService.register(newUser, result);
	    
	    if(result.hasErrors()) {
	        model.addAttribute("newLogin", new LoginUser());
	        return "login.jsp";
	    }
	    else {
	    	session.setAttribute("user_id", user.getId());
	    	return "redirect:/home";
	    }
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, 
			Model model, HttpSession session) 
	{
	    User user = userService.login(newLogin, result);
	
	    if(result.hasErrors()) {
	        model.addAttribute("newUser", new User());
	        return "login.jsp";
	    }
	    else {
	    	session.setAttribute("user_id", user.getId());
	    	return "redirect:/home";
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
