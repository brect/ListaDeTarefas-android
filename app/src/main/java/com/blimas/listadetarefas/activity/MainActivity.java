package com.blimas.listadetarefas.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.blimas.listadetarefas.R;
import com.blimas.listadetarefas.adapter.TarefaAdapter;
import com.blimas.listadetarefas.helper.DbHelper;
import com.blimas.listadetarefas.helper.RecyclerItemClickListener;
import com.blimas.listadetarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewlistaTarefas;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerViewlistaTarefas = findViewById(R.id.recyclerListaTarefas);

        //Adicionar evento de click
        recyclerViewlistaTarefas.addOnItemTouchListener((
            new RecyclerItemClickListener(
                    getApplicationContext(),
                    new RecyclerItemClickListener.OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onItemClick(View view, int position) {

                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }
                    }
                )
            ));


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
//        Tarefa tarefa1 = new Tarefa();
//        tarefa1.setNomeTarefa("Xurupita");
//        listaTarefas.add(tarefa1);
//        Tarefa tarefa2 = new Tarefa();
//        tarefa1.setNomeTarefa("Xurupita Xurupita");
//        listaTarefas.add(tarefa2);

        //configura adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);

        //configura recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewlistaTarefas.setLayoutManager(layoutManager);
        recyclerViewlistaTarefas.setHasFixedSize(true);
        recyclerViewlistaTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerViewlistaTarefas.setAdapter(tarefaAdapter);

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
