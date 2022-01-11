package com.example.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
}
