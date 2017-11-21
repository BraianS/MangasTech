package com.mangastech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GenerosEntity;

@Repository
public interface GeneroRepository extends JpaRepository<GenerosEntity, Long>{

}
