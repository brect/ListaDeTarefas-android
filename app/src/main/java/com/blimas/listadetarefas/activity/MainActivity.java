package com.blimas.listadetarefas.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.blimas.listadetarefas.R;
import com.blimas.listadetarefas.adapter.TarefaAdapter;
import com.blimas.listadetarefas.helper.DbHelper;
import com.blimas.listadetarefas.helper.RecyclerItemClickListener;
import com.blimas.listadetarefas.helper.TarefaDAO;
import com.blimas.listadetarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;
    Tarefa tarefaEscolhida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerListaTarefas);

        //Adicionar evento de clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //recupera tarefa para edicao
                        tarefaEscolhida = listaTarefas.get(position);

                        //Envia tarefa para adicionar tarefa
                        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaEscolhida", tarefaEscolhida);

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        tarefaEscolhida = listaTarefas.get(position);

                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);

                        //Configura titulo do dialogo
                        dialogo.setTitle("Confirma exclusão");
                        dialogo.setMessage("Deseja excluir a tarefa " + tarefaEscolhida.getNomeTarefa() + " ?");

                        //configura botoes
                        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                                if (tarefaDAO.deletar(tarefaEscolhida)){
                                    carregarListaTarefas();
                                    Toast.makeText(getApplicationContext(), "Sucesso ao deletar a Tarefa", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(), "Erro ao deletar a Tarefa", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        //cria exibição do dialog
                        dialogo.setNegativeButton("Não", null);
                        dialogo.create().show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void carregarListaTarefas(){
        //listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        listaTarefas = tarefaDAO.listar();

        //configura adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);

        //configura recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);

    }

    @Override
    protected void onStart() {
                carregarListaTarefas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
