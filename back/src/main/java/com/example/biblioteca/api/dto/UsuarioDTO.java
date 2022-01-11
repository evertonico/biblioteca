package com.example.biblioteca.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;

}
