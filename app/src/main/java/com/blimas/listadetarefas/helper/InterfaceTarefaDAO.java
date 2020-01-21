package com.blimas.listadetarefas.helper;

import com.blimas.listadetarefas.model.Tarefa;

import java.util.List;

public interface InterfaceTarefaDAO {


    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();

}
