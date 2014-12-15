package br.edu.qi.duastelas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OutraActivity extends Activity {

	// declara os componentes
	LinearLayout layoutOutra;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outra);
		
		// pega os componentes pelo id
		layoutOutra = (LinearLayout)
				findViewById(R.id.layout_outra);
		
		// pega a intent de origem
		Intent intent = getIntent();
		
		// pega o parâmetro nome pela chave "meu_nome"
		String nome = intent.getStringExtra("meu_nome");
		
		// mostra um Toast com o nome recebido
		Toast.makeText(
				getBaseContext(),
				nome,
				Toast.LENGTH_SHORT).show();
		
		// pega o parâmetro cor pela chave "cor"
		String cor = intent.getStringExtra("cor");
		
		// se a cor é igual a "azul"
		if (cor.equals(getString(R.string.azul))) {
			// muda a cor do layout para azul
			layoutOutra.setBackgroundResource(R.color.azul);
		}
		// senão, se for igual a "amarelo"
		else if (cor.equals(getString(R.string.amarelo))) {
			// muda a cor do layout para amarelo
			layoutOutra.setBackgroundResource(R.color.amarelo);
		}
		// senão, se for igual a "vermelho"
		else if (cor.equals(getString(R.string.vermelho))) {
			// muda a cor do layout para vermelho
			layoutOutra.setBackgroundResource(R.color.vermelho);
		}
		
	} // fecha onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.outra, menu);
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
