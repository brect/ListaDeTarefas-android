package com.blimas.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.blimas.listadetarefas.R;
import com.blimas.listadetarefas.helper.TarefaDAO;
import com.blimas.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText conteudoTarefa;
    private Tarefa edicaoTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        conteudoTarefa = findViewById(R.id.textTarefa);

        //recupera tarefa se for edicao
        edicaoTarefa = (Tarefa) getIntent().getSerializableExtra("tarefaEscolhida");
        //configura caixa de texto
        if(edicaoTarefa != null){
            conteudoTarefa.setText(edicaoTarefa.getNomeTarefa());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.itemSalvarTarefa:


                //Executa acao para salvar o item
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                    if (edicaoTarefa != null){//edicao

                        String textoTarefa = conteudoTarefa.getText().toString();

                        if (!textoTarefa.isEmpty()){
                            Tarefa tarefa = new Tarefa();
                            tarefa.setNomeTarefa(textoTarefa);
                            tarefa.setId(edicaoTarefa.getId());

                            //atualizar no banco de dados
                            if (tarefaDAO.atualizar(tarefa)){
                                finish();
                                Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(this, "Erro ao atualizar a Tarefa", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else {//salvar

                        String textoTarefa = conteudoTarefa.getText().toString();

                        if (!textoTarefa.isEmpty() || textoTarefa != null){
                            Tarefa tarefa = new Tarefa();
                            tarefa.setNomeTarefa(textoTarefa);

                            if (tarefaDAO.salvar(tarefa)){
                                Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(this, "Erro ao salvar a Tarefa", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                break;
        }

//        if (id == R.id.itemSalvarTarefa) {
//
//            //Executa acao para salvar o item
//            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
//
//            Tarefa tarefa = new Tarefa();
//            tarefa.setNomeTarefa("Ir ao xurupita");
//            tarefaDAO.salvar(tarefa);
//
//            Toast.makeText(this, "Tarefa salva" + tarefa, Toast.LENGTH_SHORT).show();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
