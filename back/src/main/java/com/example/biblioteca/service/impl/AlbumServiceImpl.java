package com.example.biblioteca.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.example.biblioteca.model.entity.Album;
import com.example.biblioteca.model.repository.AlbumRepository;
import com.example.biblioteca.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

	private AlbumRepository albumRepository;

	@Override
	public Album salvarAlbum(Album album) {
		return albumRepository.save(album);
	}

	@Override
	public Optional<Album> obterPorId(Long id) {
		return albumRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Album atualizarAlbum(Album album) {
		Objects.requireNonNull(album.getId());
		return albumRepository.save(album);
	}

	@Override
	@Transactional	
	public void deletar(Album album) {
		Objects.requireNonNull(album.getId());
		albumRepository.delete(album);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Album> buscar(Album album) {
		Example example = Example.of(album, ExampleMatcher.matching()
													.withIgnoreCase()
													.withStringMatcher(StringMatcher.CONTAINING));
		
		
		
		
		return albumRepository.findAll(example);
	}

}
