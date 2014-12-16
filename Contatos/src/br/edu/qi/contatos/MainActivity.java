package br.edu.qi.contatos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	// declara os componentes
	EditText etNome, etTelefone;
	Button btAdicionar;
	ListView lvContatos;
	
	ArrayList<Contato> arrayContatos = null;
	ArrayAdapter<Contato> adapterContatos;
	
	int posicaoSelecionada;
	
	// método que carrega os componentes pelo id
	private void carregarComponentes() {
		etNome = (EditText) findViewById(R.id.et_nome);
		etTelefone = (EditText) findViewById(R.id.et_telefone);
		btAdicionar = (Button) findViewById(R.id.bt_adicionar);
		lvContatos = (ListView) findViewById(R.id.lv_contatos);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // chama o método para carregar os componentes
        carregarComponentes();
        
        // chama o método que configura a lista
        configurarLvContatos();
        
        // chama o método que configura o botão
        configurarBtAdicionar();
        
        // registra um menu de contexto para a lista
        registerForContextMenu(lvContatos);
        
    } // fecha onCreate

    // método que adicionar o evento click no botão
    private void configurarBtAdicionar() {
    	
    	// adiciona o evento de click no botão
    	btAdicionar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				// pega o texto do campo nome
				String nome = etNome.getText().toString();
				
				// pega o texto do campo telefone
				String telefone = etTelefone.getText().toString();
				
				// cria um contato com os valores
				Contato c = new Contato(nome, telefone);
				
				// adiciona o contato na lista
				arrayContatos.add(c);
				
				// notifica o adapter da alteração
				adapterContatos.notifyDataSetChanged();
				
			} // fecha onClick
		}); // fecha OnClickListener
    	
    } // fecha configurarBtAdicionar 
    
    // método que inicializa o array e o adapter
    private void configurarLvContatos() {
    	
    	if (arrayContatos == null) {
    		// instancia o array de contatos
    		arrayContatos = new ArrayList<Contato>();
    		
    		// instancia o adapter de contatos
        	adapterContatos = new ArrayAdapter<Contato>(
        							getBaseContext(),
        							android.R.layout.simple_list_item_1,
        							arrayContatos);
    	}
    	
    	// configura a lista para utilizar o adapter
    	lvContatos.setAdapter(adapterContatos);
    	
    	// adiciona evento item click na lista
    	lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega o objeto contato clicado
				Contato c = (Contato)
						parent.getItemAtPosition(position);
				
				// cria uma intent para abrir outra activity
				Intent intent = new Intent(
									MainActivity.this,
									DetalheActivity.class);
				
				// passa o contato para a intent
				intent.putExtra("contato", c);
				
				// abre a intent
				startActivity(intent);
				
			} // fecha onItemClick
		}); // fecha OnItemClickListener
    	
    	// adiciona o evento de long click no item da lista
    	lvContatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// passo a posição para a var global
				posicaoSelecionada = position;
				
				return false;
			}
		});
    	
    } // fecha configurarLvContatos

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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	// testa se a view é a lista de contatos
    	if (v.getId() == R.id.lv_contatos) {
    		// infla o menu correto para a lista
    		getMenuInflater().inflate(R.menu.lista_menu, menu);
    	}
    	
    } // fecha onCreateContextMenu
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	// pega o id do item selecionado
    	int id = item.getItemId();
    	
    	// se o id é o id do item "Ligar"
    	if (id == R.id.action_ligar) {
    		// mostra uma mensagem "Ligando..."
    		Toast.makeText(
    				getBaseContext(),
    				R.string.ligando,
    				Toast.LENGTH_SHORT).show();
    	}
    	// senão, se for o id do item "Excluir"
    	else if (id == R.id.action_excluir) {
    		// cria o alert de confirmação
    		AlertDialog alert = new AlertDialog
    									.Builder(MainActivity.this)
    									.create();
    		
    		// configura o título do alert
    		alert.setTitle(getString(R.string.confirmacao));
    		
    		// configura a mensagem do alert
    		alert.setMessage(getString(R.string.confirma_exclusao));
    		
    		// adiciona o botão negativo
    		alert.setButton(AlertDialog.BUTTON_NEGATIVE,
    						getString(R.string.nao),
    						new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
								}
							});

    		// adiciona o botão positivo
    		alert.setButton(AlertDialog.BUTTON_POSITIVE,
    						getString(R.string.sim),
    						new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
									// remove o item na posição selecionada
						    		arrayContatos.remove(posicaoSelecionada);

						    		// notifica o adapter que os dados alteraram
						    		adapterContatos.notifyDataSetChanged();
								}
							});
    		
    		// mostra o alert
    		alert.show();
    	}
    	
    	return super.onContextItemSelected(item);
    }
}
