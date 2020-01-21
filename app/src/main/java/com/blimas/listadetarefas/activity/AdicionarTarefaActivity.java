package com.blimas.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.blimas.listadetarefas.R;
import com.blimas.listadetarefas.helper.TarefaDAO;
import com.blimas.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText tarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        tarefa = findViewById(R.id.textTarefa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salvar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.itemSalvarTarefa) {

            //Executa acao para salvar o item
            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

            Tarefa tarefa = new Tarefa();
            tarefa.setNomeTarefa("Ir ao xurupita");
            tarefaDAO.salvar(tarefa);

            Toast.makeText(this, "Tarefa salva" + tarefa, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
