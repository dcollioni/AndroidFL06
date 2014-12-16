package com.dcollioni.contatosbd;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/*
 * Classe que abre e fecha a conexão com o banco
 * e oferece um objeto db para lidar com os objetos no banco
*/
public class Db4oHelper {
	private String dir;
	private final String DB4O_FILE = "bd.db4o";
	protected ObjectContainer db;
	
	public Db4oHelper(String dir) {
		this.dir = dir;
	}
	
	// Abre uma conexão com o banco de dados
	public void abrirConexao() {
		String dbFile = dir + DB4O_FILE;
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dbFile);
	}
	
	// Fecha uma conexão com o banco de dados
	public void fecharConexao() {
		if (db != null) {
			db.close();
		}
	}
	
	public ObjectContainer db() {
		return db;
	}
}
