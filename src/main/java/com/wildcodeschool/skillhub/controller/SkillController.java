package com.wildcodeschool.skillhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wildcodeschool.skillhub.repository.SkillRepository;

@Controller
public class SkillController {

	@Autowired
	private SkillRepository skillRepository;

	@GetMapping("/skill")
	public String getAllSkills(Model model) {
		model.addAttribute("skills", skillRepository.findAll());
		return "get_all_skills";
	}
}
