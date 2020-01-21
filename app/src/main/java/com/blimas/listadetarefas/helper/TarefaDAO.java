package com.blimas.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.blimas.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements InterfaceTarefaDAO{


    private SQLiteDatabase salvarNaTabela;
    private SQLiteDatabase leDadosDaTabela;

    public TarefaDAO(Context context) {

        DbHelper dataBase = new DbHelper(context);
        salvarNaTabela = dataBase.getWritableDatabase();
        leDadosDaTabela = dataBase.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            salvarNaTabela.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "onOptionsItemSelected: Tarefa salva com sucesso");
        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            String[] args = {tarefa.getId().toString()};

            salvarNaTabela.update(DbHelper.TABELA_TAREFAS, cv, "id=?",  args);
            Log.i("INFO", "Tarefa atualizada com sucesso");

        }catch (Exception e){
            Log.i("INFO", "Erro ao atualizar tarefa " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {



        try{
            String[] args = {tarefa.getId().toString()};

            salvarNaTabela.delete(DbHelper.TABELA_TAREFAS, "id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso");

        }catch (Exception e){
            Log.i("INFO", "Erro ao remover tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas =  new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor cursor = leDadosDaTabela.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

            Long idTarefa = cursor.getLong(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));

            tarefa.setId(idTarefa);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);
        }
        return tarefas;
    }
}
