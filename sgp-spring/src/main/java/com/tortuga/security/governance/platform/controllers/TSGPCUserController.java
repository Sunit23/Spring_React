package com.tortuga.security.governance.platform.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.payload.request.RoleCreateRequest;
import com.tortuga.security.governance.platform.payload.request.UserRequest;
import com.tortuga.security.governance.platform.service.RoleService;
import com.tortuga.security.governance.platform.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class TSGPCUserController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;

	
	@GetMapping("/GetAllUser")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUser(){
		return userService.getAll();
	}
	
	@GetMapping("/GetUserDetails")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserDetails(@Valid @RequestBody UserRequest user) {
		return userService.getUserDetails(user.getUsername());
	}
	
	@PostMapping("/FindUsersInRole")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> FindUsersInRole(@Valid @RequestBody RoleCreateRequest role) {
		return userService.FindUsersInRole(role.getName());
	}
	
	@DeleteMapping("/deleteUser")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@Valid @RequestBody UserRequest userDelete) {
		return userService.deleteUser(userDelete.getUsername());
	}
	
	@PutMapping("/deactiveUser")
	@PreAuthorize("hasRole('ADMIN')")
	public User changeStatusToDeactivate(@Valid @RequestBody UserRequest user) {
		return userService.changeStatusToDeactivate(user.getUsername());
	}
	
	@PutMapping("/activateUser")
	@PreAuthorize("hasRole('ADMIN')")
	public User changeStatusToActivate(@Valid @RequestBody UserRequest user) {
		return userService.changeStatusToActivate(user.getUsername());
	}
	
}
