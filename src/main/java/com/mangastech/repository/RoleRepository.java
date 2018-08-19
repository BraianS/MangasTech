package com.mangastech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Role;

/**
 * @author Braian
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {	
	
}
