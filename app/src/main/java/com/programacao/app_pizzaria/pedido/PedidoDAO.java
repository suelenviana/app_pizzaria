package com.programacao.app_pizzaria.pedido;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.banco.DAO;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

import java.util.List;

public class PedidoDAO implements DAO<Pedido> {

    private static SQLiteDatabase bancoDados = ConexaoBancoDados.getInstance();
    private static PedidoDAO instance;

    public static PedidoDAO getInstance(){
        if (instance == null) return new PedidoDAO();
        return instance;
    }

    @Override
    public void criarTabela() {
        String sql = new StringBuilder("")
                .append("CREATE TABLE IF NOT EXISTS pedidos (")
                .append("   id INTEGER PRIMARY KEY,")
                .append("   nomeusuario VARCHAR(250) NOT NULL,")
                .append("   formapagamento VARCHAR(50) NOT NULL,")
                .append("   teleentrega BOOLEAN NOT NULL")
                .append(")")
                .toString();

        bancoDados.execSQL(sql);

        sql = new StringBuilder("")
                .append("CREATE TABLE IF NOT EXISTS pedidoitem (")
                .append("   id INTEGER PRIMARY KEY,")
                .append("   nomeproduto VARCHAR(250) NOT NULL,")
                .append("   idpedido INTEGER NOT NULL")
                .append(")")
                .toString();

        bancoDados.execSQL(sql);
    }

    @Override
    public void adicionar(Pedido pedido) {
    }


    public int adicionarPedido(Pedido pedido) {
        String sql = new StringBuilder("INSERT INTO pedidos (nomeusuario, formapagamento, teleentrega) VALUES (\"")
                .append(pedido.getNomeUsuario()).append("\", \"")
                .append(pedido.getFormaPagamento()).append("\", \"")
                .append(pedido.isRealizarEntrega()).append("\")")
                .toString();

        bancoDados.execSQL(sql);

        sql = "SELECT MAX(id) from pedidos";

        Cursor cursor = bancoDados.rawQuery(sql, null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        return i;
    }

    public void adicionarPedidoItem(PedidoItem pedidoItem) {
        String sql = new StringBuilder("INSERT INTO pedidoitem (nomeproduto, idpedido) VALUES (\"")
                .append(pedidoItem.getNomeProduto()).append("\", \"")
                .append(pedidoItem.getIdPedido()).append("\")")
                .toString();

        bancoDados.execSQL(sql);
    }

    @Override
    public List<Pedido> listar() {
        return null;
    }

    @Override
    public void atualizar(Pedido pedido) {
        StringBuilder sql = new StringBuilder("UPDATE pedidos ")
                .append(" SET nomeusuario = '")
                .append(pedido.getNomeUsuario())
                .append("', formapagamento = '")
                .append(pedido.getFormaPagamento())
                .append("', teleentrega = '")
                .append(pedido.isRealizarEntrega())
                .append("' WHERE id = ")
                .append(pedido.getId());

        bancoDados.execSQL(sql.toString());
    }

    @Override
    public void excluir(Pedido pedido) {
        String sql = new StringBuilder("DELETE FROM pedidos WHERE id = ")
                .append(pedido.getId())
                .toString();

        bancoDados.execSQL(sql);
    }
}
