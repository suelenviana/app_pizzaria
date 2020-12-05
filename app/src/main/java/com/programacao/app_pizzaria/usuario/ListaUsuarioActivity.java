package com.programacao.app_pizzaria.usuario;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.produto.Produto;
import com.programacao.app_pizzaria.produto.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarioActivity extends AppCompatActivity {
    UsuarioAdapter adapter;
    List<Usuario> usuarioList;
    Usuario usuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inicializarListView();
    }

    private void inicializarListView() {
        adapter = new UsuarioAdapter(this);
        adapter.clear();
        ListView listView = findViewById(R.id.listProduto);
        listView.setAdapter(adapter);
        usuarioList = new ArrayList<Usuario>();
        usuarioList = UsuarioDAO.getInstance().listar();
        adapter.notifyDataSetChanged();
    }

    private void listarUsuarios() {
        // Limpar a lista
        adapter.clear();

        // Pego os contatos do banco
        List<Usuario> contatos = UsuarioDAO.getInstance().listar();

        // Adicionar os contatos ao adapter
        this.adapter.addAll(contatos);

        // Notificar as mudanças de dados para o ListView
        this.adapter.notifyDataSetChanged();
    }
}
