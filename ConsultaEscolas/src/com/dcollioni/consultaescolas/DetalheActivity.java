package com.dcollioni.consultaescolas;

import com.dcollioni.consultas.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalheActivity extends Activity {

	// declara os componentes da tela
	TextView tvCodigo, tvNome, tvTelefone, tvEndereco; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);
		
		carregarComponentes();
		
		/*	TODO:
		 * 	1. Pegar o objeto escola recebido da intent
		 * 	2. Atualizar os campos com os valores do objeto
		 */
	}
	
	private void carregarComponentes() {
		tvCodigo = (TextView) findViewById(R.id.tv_codigo);
		tvNome = (TextView) findViewById(R.id.tv_nome);
		tvTelefone = (TextView) findViewById(R.id.tv_telefone);
		tvEndereco = (TextView) findViewById(R.id.tv_endereco);
	}

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
