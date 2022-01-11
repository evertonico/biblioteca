package com.example.biblioteca.api.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.api.dto.FilmeDTO;
import com.example.biblioteca.exceptions.RegraNegocioException;
import com.example.biblioteca.model.entity.Filme;
import com.example.biblioteca.model.entity.Usuario;
import com.example.biblioteca.service.FilmeService;
import com.example.biblioteca.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/filmes")
@RequiredArgsConstructor
public class FilmeResource {
	
	private final FilmeService service;
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody FilmeDTO dto) {
		Filme filme = converter(dto);
		
		try {
			Filme filmeSalvo = service.salvarFilme(filme);

			return new ResponseEntity(filmeSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id,	@RequestBody FilmeDTO dto ) {

		return service.obterPorId(id).map( entity -> {
			Filme filme = converter(dto);
			filme.setId(entity.getId());
			try {
				service.atualizarFilme(filme);
				return ResponseEntity.ok(filme);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet( () -> new ResponseEntity("Filme não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar (@PathVariable("id") Long id) {
		return service.obterPorId(id).map( entity -> {
			try {

				service.deletar(entity);
				return new ResponseEntity(HttpStatus.NO_CONTENT);

			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet( () -> new ResponseEntity("Filme não encontrado na base de dados", HttpStatus.BAD_REQUEST));

	}	
	
	@GetMapping
	public ResponseEntity buscar (
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "titulo", required = false) String titulo,
			@RequestParam("usuario") Long idUsuario
			) {

		Filme filtro = new Filme() ;
		filtro.setDescricao(descricao);
		filtro.setTitulo(titulo);
		
		Optional <Usuario> usuario = usuarioService.obterPorId(idUsuario);

		if (!usuario.isPresent() ) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado");
		} else {
			filtro.setUsuario(usuario.get());
		}

		List<Filme> lancamentos = service.buscar(filtro);

		return ResponseEntity.ok(lancamentos);
	}
	
	
	private Filme converter(FilmeDTO dto) {
		Filme filme = new Filme();

		filme.setId(dto.getId());
		filme.setDescricao(dto.getDescricao());
		filme.setAvaliacao(dto.getAvaliacao());
		filme.setFoto(dto.getFoto());
		filme.setTitulo(dto.getTitulo());
		
		Usuario usuario = usuarioService
				.obterPorId(dto.getUsuario())
				.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado"));

		filme.setUsuario(usuario);

		return filme;
	}

}
