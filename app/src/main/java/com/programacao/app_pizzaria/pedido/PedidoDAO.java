package com.programacao.app_pizzaria.pedido;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.banco.DAO;
import com.programacao.app_pizzaria.produto.Produto;
import com.programacao.app_pizzaria.usuario.Usuario;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements DAO<Pedido> {

    private static SQLiteDatabase bancoDados = ConexaoBancoDados.getInstance();
    private static PedidoDAO instance;

    public static PedidoDAO getInstance() {
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
                .append(pedido.getRealizarEntrega()).append("\")")
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
        Cursor cursor = bancoDados.rawQuery(" SELECT id, nomeusuario, formapagamento, teleentrega FROM pedidos", null);
        List<Pedido> listPedido = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int iId = cursor.getColumnIndex("id");
            int iNomeUsuario = cursor.getColumnIndex("nomeusuario");
            int iFormaPagamento = cursor.getColumnIndex("formapagamento");
            int iTeleEntrega = cursor.getColumnIndex("teleentrega");
            while (cursor.moveToNext()) {
                Pedido pedido = new Pedido();
                pedido.setId(cursor.getInt(iId));
                pedido.setNomeUsuario(cursor.getString(iNomeUsuario));
                pedido.setFormaPagamento(cursor.getString(iFormaPagamento));
                pedido.setRealizarEntrega(cursor.getString(iTeleEntrega));
                listPedido.add(pedido);
            }
        }
        return listPedido;
    }

    public List<String> listarItensPedido() {
        Cursor cursor = bancoDados.rawQuery(" SELECT nomeproduto FROM pedidoitem", null);
        List<String> listItensPedido = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int iId = cursor.getColumnIndex("id");
            int inomeProduto = cursor.getColumnIndex("nomeproduto");
            while (cursor.moveToNext()) {
                listItensPedido.add(null);

            }
        }
        return listItensPedido;
    }

    public List<PedidoItem> listarPedidoItensPorPedidoId(int idPedido) {
        Cursor cursor = bancoDados.rawQuery(" SELECT id, nomeproduto, idpedido FROM pedidoitem WHERE idpedido = " + idPedido, null);
        List<PedidoItem> itens = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            int iId = cursor.getColumnIndex("id");
            int iNomeProduto = cursor.getColumnIndex("nomeproduto");
            int iIdPedido = cursor.getColumnIndex("idpedido");
            while (cursor.moveToNext()) {
                PedidoItem item = new PedidoItem();
                item.setId(cursor.getInt(iId));
                item.setNomeProduto(cursor.getString(iNomeProduto));
                item.setIdPedido(cursor.getInt(iIdPedido));
                itens.add(item);
            }
        }
        return itens;
    }

    @Override
    public void atualizar(Pedido pedido) {
        StringBuilder sql = new StringBuilder("UPDATE pedidos ")
                .append(" SET nomeusuario = '")
                .append(pedido.getNomeUsuario())
                .append("', formapagamento = '")
                .append(pedido.getFormaPagamento())
                .append("', teleentrega = '")
                .append(pedido.getRealizarEntrega())
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
