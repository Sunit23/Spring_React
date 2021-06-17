package com.tortuga.security.governance.platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tortuga.security.governance.platform.models.Role;
import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.payload.response.MessageResponse;
import com.tortuga.security.governance.platform.repository.RoleRepository;
import com.tortuga.security.governance.platform.repository.UserRepository;

@Service
public class RoleService {
	
 @Autowired	
 private RoleRepository roleRepository;
 
 @Autowired
 private UserRepository userRepository;
 
//Retrieve operation
 public List<Role> getAll(){
	return roleRepository.findAll();
 }

public Role createRole(Role role) {
	
	return roleRepository.save(role);
}

public boolean existsByName(Role rl) {
	return roleRepository.existsByName(rl.getName());
}

public String deleteRole(Role role) {
	System.out.println("Role Name :: "+role.getName());
	Role rl = roleRepository.findByName(role.getName())
			.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

	boolean user = userRepository.findByRoles(rl.getId()).isEmpty();
	
	if(user) {
		roleRepository.deleteById(rl.getId());
		return "Role "+rl.getName()+" deleted successfully";
	}else {
		return "Role "+rl.getName()+" Not deleted due to user exist with role" +rl.getName();
	}
	
	
	
}

public Role updateRole(String oldRole,String newRole) {
	Role rl = roleRepository.findByName(oldRole)
			.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	// TODO Auto-generated method stub
	rl.setName(newRole);
	return roleRepository.save(rl);
}

}
