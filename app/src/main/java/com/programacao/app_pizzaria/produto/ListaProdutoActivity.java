package com.programacao.app_pizzaria.produto;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.DataTransferInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutoActivity extends AppCompatActivity implements DataTransferInterface {

    ProdutoAdapter adapter;
    private List<Produto> produtoList;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        inicializarListView();
        listarProdutos();

    }

    private void inicializarListView() {
        adapter = new ProdutoAdapter(this);
        adapter.clear();
        ListView listView = findViewById(R.id.listProduto);
        listView.setAdapter(adapter);
        produtoList = new ArrayList<Produto>();
        produtoList = ProdutoDAO.getInstance().listar();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditar(Object object) {
        Produto produto = (Produto) object;
        Intent intent = new Intent(ListaProdutoActivity.this, cadProdutoActivity.class);
        intent.putExtra("id", produto.getId());
        intent.putExtra("descricao", produto.getDescricao());
        intent.putExtra("precoVenda", produto.getPrecoVenda());
        intent.putExtra("precoCusto", produto.getPrecoCusto());
        intent.putExtra("tipoProduto", produto.getTipoProduto());
        intent.putExtra("emEdicao", true);

        startActivity(intent);
    }
    public void listarProdutos() {
        // Limpar a lista
        adapter.clear();

        // Pego os contatos do banco
        List<Produto> produtos = ProdutoDAO.getInstance().listar();

        // Adicionar os contatos ao adapter
        this.adapter.addAll(produtos);

        // Notificar as mudanças de dados para o ListView
        this.adapter.notifyDataSetChanged();
    }
}