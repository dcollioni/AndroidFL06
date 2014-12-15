package br.edu.qi.contatos;

import java.io.Serializable;

public class Contato implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Contato(String nome, String telefone) {
		super();
		this.nome = nome;
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
