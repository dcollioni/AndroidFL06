package br.edu.qi.spinners;

public class Estado {
	private String nome;
	private String sigla;
	private String capital;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public Estado(String nome, String sigla, String capital) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.capital = capital;
	}
	
	@Override
	public String toString() {
		return nome; 
	}
}
