package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import com.example.biblioteca.model.entity.Album;

public interface AlbumService {
	
	Album salvarAlbum(Album album);
	
	Album atualizarAlbum(Album album);
	
	Optional<Album> obterPorId(Long id);

	void deletar(Album album);

	List<Album> buscar(Album filtro);

}
