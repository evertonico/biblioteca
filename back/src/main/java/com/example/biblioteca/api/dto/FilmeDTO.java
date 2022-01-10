package com.example.biblioteca.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilmeDTO {
	
	private String titulo;
	private String foto;
	private String descricao;
	private String avaliacao;

}
