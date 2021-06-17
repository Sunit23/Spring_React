package com.tortuga.security.governance.platform.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

/**
* The TSGPCRoleController program implements an application that
* Verify different Operation with role
*
* @author  Tortuga
* @version 1.0
*/
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.models.Role;
import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.payload.request.RoleCreateRequest;
import com.tortuga.security.governance.platform.payload.request.RoleUpdateRequest;
import com.tortuga.security.governance.platform.payload.response.MessageResponse;
import com.tortuga.security.governance.platform.service.RoleService;
import com.tortuga.security.governance.platform.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class TSGPCRoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	
	
 
	@GetMapping("/GetAllRoles")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Role> getAllRoles() {
		return roleService.getAll();
	}
	

	
	@PostMapping("/createRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> CreateRole(@Valid @RequestBody RoleCreateRequest role) {
		System.out.println("Role :: "+role.getName());
		Role rl = new Role(role.getName());
		if (roleService.existsByName(rl)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Role is already exist!"));
		}
	return ResponseEntity.ok(roleService.createRole(rl));
	}
	
	/*
	 * @PutMapping("/updateRole") public Role UpdateRole(@Valid @RequestBody
	 * RoleUpdateRequest role) {
	 * 
	 * return roleService.updateRole(role.getOldRoleName(), role.getNewRoleName());
	 * 
	 * }
	 */
	
	@DeleteMapping("/deleteRole")
	@PreAuthorize("hasRole('ADMIN')")
	public String DeleteRole(@Valid @RequestBody RoleCreateRequest role) {
		Role rl = new Role(role.getName());
	  //  roleService.deleteRole(rl);
	 return roleService.deleteRole(rl);
	}
	
	

	
}
