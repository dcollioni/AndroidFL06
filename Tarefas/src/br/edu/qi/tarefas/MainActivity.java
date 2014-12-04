package br.edu.qi.tarefas;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	// declara os componentes da tela
	ListView lvTarefas;
	
	// declara objetos para configurar a listview
	ArrayList<Tarefa> tarefasArray;
	ArrayAdapter<Tarefa> tarefasAdapter;
	
	// método para carregar todos os componentes
	private void carregarComponentes() {
		
		lvTarefas = (ListView) findViewById(R.id.lv_tarefas);
		
	} // fecha carregarComponentes
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // chama o método para carregar os componentes
        carregarComponentes();
        
        // chama o método para configura a listview
        configurarLvTarefas();
        
    } // fecha onCreate

    // método para configura a list view de tarefas
    private void configurarLvTarefas() {
    	
    	// cria um novo array de tarefas
    	tarefasArray = new ArrayList<Tarefa>();
    	
    	// adiciona alguns objetos ao array
    	tarefasArray.add(new Tarefa("Estudar Android", "Alta"));
    	tarefasArray.add(new Tarefa("Desenvolver um app", "Média"));
    	tarefasArray.add(new Tarefa("Publicar no Google Play", "Média"));
    	tarefasArray.add(new Tarefa("Fazer marketing", "Baixa"));
    	tarefasArray.add(new Tarefa("Ganhar muito dinheiro", "Alta"));
    	tarefasArray.add(new Tarefa("Viajar para a Suíça", "Alta"));
    	
    	// cria um novo adapter de tarefas
    	tarefasAdapter = new ArrayAdapter<Tarefa>(
    						getBaseContext(),
    						android.R.layout.simple_list_item_1,
    						tarefasArray);
    	
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
				
				// pega a descrição da tarefa
				String descricao = tarefa.getDescricao();
				
				// mostra um Toas com a descrição
				Toast.makeText(
						getBaseContext(), 
						"Longclick..." + descricao, 
						Toast.LENGTH_SHORT).show();
				
				// se retornar false, dispara o evento de click também
				// se retornar true, não dispara o evento de click
				return false;
				
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
