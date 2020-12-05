package com.programacao.app_pizzaria.produto;

import android.os.Bundle;

import com.programacao.app_pizzaria.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listProdutoActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private List<Produto> produtoList;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarListView();

    }

    private void inicializarListView() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.clear();
        ListView listView = findViewById(R.id.listProduto);
        listView.setAdapter(adapter);
        produtoList = new ArrayList<Produto>();
        produtoList = ProdutoDAO.getInstance().listar();
        int size = produtoList.size();
        for (int i = 0; i < size; i++) {
            produto = (Produto) produtoList.get(i);
            this.adapter.add(produto.getDescricao() + " R$ : " + produto.getPrecoVenda());
        }
        adapter.notifyDataSetChanged();


    }
}