package com.programacao.app_pizzaria.Util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Util {

    private static Util instance;

    public static final String USUARIO_FUNCAO_GERENTE = "Gerente";
    public static final String USUARIO_FUNCAO_GARCOM = "Garçom";
    public static final String PRODUTO_TIPO_ALIMENTO = "Alimento";
    public static final String PRODUTO_TIPO_BEBIDA = "Bebida";
    public static final String PRODUTO_TIPO_OUTROS = "Outros";

    public static final String CADPRODUTO_VALIDA_DESCRICAO = "Necessáro preencher a Descrição!";
    public static final String CADPRODUTO_VALIDA_PRECO_VENDA = "Necessáro preencher o Preço de Venda!";
    public static final String CADPRODUTO_VALIDA_PRECO_CUSTO = "Necessáro preencher o Preço de Custo!";
    public static final String CADPRODUTO_VALIDA_TIPO_PRODUTO = "Necessáro preencher o Tipo de Produto!";
    public static final String CADPRODUTO_SALVAR_SUCESSO = "Produto salvo com sucesso!";
    public static final String CADPRODUTO_ATUALIZAR_SUCESSO = "Produto atualizado com sucesso!";

    public static final String CADUSUARIO_VALIDA_NOME = "Necessário preencher o Nome";
    public static final String CADUSUARIO_VALIDA_EMAIL = "Necessário preencher o E-mail";
    public static final String CADUSUARIO_VALIDA_TELEFONE = "Necessário preencher o Número de Contato";
    public static final String CADUSUARIO_VALIDA_FUNCAO = "Necessário preencher a Função";
    public static final String CADUSUARIO_SALVAR_SUCESSO = "Usuário salvo com sucesso!";
    public static final String CADUSUARIO_ATUALIZAR_SUCESSO = "Usuário atualizado com sucesso!";

    public static final String CADPEDIDO_SALVAR_SUCESSO = "Pedido salvo com sucesso!";
    public static final String CADPEDIDO_ATUALIZAR_SUCESSO = "Pedido atualizado com sucesso!";
    public static final String CADPEDIDO_PAGAMENTO_DINHEIRO = "Dinheiro";

    public static final String MSG_TITULO = "Confirmação";
    public static final String MSG_SIM = "Sim";
    public static final String MSG_NAO = "Não";
    public static final String MSG_EXCLUIDO = "Excluido!";
    public static final String MSG_EXCLUIR_USUARIO = "Deseja excluir o usuário?";
    public static final String MSG_EXCLUIR_PRODUTO = "Deseja excluir o Produto?";
    public static final String MSG_EXCLUIR_PEDIDO = "Deseja excluir o Pedido?";

    public static final int REQUEST_CODE_EDITAR = 1;

    public static Util getInstance() {
        if (instance != null) return instance;
        return new Util();
    }

    public void mostraMensagem(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    public void mostraMensagemSnackBar(View view, String mensagem) {
        Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG).show();
    }
}
