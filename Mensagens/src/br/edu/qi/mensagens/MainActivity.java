package br.edu.qi.mensagens;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Declara uma variável Button
        Button btnOK;
        
        // Pega o botão pelo ID
        btnOK = (Button) findViewById(R.id.btn_ok);
        
        // Adiciona o evento de click no botão
        btnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Cria um objeto Toast
		        Toast t = Toast.makeText(
		        				getBaseContext(), 
		    					R.string.bem_vindo,
		        				Toast.LENGTH_LONG);
		        
		        // Mostra o toast
		        t.show();
		        
		        // Cria outro Toast
		        Toast t2 = Toast.makeText(
		        				getBaseContext(),
		    					R.string.ola, 
		        				Toast.LENGTH_SHORT);
		        
		        // Mostra o toast
		        t2.show();
		        
		        // Cria um objeto alerta
		        AlertDialog a = new AlertDialog
		        						.Builder(MainActivity.this)
		        						.setPositiveButton("OK", null)
		        						.setNegativeButton("Cancelar", null)
		        						.create();
		        
		        // Coloca um título no alerta
		        a.setTitle(R.string.bem_vindo);
		        // Coloca uma mensagem no alerta
		        a.setMessage(getString(R.string.ola));
		        // Mostra o alerta
		        a.show();
				
			}
		});
        
        // declara o botão Natal
        Button btnNatal;
        
        // pega o botão
        btnNatal = (Button)findViewById(R.id.btn_natal);
        
        // adiciona o evento no botão Natal
        btnNatal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Mostra um Toast
				Toast.makeText(
						getBaseContext(),
						R.string.feliz_natal, 
						Toast.LENGTH_SHORT).show();
			}
		});
        
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
