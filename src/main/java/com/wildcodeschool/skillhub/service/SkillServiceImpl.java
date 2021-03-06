package com.wildcodeschool.skillhub.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.Skill;
import com.wildcodeschool.skillhub.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

	private final SkillRepository skillRepository;

	@Autowired
	public SkillServiceImpl(SkillRepository skillRepository) {
		super();
		this.skillRepository = skillRepository;
	}

	@Override
	public List<Skill> getAllSkills() {
		List<Skill> skills = skillRepository.findAll();

		skills.sort(Comparator.comparing(Skill::getName, Comparator.nullsLast(Comparator.naturalOrder())));

		return skills;
	}

	@Override
	public Optional<Skill> getSingleSkillById(Long skillId) {
		return skillRepository.findById(skillId);
	}

	@Override
	public Skill createNewSkill(Skill skill) {

		// Check if a new skill has been passed i.e. id of skill is null
		if (skill.getId() != null) {

			// TODO Find a better exception
			throw new IllegalArgumentException();
		}
		return skillRepository.save(skill);
	}

}
