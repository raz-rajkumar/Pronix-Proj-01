package com.prnx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prnx.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRoleName(String role);

}
