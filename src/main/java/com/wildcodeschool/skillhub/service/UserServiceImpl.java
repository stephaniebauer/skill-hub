package com.wildcodeschool.skillhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildcodeschool.skillhub.model.User;
import com.wildcodeschool.skillhub.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> getSingleUser(Long userId) {
		// TODO Add checks
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> getSingleUserByEmail(String email) {
		// TODO Add checks
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getUsersBySkillId(Long skillId) {
		return userRepository.findByuserSkills_SkillId(skillId);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Add checks
		userRepository.deleteById(userId);
	}

	@Override
	public User createNewUser(User user) {
		// TODO Check if user exists
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Add checks etc.
		userRepository.save(user);
	}

	@Override
	public boolean emailExists(String email) {

		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			return true;
		}
		return false;
	}

}
