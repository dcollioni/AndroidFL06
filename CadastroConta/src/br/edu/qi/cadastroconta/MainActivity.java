package br.edu.qi.cadastroconta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {

	// declara os componentes da tela
	EditText etNome, etCpf;
	CheckBox cbCartao, cbTalao, cbSeguro;
	Button btConfirma;
	RadioGroup rgTipoConta;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // pega todos os componentes da tela
        etNome = (EditText) findViewById(R.id.et_nome);
        etCpf = (EditText) findViewById(R.id.et_cpf);
        cbCartao = (CheckBox) findViewById(R.id.cb_cartao);
        cbTalao = (CheckBox) findViewById(R.id.cb_talao);
        cbSeguro = (CheckBox) findViewById(R.id.cb_seguro);
        btConfirma = (Button) findViewById(R.id.bt_confirma);
        rgTipoConta = (RadioGroup) findViewById(R.id.rg_tipo_conta);
        
        // adiciona o evento de click no botão
        btConfirma.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// pega os valores de texto dos campos
				String nome = etNome.getText().toString();
				String cpf = etCpf.getText().toString();
				String cartao = cbCartao.getText().toString();
				String talao = cbTalao.getText().toString();
				String seguro = cbSeguro.getText().toString();
				
				// pega se os checkboxes estão marcados
				boolean cartaoMarcado = cbCartao.isChecked();
				boolean talaoMarcado = cbTalao.isChecked();
				boolean seguroMarcado = cbSeguro.isChecked();
				
				// pega sim ou não dependendo se está marcado
				String cartaoSimNao = cartaoMarcado ?
										getString(R.string.sim) :
										getString(R.string.nao);
				
				String talaoSimNao = talaoMarcado ?
										getString(R.string.sim) :
										getString(R.string.nao);
										
				String seguroSimNao = seguroMarcado ? 
										getString(R.string.sim) :
										getString(R.string.nao);
										
				// pega o id do radio button selecionado
				int contaSelecionada = rgTipoConta.getCheckedRadioButtonId();
				
				// pega o radio button selecionado
				RadioButton rdSelecionado = (RadioButton)
											findViewById(contaSelecionada);
				
				// pega o texto do radio button selecionado
				String tipoConta = rdSelecionado.getText().toString();
				
				// cria uma mensagem para mostrar
				String msg = getString(R.string.nome) + ": " + nome;
				msg += "\n" + getString(R.string.cpf) + ": " + cpf;
				msg += "\n" + cartao + ": " + cartaoSimNao;
				msg += "\n" + talao + ": " + talaoSimNao;
				msg += "\n" + seguro + ": " + seguroSimNao;
				msg += "\n" + getString(R.string.tipo_conta) + ": " + tipoConta;
				
				// mostra um Toast com a msg
				Toast.makeText(
						getBaseContext(),
						msg,
						Toast.LENGTH_SHORT).show();
				
			} // fecha onClick
		}); // fecha listener
        
        // adiciona evento para quando o checkbox alterar o check
        cbCartao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(
					CompoundButton buttonView,
					boolean isChecked) {
				
				// habilita ou desabilita o checkbox de seguro
				cbSeguro.setEnabled(isChecked);
				
				// remove a marcação
				cbSeguro.setChecked(false);
				
			} // fecha onCheckedChanged
		}); // fecha listener
        
        rgTipoConta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(
							RadioGroup group,
							int checkedId) {
				
				// pega o radio button selecionado
				RadioButton rdSelecionado = (RadioButton)
											group.findViewById(checkedId);
				
				// pega o texto do radio button
				String tipoConta = rdSelecionado.getText().toString();
				
				// mostra um toast com o texto
				Toast.makeText(
						getBaseContext(),
						tipoConta,
						Toast.LENGTH_SHORT).show();
				
			} // fecha checked change
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
