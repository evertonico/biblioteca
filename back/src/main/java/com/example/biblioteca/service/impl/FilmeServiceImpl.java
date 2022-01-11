package com.example.biblioteca.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

	@Override
	public Optional<Filme> obterPorId(Long id) {
		return filmeRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Filme atualizarFilme(Filme filme) {
		Objects.requireNonNull(filme.getId());
		return filmeRepository.save(filme);
	}

	@Override
	@Transactional	
	public void deletar(Filme filme) {
		Objects.requireNonNull(filme.getId());
		filmeRepository.delete(filme);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Filme> buscar(Filme filme) {
		Example example = Example.of(filme, ExampleMatcher.matching()
													.withIgnoreCase()
													.withStringMatcher(StringMatcher.CONTAINING));
		
		
		
		
		return filmeRepository.findAll(example);
	}

}
