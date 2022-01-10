package com.example.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.model.entity.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{

}
