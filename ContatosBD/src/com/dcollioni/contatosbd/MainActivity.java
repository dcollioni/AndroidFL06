package com.dcollioni.contatosbd;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	// declara os objetos do banco
	Db4oHelper db4o;
	ContatoDao contatoDao;
	
	// declara os componentes da lista
	ListView lvContatos;
	ArrayList<Contato> arrayContatos;
	ArrayAdapter<Contato> adapterContatos;
	
	// declara uma variável para controlar o id do contato selecionado na lista
	long contatoIdSelecionado;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        configurarDb4o();
        carregarComponentes();
        configurarLvContatos();
    }
    
    // carrega os componentes da tela pelo id
    private void carregarComponentes() {
    	lvContatos = (ListView) findViewById(R.id.lv_contatos);
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
    
    // configura a lista de contatos
    private void configurarLvContatos() {
    	// cria um novo array de contatos
    	arrayContatos = new ArrayList<Contato>();
    	
    	// cria um novo adapter de contatos passando o array criado
    	adapterContatos = new ArrayAdapter<Contato>(getBaseContext(), android.R.layout.simple_list_item_1, arrayContatos);
    	
    	// configura o adapter na lista
    	lvContatos.setAdapter(adapterContatos);
    	
    	lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				/*	TODO:
				*	Pegar o ID do contato selecionado
				*	e abrir a FormActivity passando esse ID
				*/
			}
		});
    	
    	lvContatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				/* 	TODO:
				*	Pegar o ID do contato selecionado
				* 	e salvar na variável contatoIdSelecionado
				*/
				
				return false;
			}
		});
    	
    	registerForContextMenu(lvContatos);
    }
    
    // carrega os contatos do banco e mostra na lista
    private void carregarLvContatos() {
    	/*	TODO:
    	*	1. Limpar o array de contatos atual
    	*	2. Pegar os contatos do banco usando o objeto contatoDao
    	*	3. Atualizar o array de contatos com o resultado da busca
    	*	4. Notificar o adapter sobre a alteração
    	*/
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	// abre a conexão com o banco
    	db4o.abrirConexao();
    	// carrega os contatos do banco
    	carregarLvContatos();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // se clicar no menu "Incluir contato"
        if (id == R.id.incluir_contato) {
            
        	// cria uma intent e abre a tela de cadastro
        	Intent i = new Intent(MainActivity.this, FormActivity.class);
        	startActivity(i);
        	
        	return true;
        	
        }
        // senão, se clicar no menu "Excluir todos"
        else if (id == R.id.excluir_contatos) {
        	/*	TODO:
        	*	1. Passar por todos os itens do array de contatos
        	*	2. Pegar o ID de cada item do array
        	*	3. Chamar o método de excluir contato passando o ID
        	*	4. Recarregar a lista de contatos 
        	*/
        	
        	return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	if (v.getId() == R.id.lv_contatos) {
			getMenuInflater().inflate(R.menu.lista_contatos, menu);
		}
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	int id = item.getItemId();
    	
    	// se clicar no menu de contexto "Excluir"
    	if (id == R.id.excluir_contato) {
    		// se existe um contato selecionado
    		if (contatoIdSelecionado > 0) {
    			// cria um Alert de confirmação de exclusão
    			AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
    													.setNegativeButton(R.string.nao, null)
    													.create();
    			
    			alert.setTitle(getString(R.string.confirmacao));
    			alert.setMessage(getString(R.string.confirma_exclusao));
    			
    			alert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.sim), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						/*	TODO:
						* 	1. Excluir o contato passando o Id selecionado
						* 	2. Recarregar a lista de contatos
						*/
						
					}
				});
    			alert.show();
    		}
		}
    	
    	return super.onContextItemSelected(item);
    }
}
