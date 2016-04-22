package br.com.avaliacao.bean;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private Integer id;
	private String nome;
	private String senha;
	private List<Contato> contatos = new ArrayList<Contato>();

	public Usuario(Integer id, String nome, String senha) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void addContatos(Contato contato) {
		this.contatos.add(contato);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
