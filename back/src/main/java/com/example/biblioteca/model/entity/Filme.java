package com.example.biblioteca.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="filme", schema="biblioteca")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filme {
	
	@Id
	@Column(name="id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="foto")
	private String foto;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="avaliacao")
	private String avaliacao;	
	
}
