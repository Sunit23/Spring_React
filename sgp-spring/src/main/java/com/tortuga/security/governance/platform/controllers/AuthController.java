package com.tortuga.security.governance.platform.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tortuga.security.governance.platform.models.Role;
import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.payload.request.LoginRequest;
import com.tortuga.security.governance.platform.payload.request.SignupRequest;
import com.tortuga.security.governance.platform.payload.response.JwtResponse;
import com.tortuga.security.governance.platform.payload.response.MessageResponse;
import com.tortuga.security.governance.platform.repository.RoleRepository;
import com.tortuga.security.governance.platform.repository.UserRepository;
import com.tortuga.security.governance.platform.security.jwt.JwtUtils;
import com.tortuga.security.governance.platform.security.services.UserDetailsImpl;


/**
* The AuthController program implements an application that
* simply signin and singup process
*
* @author  Tortuga
* @version 1.0
*/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

   /**
   * This method is used to signin in application.
   * @param LoginRequest This is content username and password
   * @return ResponseEntity This returns JwtResponse with userid, token etc.
   */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		User user = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));
		
		if (!user.isActive()) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is not active. Please contact to Admin!"));
		}
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	/**
	   * This method is used to signup in application.
	   * @param SignupRequest This is content username information
	   * @return Message This returns user registered successfully.
	*/
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			new RuntimeException("Error: Role is not found.");
		} else {
			strRoles.forEach(role -> {
				
					Role adminRole = roleRepository.findByName(role)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

			});
		}

		user.setRoles(roles);
		user.setActive(true);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
