package br.edu.qi.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

	// Declarando os componentes da tela
	EditText etUsuario, etSenha, etEmail;
	ToggleButton tbValidar;
	Button btEntrar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // pega os componentes do xml
        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etSenha = (EditText) findViewById(R.id.et_senha);
        etEmail = (EditText) findViewById(R.id.et_email);
        tbValidar = (ToggleButton) findViewById(R.id.tb_validar);
        btEntrar = (Button) findViewById(R.id.bt_entrar);
        
        // adiciona um evento de click no botão
        btEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// declara duas strings para os valores dos campos
				String usuario = etUsuario.getText().toString();
				String senha = etSenha.getText().toString();
				String email = etEmail.getText().toString();
				
				// declara um boolean para pegar o valor do ToggleButton
				boolean validar = tbValidar.isChecked();
				
				// testa se não está marcado para validar
				if (!validar) {
				
					// declara uma String com a mensagem
					String msg = getString(R.string.usuario) + ": " + usuario;
					msg += "\n " + getString(R.string.senha) + ": " + senha;
					msg += "\n " + getString(R.string.email) + ": " + email;
					
					// mostra um Toast com a mensagem
					Toast.makeText(
							getBaseContext(),
							msg,
							Toast.LENGTH_SHORT).show();
				} // fecha if
				else {
					// quando o validar estiver marcado...
					
					// declara o usuário e senha corretos
					String usuarioCorreto = "alunoQI";
					String senhaCorreta = "123456";
					
					// se usuário e senha são válidos
					if (usuario.equals(usuarioCorreto)
						&& senha.equals(senhaCorreta)) {
						
						// mostra mensagem de sucesso
						Toast.makeText(
								getBaseContext(),
								R.string.usuario_valido,
								Toast.LENGTH_SHORT).show();
						
					} // fecha if
					else {
						// quando usuário ou senha forem inválidos...
						
						// mostra mensagem de inválido
						Toast.makeText(
								getBaseContext(), 
								R.string.usuario_invalido,
								Toast.LENGTH_SHORT).show();
					}
					
				} // fecha else
				
			} // fecha onClick
		}); // fecha listener
        
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
