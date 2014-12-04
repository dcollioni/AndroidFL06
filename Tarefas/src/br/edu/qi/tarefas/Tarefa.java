package br.edu.qi.tarefas;

public class Tarefa {
	private String descricao;
	private String prioridade;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	public Tarefa(String descricao, String prioridade) {
		super();
		this.descricao = descricao;
		this.prioridade = prioridade;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
}
