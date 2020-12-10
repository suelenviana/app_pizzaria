package com.programacao.app_pizzaria.Util;

import com.programacao.app_pizzaria.produto.Produto;
import com.programacao.app_pizzaria.usuario.Usuario;

public interface DataTransferInterface<T> {
    void onEditar(T t);

    void onExcluir(T t);
}
