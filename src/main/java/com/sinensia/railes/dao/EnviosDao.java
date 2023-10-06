package com.sinensia.railes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinensia.railes.model.Envio;

public interface EnviosDao extends JpaRepository<Envio, Integer>{
    
}
