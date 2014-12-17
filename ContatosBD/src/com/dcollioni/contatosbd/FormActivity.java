package com.dcollioni.contatosbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends Activity {

	// declara os componentes da tela
	EditText etNome, etTelefone;
	Button btSalvar;
	
	// declara os objetos do banco
	Db4oHelper db4o;
	ContatoDao contatoDao;
	
	// declara uma variável para receber o id do contato
	// (quando for uma edição)
	long contatoId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		
		// pega a intent que abriu essa activity
		Intent i = getIntent();
		// pega o id do contato recebido (se for edição) ou o valor zero (se for inclusão)
		contatoId = i.getLongExtra("id_contato", 0);
		
		carregarComponentes();
		configurarDb4o();
		configurarBtSalvar();
	}
	
	// carrega os componentes da tela pelo id
	private void carregarComponentes() {
		etNome = (EditText) findViewById(R.id.et_nome);
		etTelefone = (EditText) findViewById(R.id.et_telefone);
		btSalvar = (Button) findViewById(R.id.bt_salvar);
	}
	
	// configura a conexão com o banco de dados
	private void configurarDb4o() {
		// pega o diretório "data" do aplicativo
    	String dir = getDir("data", 0) + "/";
    	// cria um novo objeto db4oHelper
        db4o = new Db4oHelper(dir);
        // cria um novo objeto contatoDao passando o db4oHelper
        contatoDao = new ContatoDao(db4o);
	}
	
	// configura o botão Salvar
	private void configurarBtSalvar() {
		
		// se o id recebido é maior do que zero
		if (contatoId > 0) {
			// altera o texto do botão para "Atualizar"
			btSalvar.setText(getString(R.string.atualizar));
		}
		
		// adiciona o evento de click no botão
		btSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// pega os valores dos campos da tela
				String nome = etNome.getText().toString();
				String telefone = etTelefone.getText().toString();
				
				// cria um novo objeto Contato com os valores
				Contato c = new Contato(nome, telefone);
				
				// se o id do contato é zero (inclusão)
				if (contatoId == 0) {
					// inclui o contato no banco
					contatoDao.incluirContato(c);
				}
				// senão (alteração)
				else {
					// atualiza o contato no banco
					contatoDao.atualizarContato(c, contatoId);
				}
				
				// finaliza a FormActivity
				finish();
			}
		});
	}
	
	// carrega um contato pelo id recebido
	private void carregarContato() {
		
		// pega o contato pelo id recebido
		Contato c = contatoDao.obterContato(contatoId);
		
		// atualiza os campos com os valores do contato
		etNome.setText(c.getNome());
		etTelefone.setText(c.getTelefone());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// abre a conexão com o banco
		db4o.abrirConexao();
		
		// se foi recebido o ID de um contato
		if (contatoId > 0) {
			// carrega as informações do contato recebido
			carregarContato();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// fecha a conexão com o banco
		db4o.fecharConexao();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
