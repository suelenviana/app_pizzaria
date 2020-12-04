package com.programacao.app_pizzaria.banco;

import java.util.List;

public interface DAO<T> {
    void criarTabela();

    void adicionar(T t);

    List<Object> listar();

    void atualizar(T t);

    void excluir(T t);
}
