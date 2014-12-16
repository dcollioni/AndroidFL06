package com.dcollioni.contatosbd;

import java.util.ArrayList;

import com.db4o.ObjectSet;

/*
 *	Classe que conecta com o banco de dados DB4O
 *	e fornece métodos para lidar com Contatos
 * 
*/
public class ContatoDao {
	private Db4oHelper db4o;
	
	public ContatoDao (Db4oHelper db4o) {
		this.db4o = db4o;
	}
	
	// Incluir um contato no banco
	public void incluirContato(Contato c) {
		db4o.db().store(c);
	}
	
	// Atualiza um contato no banco
	public void atualizarContato(Contato c, long id) {
		Contato cAntigo = (Contato)db4o.db().ext().getByID(id);
		cAntigo.setNome(c.getNome());
		cAntigo.setTelefone(c.getTelefone());
		
		db4o.db().store(cAntigo);
	}
	
	// Exclui um contato do banco
	public void excluirContato(long id) {
		Contato c = (Contato)db4o.db().ext().getByID(id);
		db4o.db().delete(c);
	}
	
	// Obtém um contato do banco pelo id
	public Contato obterContato(long id) {
		Contato c = (Contato)db4o.db().ext().getByID(id);
		db4o.db().ext().activate(c);
		
		return c;
	}
	
	// Obtém o id de um contato do banco
	public long obterId(Contato c) {
		long id = db4o.db().ext().getID(c);
		
		return id;
	}
	
	// Busca todos os contatos do banco
	public ArrayList<Contato> listarContatos() {
		ArrayList<Contato> listaContatos = new ArrayList<Contato>();
		
		ObjectSet<Contato> contatos = db4o.db().query(Contato.class);
		
		listaContatos.addAll(contatos);
		
		return listaContatos;
	}
}