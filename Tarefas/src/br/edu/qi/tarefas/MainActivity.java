package br.edu.qi.tarefas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

	// declara os componentes da tela
	ListView lvTarefas;
	EditText etDescricao;
	Spinner spPrioridade;
	Button btAdicionar;
	
	// declara objetos para configurar a listview
	ArrayList<Tarefa> tarefasArray;
	//ArrayAdapter<Tarefa> tarefasAdapter; - adapter simples
	TarefaAdapter tarefasAdapter;
	
	// método para carregar todos os componentes
	private void carregarComponentes() {
		
		lvTarefas = (ListView) findViewById(R.id.lv_tarefas);
		etDescricao = (EditText) findViewById(R.id.et_descricao);
		spPrioridade = (Spinner) findViewById(R.id.sp_prioridade);
		btAdicionar = (Button) findViewById(R.id.bt_adicionar);
		
	} // fecha carregarComponentes
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // chama o método para carregar os componentes
        carregarComponentes();
        
        // chama o método para configura a listview
        configurarLvTarefas();
        
        // chama o método para configurar o botão
        configurarBtAdicionar();
    } // fecha onCreate

    // método para configurar o botão adicionar
    private void configurarBtAdicionar() {
    	
    	// adiciona o evento click no botão
    	btAdicionar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// pega o valor do campo descrição
				String descricao = etDescricao.getText().toString();
				
				// pega o valor do spinner prioridade
				String prioridade = spPrioridade.getSelectedItem().toString();
				
				// cria um objeto tarefa com os valores
				Tarefa tarefa = new Tarefa(descricao, prioridade);
				
				// adiciona a tarefa no array de tarefas
				tarefasArray.add(tarefa);
				
				// avisa o adapter que os dados mudaram
				tarefasAdapter.notifyDataSetChanged();
				
			} // fecha onClick
		}); // fecha setOnClickListener
    	
    } // fecha configurarBtAdicionar
    
    // método para configuras a list view de tarefas
    private void configurarLvTarefas() {
    	
    	// cria um novo array de tarefas
    	tarefasArray = new ArrayList<Tarefa>();
    	
    	// adiciona alguns objetos ao array
    	//tarefasArray.add(new Tarefa("Estudar Android", "Alta"));
    	//tarefasArray.add(new Tarefa("Desenvolver um app", "Média"));
    	//tarefasArray.add(new Tarefa("Publicar no Google Play", "Média"));
    	//tarefasArray.add(new Tarefa("Fazer marketing", "Baixa"));
    	//tarefasArray.add(new Tarefa("Ganhar muito dinheiro", "Alta"));
    	//tarefasArray.add(new Tarefa("Viajar para a Suíça", "Alta"));
    	
    	// cria um novo adapter de tarefas - adapter simples
    	//tarefasAdapter = new ArrayAdapter<Tarefa>(
    	//					getBaseContext(),
    	//					android.R.layout.simple_list_item_1,
    	//					tarefasArray);
    	
    	tarefasAdapter = new TarefaAdapter(this, tarefasArray);
    	
    	// tipos de layouts que podemos usar na lista:
    	// simple_list_item_1 - padrão
    	// simple_list_item_activated_1 - item selecionado azul
    	// simple_list_item_single_choice - radio buttons
    	// simple_list_item_multiple_choice - checkboxes
    	// simple_list_item_checked - vistos (V)
    	
    	// configura a lista para usar o adapter
    	lvTarefas.setAdapter(tarefasAdapter);
    	
    	// configura o tipo de seleção da lista
    	lvTarefas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    	
    	// adiciona o evento de item click na lista
    	lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega a tarefa selecionada
				Tarefa tarefa = (Tarefa)
						parent.getItemAtPosition(position);
				
				// pega a prioridade da tarefa
				String prioridade = tarefa.getPrioridade();
				
				// mostra um Toast com a prioridade
				Toast.makeText(
						getBaseContext(),
						prioridade,
						Toast.LENGTH_SHORT).show();
				
			} // fecha onItemClick
		}); // fecha setOnItemClickListener
    	
    	// adiciona o evento long click nos itens da lista
    	lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega a tarefa clicada
				Tarefa tarefa = (Tarefa)
						parent.getItemAtPosition(position);
				
				// remove a tarefa do array de tarefas
				tarefasArray.remove(tarefa);
				
				// notifica o adapter que os dados mudaram
				tarefasAdapter.notifyDataSetChanged();
				
				// pega a descrição da tarefa
				//String descricao = tarefa.getDescricao();
				
				// mostra um Toast com a descrição
				//Toast.makeText(
				//		getBaseContext(), 
				//		"Longclick..." + descricao, 
				//		Toast.LENGTH_SHORT).show();
				
				// se retornar false, dispara o evento de click também
				// se retornar true, não dispara o evento de click
				return true;
				
			} // fecha onItemLongClick
		}); // fecha setOnItemLongClickListener
    	
    } // fecha configurarLvTarefas

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
}
