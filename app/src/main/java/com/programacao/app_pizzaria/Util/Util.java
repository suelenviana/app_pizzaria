package com.programacao.app_pizzaria.Util;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Util extends AppCompatActivity {

    private static Util instance;

    public static final String CADPRODUTO_VALIDA_DESCRICAO = "Necessáro preencher a Descrição!";
    public static final String CADPRODUTO_VALIDA_PRECO_VENDA = "Necessáro preencher o Preço de Venda!";
    public static final String CADPRODUTO_VALIDA_PRECO_CUSTO = "Necessáro preencher o Preço de Custo!";
    public static final String CADPRODUTO_VALIDA_TIPO_PRODUTO = "Necessáro preencher o Tipo de Produto!";


    public static Util getInstance() {
        if (instance != null) return instance;
        return new Util();
    }

    public void mostraMensagem(String mensagem) {
        Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
    }

}
