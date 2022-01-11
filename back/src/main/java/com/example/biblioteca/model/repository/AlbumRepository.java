package com.example.biblioteca.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.model.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{

}
