package br.edu.qi.duastelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MainActivity extends Activity {

	// 1. declara os componentes
	Button btAbrirOutraTela;
	EditText etNome;
	Spinner spCores;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 2. busca os componentes pelo id
        btAbrirOutraTela = (Button)
        		findViewById(R.id.bt_abrir_outra_tela);
        etNome = (EditText)
        		findViewById(R.id.et_nome);
        spCores = (Spinner)
        		findViewById(R.id.sp_cores);
        
        // 3. adiciona evento de click no botão
        btAbrirOutraTela.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// declara uma intent para a OutraActivity
				// o primeiro parâmetro é a tela de origem
				// o segundo parâmetro é a tela de destino
				Intent intent = new Intent(
									MainActivity.this,
									OutraActivity.class);
				
				// pega o texto do campo nome
				String nome = etNome.getText().toString();
				
				// passa o nome para a intent que vai abrir
				// o primeiro parâmetro é a chave do parâmetro
				// o segundo parâmetro é o valor em si
				intent.putExtra("meu_nome", nome);
				
				// pega a cor selecionada no spinner
				String cor = spCores.getSelectedItem().toString();
				
				// passa a cor para a intent que vai abrir
				intent.putExtra("cor", cor);
				
				// inicia a intent criada
				startActivity(intent);
				
			} // fecha onClick
		}); // fecha OnClickListener
        
    } // fecha onCreate


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
