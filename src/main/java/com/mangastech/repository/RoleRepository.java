package com.mangastech.repository;

import com.mangastech.model.Role;
import com.mangastech.model.RoleNome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role>,JpaRepository<Role,Long>{
    Role findByNome(RoleNome roleNome);
}