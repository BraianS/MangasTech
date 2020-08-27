package com.mangastech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Role;
import com.mangastech.model.RoleNome;

/**
 * @author Braian
 *
 */
@Repository
public interface RoleRepository extends BaseRepository<Role>,JpaRepository<Role,Long>{
    Optional<Role> findByNome(RoleNome roleNome);
}