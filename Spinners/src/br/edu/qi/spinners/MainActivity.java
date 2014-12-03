package br.edu.qi.spinners;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	Spinner spCores, spEstados;
	LinearLayout layoutPrincipal;
	TextView tvNomeEstado, tvSiglaEstado, tvCapitalEstado;
	
	// método para carregar todos os componentes da tela
	private void carregarComponentes() {
		
		spCores = (Spinner) findViewById(R.id.sp_cores);
		
		spEstados = (Spinner) findViewById(R.id.sp_estados);
		
		layoutPrincipal = (LinearLayout)
				findViewById(R.id.layout_principal);
		
		tvNomeEstado = (TextView) findViewById(R.id.tv_nome_estado);
		tvSiglaEstado = (TextView) findViewById(R.id.tv_sigla_estado);
		tvCapitalEstado = (TextView) findViewById(R.id.tv_capital_estado);
		
	} // fecha carregarComponentes
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // carrega todos os componentes
        carregarComponentes();
        
        // configura o spinner de cores
        configurarSpinnerCores();
        
        // configura o spinner de estados
        configurarSpinnerEstados();
        
    } // fecha onCreate
    
    // método para configurar o Spinner cores
    private void configurarSpinnerCores() {
    	
    	// cria um array adapter para o spinner
    	ArrayAdapter<CharSequence> adapter = 
    			ArrayAdapter.createFromResource(
    					getBaseContext(),
    					R.array.cores,
						android.R.layout.simple_spinner_item);
    	
    	// configura o layout do spinner
    	adapter.setDropDownViewResource(
    			android.R.layout.simple_spinner_dropdown_item);
    	
    	// configura o spinner com o adapter criado
    	spCores.setAdapter(adapter);
    	
    	// adiciona o evento de item selecionado ao spinner
    	spCores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega o item selecionado
				String cor = (String)parent.getItemAtPosition(position);
				
				// mostra um Toast com a cor selecionada
				//Toast.makeText(
						//getBaseContext(),
						//cor,
						//Toast.LENGTH_SHORT).show();
				
				// declara um int para receber o id da cor
				int corId;
				
				// testa se a cor selecionada é azul
				if (cor.equals(getString(R.string.azul))) {
					// pega a cor azul
					corId = R.color.azul;
				}
				// senão, testa se é vermelho
				else if (cor.equals(getString(R.string.vermelho))) {
					// pega a cor vermelho
					corId = R.color.vermelho;
				}
				// senão
				else {
					// pega a cor amarelo
					corId = R.color.amarelo;
				}
				
				// altera a cor de fundo da página
				layoutPrincipal.setBackgroundResource(corId);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    } // fecha método configurarSpinnerCores

    // método para configurar o spinner de estados
    private void configurarSpinnerEstados() {
    	
    	// declara um arraylist de estados
    	ArrayList<Estado> estados = new ArrayList<Estado>();
    	
    	// adiciona três estados ao arraylist
    	estados.add(new Estado(
    			"Rio Grande do Sul",
    			"RS",
    			"Porto Alegre"));
    	
    	estados.add(new Estado(
    			"Santa Catarina",
    			"SC",
    			"Florianópolis"));
    	
    	estados.add(new Estado(
    			"Paraná",
    			"PR",
    			"Curitiba"));
    	
    	// cria um adapter com o array de estados
    	ArrayAdapter<Estado> adapter =
    			new ArrayAdapter<Estado>(
    					getBaseContext(),
    					android.R.layout.simple_spinner_item,
    					estados);
    	
    	// configura o layout
    	adapter.setDropDownViewResource(
    			android.R.layout.simple_spinner_dropdown_item);
    	
    	// adiciona o adapter ao spinner
    	spEstados.setAdapter(adapter);
    	
    	spEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				// pega o estado selecionado
				Estado estado = (Estado) parent.getItemAtPosition(position);
				
				// atualiza os text views com os valores do estado
				tvNomeEstado.setText(estado.getNome());
				tvSiglaEstado.setText(estado.getSigla());
				tvCapitalEstado.setText(estado.getCapital());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    } // fecha método configurarSpinnerEstados

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
