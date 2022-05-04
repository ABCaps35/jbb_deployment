package com.codingdojo.joybundler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.joybundler.models.Name;
import com.codingdojo.joybundler.models.User;
import com.codingdojo.joybundler.repositories.NameRepository;

@Service
public class NameService {
	@Autowired
	private NameRepository nameRepository;
	@Autowired
	private UserService userService;
	
	public List<Name> allNames() {
		return nameRepository.findAll();
	}
	
	public Name createName(Name name) {
		return nameRepository.save(name);
	}
	
	//new
	public Name checkName(Name name, BindingResult result) {
		Optional<Name> maybeName = nameRepository.findByName(name.getName());
    	if(maybeName.isPresent()) {
    		result.rejectValue("name", "Matches", "Name already exists");
    	}
    	
    	if(result.hasErrors()) {
    	    return null;
    	}
    	else {
    		return name;
    	}
	}
	
	public Name findName(Long id) {
		Optional<Name> optionalName = nameRepository.findById(id);
		if(optionalName.isPresent()) {
			return optionalName.get();
		} else {
			return null;
		}
	}
	
	public Name updateName(Long id, Name nameIn) {
		Optional<Name> optionalName = nameRepository.findById(id);
		if(optionalName.isPresent()) {
			Name name = optionalName.get();
			name.setId(id);
			name.setName(nameIn.getName());
			name.setTypicalGender(nameIn.getTypicalGender());
			name.setOrigin(nameIn.getOrigin());
			name.setMeaning(nameIn.getMeaning());
			return nameRepository.save(name);
		} else {
			return null;
		}
	}
	
	public void deleteName(Long id) {
		Optional<Name> optionalName = nameRepository.findById(id);
		if(optionalName.isPresent()) {
			nameRepository.delete(optionalName.get());
		}
	}
	
	public boolean didVote(Long name_id, Long user_id) {
		Name name = findName(name_id);
		User user = userService.findUser(user_id);
		if(name.getVotedUsers().contains(user)) {
			return true;
		}
		return false;
	}
	
	public void upvote(Long name_id, Long user_id) {
		Name name = findName(name_id);
		User user = userService.findUser(user_id);
		if(!name.getVotedUsers().contains(user)) {
			name.getVotedUsers().add(user);
		}
		nameRepository.save(name);
	}
	
	public void removeVote(Long name_id, Long user_id) {
		Name name = findName(name_id);
		User user = userService.findUser(user_id);
		if(name.getVotedUsers().contains(user)) {
			name.getVotedUsers().remove(user);
		}
		nameRepository.save(name);
	}
}
