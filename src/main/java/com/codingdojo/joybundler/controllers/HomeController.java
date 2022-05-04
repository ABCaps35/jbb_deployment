package com.codingdojo.joybundler.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.joybundler.models.Name;
import com.codingdojo.joybundler.models.User;
import com.codingdojo.joybundler.services.NameService;
import com.codingdojo.joybundler.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private NameService nameService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		
		Long user_id = (Long) session.getAttribute("user_id");
		User user = userService.findUser(user_id);
		
		List<Name> allNames = nameService.allNames();
		for(Name name : allNames) {
			name.setDidVote(nameService.didVote(name.getId(), user_id));
		}
		
		model.addAttribute("all_names",allNames);
		model.addAttribute("username",user.getName());
		return "home.jsp";
	}
	
	//new
	@PutMapping("/home")
	public String toggleVote(
			@RequestParam("name_id") Long nameId,
			@RequestParam("action") String action,
			HttpSession session
			) 
	{
		Long userId = (Long) session.getAttribute("user_id");
		Name name = nameService.findName(nameId);
		
		if(name!=null) {
			if(action.equals("upvote")) {
				nameService.upvote(nameId, userId);
			}
			else if(action.equals("downvote")) {
				nameService.removeVote(nameId, userId);
			}
		}
		return "redirect:/home";
	}
}
