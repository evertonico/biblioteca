package com.example.biblioteca.service;

import java.util.Optional;

import com.example.biblioteca.model.entity.Usuario;

public interface UsuarioService {

	Optional<Usuario> obterPorId(Long id);
	
	Optional<Usuario> obterPorLogin(String login);
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
}