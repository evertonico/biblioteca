package com.example.biblioteca.service;

import java.util.Optional;

import com.example.biblioteca.model.entity.Usuario;

public interface UsuarioService {

	Optional<Usuario> obterPorId(Long id);
}