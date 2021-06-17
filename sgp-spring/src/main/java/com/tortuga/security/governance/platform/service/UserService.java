package com.tortuga.security.governance.platform.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.models.Role;
import com.tortuga.security.governance.platform.repository.RoleRepository;
import com.tortuga.security.governance.platform.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;




	public List<User> FindUsersInRole(String role) {
		Role rl = roleRepository.findByName(role)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		return userRepository.findByRoles(rl.getId());
	}


	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
	public User getUserDetails(String userName) {

		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
		return user;
	}

	public String deleteUser(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		userRepository.delete(user);
		return "UserName "+username+" deleted successfully";
	}


	public User changeStatusToDeactivate(String userName) {
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
		user.setActive(false);
		return userRepository.save(user);
	}

	public User changeStatusToActivate(String userName) {
		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));
		user.setActive(true);
		return userRepository.save(user);
	}
	
}
