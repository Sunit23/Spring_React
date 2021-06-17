package com.tortuga.security.governance.platform.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tortuga.security.governance.platform.models.User;
import com.tortuga.security.governance.platform.models.Role;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	List<User> findByRoles(Set<Role> role);

	@Query("{'roles.$id' : ObjectId(?0)}")
	List<User> findByRoles(String roleId); 
}
