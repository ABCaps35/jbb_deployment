package com.codingdojo.joybundler.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.joybundler.models.Name;
import com.codingdojo.joybundler.models.User;
import com.codingdojo.joybundler.services.NameService;
import com.codingdojo.joybundler.services.UserService;

@Controller
@RequestMapping("/names")
public class NameController {
	@Autowired
	private NameService nameService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/new")
	public String add(@ModelAttribute("nameForm") Name name, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		return "addname.jsp";
	}
	
	@PostMapping("/new")
	public String add(@Valid @ModelAttribute("nameForm") Name nameIn, BindingResult result, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Name name = nameService.checkName(nameIn,result);
		if(result.hasErrors()) {
			System.out.println("error");
			return "addname.jsp";
		}
		else {
			User poster = userService.findUser((Long) session.getAttribute("user_id"));
			name.setPoster(poster);
			nameService.createName(name);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/{id}")
	public String view(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Name name = nameService.findName(id);
		name.setDidVote(nameService.didVote(name.getId(), (Long) session.getAttribute("user_id")));
		model.addAttribute("name", name);
		return "viewname.jsp";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		model.addAttribute("nameForm", nameService.findName(id));
		return "editname.jsp";
	}
	
	@PutMapping("/{id}")
	public String edit(
			@PathVariable("id") Long id, 
			Model model,
			HttpSession session,
			@Valid @ModelAttribute("nameForm") Name name, BindingResult result
	) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "editname.jsp";
		}
		else {
			nameService.updateName(id, name);
			return "redirect:/home";
		}
	}
	
	@DeleteMapping("/{id}")
	public String view(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		nameService.deleteName(id);
		return "redirect:/home";
	}
}
