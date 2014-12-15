package br.edu.qi.contatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DetalheActivity extends Activity {

	// declara os componentes da tela
	EditText etNome, etTelefone;
	
	// método para carregar os componentes pelo id
	private void carregarComponentes() {
		
		etNome = (EditText) findViewById(R.id.et_nome);
		etTelefone = (EditText) findViewById(R.id.et_telefone);
		
	} // fecha carregarComponentes
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);
		
		// chama o método para carregar os componentes
		carregarComponentes();
		
		// pega a intent
		Intent intent = getIntent();
		
		// pega o objeto contato que veio na intent
		Contato c = (Contato)
				intent.getSerializableExtra("contato");
		
		// atualiza os campos com os valores do objeto
		etNome.setText(c.getNome());
		etTelefone.setText(c.getTelefone());
		
	} // fecha onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhe, menu);
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
