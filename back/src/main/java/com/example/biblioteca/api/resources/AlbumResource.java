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

import com.example.biblioteca.api.dto.AlbumDTO;
import com.example.biblioteca.exceptions.RegraNegocioException;
import com.example.biblioteca.model.entity.Album;
import com.example.biblioteca.model.entity.Usuario;
import com.example.biblioteca.service.AlbumService;
import com.example.biblioteca.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/albuns")
@RequiredArgsConstructor
public class AlbumResource {
	
	private final AlbumService service;
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody AlbumDTO dto) {
		Album album = converter(dto);
		
		try {
			Album albumSalvo = service.salvarAlbum(album);

			return new ResponseEntity(albumSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id,	@RequestBody AlbumDTO dto ) {

		return service.obterPorId(id).map( entity -> {
			Album album = converter(dto);
			album.setId(entity.getId());
			try {
				service.atualizarAlbum(album);
				return ResponseEntity.ok(album);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}).orElseGet( () -> new ResponseEntity("Album não encontrado na base de dados", HttpStatus.BAD_REQUEST));
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

		}).orElseGet( () -> new ResponseEntity("Album não encontrado na base de dados", HttpStatus.BAD_REQUEST));

	}	
	
	@GetMapping
	public ResponseEntity buscar (
			@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "titulo", required = false) String titulo,
			@RequestParam("usuario") Long idUsuario
			) {

		Album filtro = new Album() ;
		filtro.setDescricao(descricao);
		filtro.setTitulo(titulo);
		
		Optional <Usuario> usuario = usuarioService.obterPorId(idUsuario);

		if (!usuario.isPresent() ) {
			return ResponseEntity.badRequest().body("Não foi possível realizar a consulta. Usuário não encontrado");
		} else {
			filtro.setUsuario(usuario.get());
		}

		List<Album> albuns = service.buscar(filtro);

		return ResponseEntity.ok(albuns);
	}
	
	
	private Album converter(AlbumDTO dto) {
		Album album = new Album();

		album.setId(dto.getId());
		album.setDescricao(dto.getDescricao());
		album.setAvaliacao(dto.getAvaliacao());
		album.setFoto(dto.getFoto());
		album.setTitulo(dto.getTitulo());
		
		Usuario usuario = usuarioService
				.obterPorId(dto.getUsuario())
				.orElseThrow( () -> new RegraNegocioException("Usuário não encontrado para o Id informado"));

		album.setUsuario(usuario);

		return album;
	}

}
