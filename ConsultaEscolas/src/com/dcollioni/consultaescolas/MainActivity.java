package com.dcollioni.consultaescolas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.dcollioni.consultas.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {

	// declara os componentes
	ListView lvEscolas;
	ArrayList<Escola> arrayEscolas;
	ArrayAdapter<Escola> adapterEscolas;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        configurarLvEscolas();
    }

    // configura a lista de escolas
    private void configurarLvEscolas() {
    	lvEscolas = (ListView)findViewById(R.id.lv_escolas);
    	
    	arrayEscolas = new ArrayList<Escola>();
    	
    	adapterEscolas = new ArrayAdapter<Escola>(
    								getBaseContext(),
    								android.R.layout.simple_list_item_1,
    								arrayEscolas);
    	
    	lvEscolas.setAdapter(adapterEscolas);
    	
    	lvEscolas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				/*	TODO:
				 * 	1. Pegar a escola selecionada
				 * 	2. Criar uma intent para abrir a activity Detalhe
				 * 	3. Passar a escola para a intent
				 * 	4. Iniciar a intent
				 */
			}
		});
    }
    
    // classe que faz uma consulta assíncrona para buscar as escolas
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
    			
    			/*	TODO:
    			 * 	1. Pegar o resultado recebido em forma de JSON
    			 * 	2. Para cada resultado, criar um objeto escola
    			 * 	3. Adicionar o objeto escola no array de escolas
    			 * 	4. No fim, notificar o adapter sobre a alteração nos dados
    			 */
	        }
	        catch (Exception e) {
	        	Log.e("Consultas", e.toString());
	        }
        }
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
        if (id == R.id.action_carregar) {
            
        	/*	TODO:
        	 * 	1. Criar um objeto ConsultaAsync
        	 * 	2. Executar a consulta passando a URL
        	 */
        	
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
