package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import com.example.biblioteca.model.entity.Filme;

public interface FilmeService {
	
	Filme salvarFilme(Filme filme);
	
	Filme atualizarFilme(Filme filme);
	
	Optional<Filme> obterPorId(Long id);

	void deletar(Filme filme);

	List<Filme> buscar(Filme filtro);

}
