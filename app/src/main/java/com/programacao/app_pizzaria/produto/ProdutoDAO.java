package com.programacao.app_pizzaria.produto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.banco.DAO;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements DAO<Produto> {

    private static SQLiteDatabase bancoDados = ConexaoBancoDados.getInstance();
    private static ProdutoDAO instance;

    public static ProdutoDAO getInstance() {
        if (instance == null) return new ProdutoDAO();
        return instance;
    }

    @Override
    public void criarTabela() {
        String sql = new StringBuilder("")
                .append("CREATE TABLE IF NOT EXISTS produtos (")
                .append("   id INTEGER PRIMARY KEY,")
                .append("   descricao VARCHAR(250) NOT NULL,")
                .append("   precoVenda NUMBER(50) NOT NULL,")
                .append("   precoCusto NUMBER(50) NOT NULL,")
                .append("   tipoProduto CHAR(1) NOT NULL")
                .append(")")
                .toString();
        bancoDados.execSQL(sql);
    }

    @Override
    public void adicionar(Produto produto) {
        String sql = new StringBuilder("")
                .append("INSERT INTO produtos")
                .append(" (descricao, precoVenda, precoCusto, tipoProduto)")
                .append(" VALUES (\"")
                .append(produto.getDescricao()).append("\", \"")
                .append(produto.getPrecoVenda()).append("\", \"")
                .append(produto.getPrecoCusto()).append("\", \"")
                .append(produto.getTipoProduto()).append("\"")
                .append(" )")
                .toString();
        bancoDados.execSQL(sql);
    }

    @Override
    public List<Produto> listar() {
        Cursor cursor = bancoDados.rawQuery(" SELECT id, descricao, precoVenda, precoCusto  FROM produtos", null);
        List<Produto> listProduto = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int iId = cursor.getColumnIndex("id");
            int iDescricao = cursor.getColumnIndex("descricao");
            int iPrecoVenda = cursor.getColumnIndex("precoVenda");
            int iPrecoCusto = cursor.getColumnIndex("precoCusto");
            while (cursor.moveToNext()) {
                Produto produto = new Produto();
                produto.setId(cursor.getInt(iId));
                produto.setDescricao(cursor.getString(iDescricao));
                produto.setPrecoVenda(cursor.getString(iPrecoVenda));
                produto.setPrecoCusto(cursor.getString(iPrecoCusto));
                listProduto.add(produto);
            }
        }
        return listProduto;
    }

    @Override
    public void atualizar(Produto produto) {
        StringBuilder sql = new StringBuilder("UPDATE produtos ")
                .append(" SET descricao = '")
                .append(produto.getDescricao())
                .append("', precoVenda = '")
                .append(produto.getPrecoVenda())
                .append("', precoCusto = '")
                .append(produto.getPrecoCusto())
                .append("', tipoProduto = '")
                .append(produto.getTipoProduto())
                .append("' WHERE id = ")
                .append(produto.getId());

        bancoDados.execSQL(sql.toString());

    }

    @Override
    public void excluir(Produto produto) {
        String sql = new StringBuilder("DELETE FROM produtos WHERE id = ")
                .append(produto.getId())
                .toString();

        bancoDados.execSQL(sql);

    }


}
