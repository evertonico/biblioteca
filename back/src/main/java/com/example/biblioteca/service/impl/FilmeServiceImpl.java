package com.example.biblioteca.service.impl;

import org.springframework.stereotype.Service;

import com.example.biblioteca.model.entity.Filme;
import com.example.biblioteca.model.repository.FilmeRepository;
import com.example.biblioteca.service.FilmeService;

@Service
public class FilmeServiceImpl implements FilmeService {
	
	private FilmeRepository filmeRepository;

	@Override
	public Filme salvarFilme(Filme filme) {
		return filmeRepository.save(filme);
	}

}
