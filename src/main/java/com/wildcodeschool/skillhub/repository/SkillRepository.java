package com.wildcodeschool.skillhub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.skillhub.model.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

	List<Skill> findAll();

	Optional<Skill> findById(Long id);

	Optional<Skill> findByName(String name);

}
