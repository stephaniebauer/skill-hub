package com.wildcodeschool.skillhub.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wildcodeschool.skillhub.repository.SkillRepository;
import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SkillRepository skillRepository;

	@GetMapping("/users/search")
	public String getBySkill(Model model, @RequestParam Long id) {
		model.addAttribute("users", userRepository.findBySkills_SkillId(id));

		Optional<Skill> optionalSkill = skillRepository.findById(id);
		if (optionalSkill.isPresent()) {
			model.addAttribute("skill", optionalSkill.get());
		}

//		List<Skill> skills = new ArrayList<>();
//
//		skills = skillRepository.findByName(skillName);
//
//		for (Skill skill : skills) {
//			model.addAttribute("users", userRepository.findBySkills_SkillId(skill.getId()));
//		}

		return "users";
	}
	
	@GetMapping("/users/users/search")
	public String getUserprofileBySkill(Model model, @RequestParam Long id) {
		
		Optional<Skill> optionalSkill = skillRepository.findById(id);
		if (optionalSkill.isPresent()) {
			model.addAttribute("skill", optionalSkill.get());
		}
		
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			model.addAttribute("user", optionalUser.get());
		}

		return "viewUser";
	}

	// Zeige Liste
	@GetMapping("/users/userslist")
	public String getAll(Model model) {

		model.addAttribute("see_created_users", userRepository.findAll());

		return "see_created_users";
	}

	// Editieren (Daten werden angezeigt...!
	// @GetMapping("/create")
	@GetMapping("/users/create")
	public String getUser(Model model, @RequestParam(required = false) Long id) {

		User user = new User();
		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}
		model.addAttribute("user", user);

		return "create_user";
	}

	// Editieren (Daten werden angezeigt...!
	@GetMapping("/create")
	// @GetMapping("/users/create")
	public String getUser2(Model model, @RequestParam(required = false) Long id) {

		User user = new User();
		if (id != null) {
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				user = optionalUser.get();
			}
		}
		model.addAttribute("user", user);

//	        return "create_user";
		return "redirect:/users/userslist";
	//Editieren (Daten werden angezeigt...!
	//@GetMapping("/create")
	@GetMapping("/users/create")
    public String getUser(Model model,
                            @RequestParam(required = false) Long id) {

        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        model.addAttribute("user", user);

        return "create_user";
    }
    
	
	//Editieren (Daten werden angezeigt...!
		@GetMapping("/create")
		//@GetMapping("/users/create")
	    public String getUser2(Model model,
	                            @RequestParam(required = false) Long id) {

	        User user = new User();
	        if (id != null) {
	            Optional<User> optionalUser = userRepository.findById(id);
	            if (optionalUser.isPresent()) {
	                user = optionalUser.get();
	            }
	        }
	        model.addAttribute("user", user);

	        return "create_user";        
	        
	    }
	    
	
	//Sende eingegebene Daten in Datenbank ("save")
    //@PostMapping("/create") --> falsch
    @PostMapping("/users/create")
    public String postUser(@ModelAttribute User user) {

    	userRepository.save(user);
        return "redirect:/users/userslist";	
    }
    
    
    
  //Delete one User
    @GetMapping("/create/delete")
    public String deleteUser(@RequestParam Long id) {

    	userRepository.deleteById(id);

        return "redirect:/users/userslist";
    }

	}

	// Sende eingegebene Daten in Datenbank ("save")
	// @PostMapping("/create") --> neu
	@PostMapping("/users/create")
	public String postUser(@ModelAttribute User user) {

		userRepository.save(user);
		return "redirect:/users/create";
	}

	/*
	 * alt
	 * 
	 * @GetMapping("/users/delete") public String deleteUser(@RequestParam Long id)
	 * {
	 * 
	 * userRepository.deleteById(id);
	 * 
	 * return "redirect:/see_created_users"; }
	 * 
	 */

	// Delete one User
	@GetMapping("/create/delete")
	public String deleteUser(@RequestParam Long id) {

		userRepository.deleteById(id);

		return "redirect:/users/userslist";
	}

}
