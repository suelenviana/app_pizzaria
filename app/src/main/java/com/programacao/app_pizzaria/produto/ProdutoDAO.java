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
        Cursor cursor = bancoDados.rawQuery(" SELECT descricao, precoVenda  FROM produtos", null);
        List<Produto> listProduto = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int iDescricao = cursor.getColumnIndex("descricao");
            int iPrecoVenda = cursor.getColumnIndex("precoVenda");
            while (cursor.moveToNext()) {
                Produto produto = new Produto();
                produto.setDescricao(cursor.getString(iDescricao));
                produto.setPrecoVenda(cursor.getString(iPrecoVenda));
                listProduto.add(produto);
            }
        }
        return listProduto;
    }

    @Override
    public void atualizar(Produto produto) {

    }

    @Override
    public void excluir(Produto produto) {

    }


}
