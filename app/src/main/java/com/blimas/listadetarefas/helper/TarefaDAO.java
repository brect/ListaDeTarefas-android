package com.blimas.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.blimas.listadetarefas.model.Tarefa;

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
        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar tarefa " + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {
        return null;
    }
}
