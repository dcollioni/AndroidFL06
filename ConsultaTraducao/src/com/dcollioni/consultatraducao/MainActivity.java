package com.dcollioni.consultatraducao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	// declara os componentes da tela
	Spinner spIdioma;
	EditText etTexto;
	Button btTraduzir;
	TextView tvResultado;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        carregarComponentes();
        configurarBtTraduzir();
    }

    private void carregarComponentes() {
    	spIdioma = (Spinner) findViewById(R.id.sp_idioma);
    	etTexto = (EditText) findViewById(R.id.et_texto);
    	btTraduzir = (	Button) findViewById(R.id.bt_traduzir);
    	tvResultado = (TextView) findViewById(R.id.tv_resultado);
    }
    
    private void configurarBtTraduzir() {
    	
    	btTraduzir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				/*	TODO:
				 * 	4. Montar a URL com os parâmetros
				 * 	5. Executar a consulta passando a url
				 */
				
				// pega o idioma selecionado
				String idioma = spIdioma.getSelectedItem().toString();
				
				// pega o código do idioma selecionado
				String codigoIdioma =
						idioma.equals(getString(R.string.ingles)) ? "en" :
						idioma.equals(getString(R.string.espanhol)) ? "es" :
						idioma.equals(getString(R.string.frances)) ? "fr" : "";
				
				// pega o texto digitado
				String texto = etTexto.getText().toString();
				
				// monta a url para traduzir
				String url = "http://www.worldlingo.com/";
				url += "S3704.3/texttranslate";
				url += "?wl_srclang=pt";
				url += "&wl_trglang=" + codigoIdioma;
				url += "&wl_text=" + texto;
				
				// cria o objeto consulta async
				ConsultaAsync consultaTraducao = new ConsultaAsync();
				
				// executa a consulta
				consultaTraducao.execute(url);
			}
		});
    }

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
    
 // classe que faz uma consulta assíncrona para buscar a tradução
    private class ConsultaAsync extends AsyncTask<String, Void, String> {

    	@Override
    	protected String doInBackground(String... url) {
    		
    		// cria os objetos para fazer um request
    		HttpClient client = new DefaultHttpClient();
        	HttpGet getRequest = new HttpGet();
        	
        	// variável que irá receber os resultados 
        	String result = "";
        	
        	try {
        		// passa a URL recebida por parâmetro
    			getRequest.setURI(new URI(url[0]));
    			
    			// cria os objetos que serão usados para ler a resposta
    			BufferedReader in = null;
    			HttpResponse response = null;
        	
    			// executa a requisição
        		response = client.execute(getRequest);
        		
        		// lê o resultado da requisição
    			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        	
    			// cria os objetos para transformar o resultado em string
    			StringBuffer buff = new StringBuffer("");
    			String line = "";
        	
    			// enquanto houver linhas no resultado
    			while ((line=in.readLine())!=null)
    			{
    				// adiciona uma linha na string de resultado
    				buff.append(line);
    			}
        	
    			// fecha o resultado recebido
    			in.close();
    			
    			// passa o resultado em string para a variável result
    			result = buff.toString();
    			
    			// loga as informações para poder conferir
    			Log.i("Consultas", result);
        	}
        	catch (Exception e) {
        		Log.e("Consultas", e.toString());
        	}
        	
        	return result;
    	}
    	
    	// método que será chamado depois que a requisição for executada
    	protected void onPostExecute(String result) {
    		try {
    			
    			// preenche o text view com o resultado
    			tvResultado.setText(result);
			}
	        catch (Exception e) {
	        	Log.e("Consultas", e.toString());
	        }
        }
    }
}
