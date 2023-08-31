package com.shopme.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.shopme.entity.Role;
import com.shopme.entity.User;

@Component
public class ObjectMapper {

	User userModelToEntity(com.shopme.model.User user) {

		User userNew = new User();
		HashSet<com.shopme.model.Role> roles = new HashSet<com.shopme.model.Role>();

		userNew.setId(user.getId());
		userNew.setEmail(user.getEmail());
		userNew.setFirstName(user.getFirstName() != null ? user.getFirstName() : "NA");
		userNew.setLastName(user.getLastName() != null ? user.getLastName() : "NA");
		user.getPhotos();
		Set<Role> roleList = user.getRoles();
		for (Role role : roleList) {
			com.shopme.model.Role newRole = new com.shopme.model.Role();
			newRole.setName(role.getName());
			newRole.setDescription(role.getDescription());
			roles.add(newRole);
		}

		return userNew;
	}

}
