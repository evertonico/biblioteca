package com.example.biblioteca.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.api.dto.FilmeDTO;
import com.example.biblioteca.exceptions.RegraNegocioException;
import com.example.biblioteca.model.entity.Filme;
import com.example.biblioteca.service.FilmeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/filmes")
@RequiredArgsConstructor
public class FilmeResource {
	
	private final FilmeService service;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody FilmeDTO dto) {
		Filme filme = Filme.builder()
				.titulo(dto.getTitulo())
				.foto(dto.getFoto())
				.descricao(dto.getDescricao())
				.avaliacao(dto.getAvaliacao()).build();
		
		try {
			Filme filmeSalvo = service.salvarFilme(filme);

			return new ResponseEntity(filmeSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
