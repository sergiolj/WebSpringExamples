package br.edu.ucsal.sergiolj.contatos.dto;

import br.edu.ucsal.sergiolj.contatos.model.Contato;

public class ContatoDto {

	 public Long id;
	 public String nome;
	 public String email;
	 public boolean marcado;
	 
	 public ContatoDto() {}

	 public ContatoDto(Long id, String nome, String email, boolean marcado) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.marcado = marcado;
	 }
	 
	 public ContatoDto(Contato contato) {
		 this.id = contato.getId();
		 this.nome = contato.getNome();
		 this.email = contato.getEmail().toString();
		 this.marcado = contato.isMarcado();
	 }
	 
	 
}
