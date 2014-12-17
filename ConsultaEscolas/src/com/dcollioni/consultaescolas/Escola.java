package com.dcollioni.consultaescolas;

import java.io.Serializable;

public class Escola implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nome;
	private String telefone;
	private String endereco;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
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
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}