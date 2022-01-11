package com.example.biblioteca.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.exceptions.ErroAutenticacao;
import com.example.biblioteca.exceptions.RegraNegocioException;
import com.example.biblioteca.model.entity.Usuario;
import com.example.biblioteca.model.repository.UsuarioRepository;
import com.example.biblioteca.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario autenticar(String login, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
		
		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado");
		}
		
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha não confere");
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = usuarioRepository.existsByEmail(email);
		
		if (existe) {
			throw new RegraNegocioException("Ja Existe um usuário cadastrado com esse email");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

}
