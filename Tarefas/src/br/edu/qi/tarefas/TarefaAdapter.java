package br.edu.qi.tarefas;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TarefaAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Tarefa> tarefas;
	
	public TarefaAdapter(Context context, ArrayList<Tarefa> tarefas) {
		super();
		this.context = context;
		this.tarefas = tarefas;
	}

	@Override
	public int getCount() {
		// retorna a quantidade de tarefas do array
		return tarefas.size();
	}

	@Override
	public Object getItem(int position) {
		// retorna a tarefa na posição
		return tarefas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// não usamos, pois não temos ID na tarefa
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// declara um objeto ViewHolder
		ViewHolder viewHolder;
		
		// testa se o convertView é nulo
		if (convertView == null) {
			// pega o layout inflater
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			
			// carrega o convertView com o layout da lista
			convertView = inflater.inflate(
								R.layout.tarefa_list_item,
								parent,
								false);
			
			// cria o viewHolder passando o convertView
			viewHolder = new ViewHolder(convertView);
			
			// configura a tag do convertView para o viewHolder
			convertView.setTag(viewHolder);
		}
		// senão
		else {
			// pega o viewHolder do convertView já carregado
			viewHolder = (ViewHolder)convertView.getTag();
		}
		
		// pega a tarefa na posição atual
		Tarefa tarefa = tarefas.get(position);
		
		// testa se a tarefa não é nula
		if (tarefa != null) {
			
			// pega os valores das propriedades da tarefa
			String descricao = tarefa.getDescricao();
			String prioridade = tarefa.getPrioridade();
			
			// preenche o textView com a descrição
			viewHolder.textDescricao.setText(descricao);
			
			// declara um int para o id da imagem
			int imgId = 0;
			
			// testa se a prioridade é alta
			if (prioridade.equals(context.getString(R.string.alta))) {
				// pega o id da imagem vermelha
				imgId = R.drawable.circ_vermelho;
			}
			// senão testa se a prioridade é média
			else if (prioridade.equals(context.getString(R.string.media))) {
				// pega o id da imagem amarela
				imgId = R.drawable.circ_amarelo;
			}
			// senão, testa se a prioridade é baixa
			else if (prioridade.equals(context.getString(R.string.baixa))) {
				// pega o id da imagem verde
				imgId = R.drawable.circ_verde;
			}
			
			// preenche o image view com o id da imagem
			viewHolder.imgPrioridade.setImageResource(imgId);
			// preenche o content description da image view
			viewHolder.imgPrioridade.setContentDescription(prioridade);
			
		} // fecha if
		
		// retorna o convertView
		return convertView;
	} // fecha getView
	
	public static class ViewHolder {
		
		public final ImageView imgPrioridade;
		public final TextView textDescricao;
		
		public ViewHolder(View view) {
			
			imgPrioridade = (ImageView)
					view.findViewById(R.id.img_prioridade);
			
			textDescricao = (TextView)
					view.findViewById(R.id.text_descricao);
			
		} // fecha construtor
		
	} // fecha ViewHolder

} // fecha classe
