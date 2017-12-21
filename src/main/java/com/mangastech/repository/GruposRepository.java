package com.mangastech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GruposEntity;

@Repository
public interface GruposRepository extends JpaRepository<GruposEntity, Long>{

}
