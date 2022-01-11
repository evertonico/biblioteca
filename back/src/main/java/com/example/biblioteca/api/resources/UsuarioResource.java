package com.example.biblioteca.api.resources;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.api.dto.UsuarioDTO;
import com.example.biblioteca.exceptions.ErroAutenticacao;
import com.example.biblioteca.exceptions.RegraNegocioException;
import com.example.biblioteca.model.entity.Usuario;
import com.example.biblioteca.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsuarioResource {
	
	private final UsuarioService usuarioService;
	
	@PostMapping("/signup")
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = converter(dto);
		
		try {
			Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);

			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getLogin(), dto.getSenha());
			
			return ResponseEntity.ok(usuarioAutenticado);
			
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}	
	
	@GetMapping("/exists/{login}")
	public ResponseEntity recuperaUsuario(@PathVariable ("login") String login) {
		Optional<Usuario> usuario = usuarioService.obterPorLogin(login);
		
		if (!usuario.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(usuario);
	}	
	
	@GetMapping("/exists/{login}")
	public ResponseEntity buscaPorID(@PathVariable ("id") Long id) {
		Optional<Usuario> usuario = usuarioService.obterPorId(id);
		
		if (!usuario.isPresent()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(usuario);
	}	
	
	private Usuario converter(UsuarioDTO dto) {
		Usuario usuario = new Usuario();

		usuario.setId(dto.getId());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setLogin(dto.getLogin());
		usuario.setSenha(dto.getSenha());
		
		return usuario;
	}

}
