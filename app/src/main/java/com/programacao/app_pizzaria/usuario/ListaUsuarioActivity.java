package com.programacao.app_pizzaria.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.DataTransferInterface;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarioActivity extends AppCompatActivity implements DataTransferInterface {
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
        listarUsuarios();
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

    public void listarUsuarios() {
        // Limpar a lista
        adapter.clear();

        // Pego os contatos do banco
        List<Usuario> contatos = UsuarioDAO.getInstance().listar();

        // Adicionar os contatos ao adapter
        this.adapter.addAll(contatos);

        // Notificar as mudanças de dados para o ListView
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void onEditarUsuario(Usuario usuario) {
        Intent intent = new Intent(ListaUsuarioActivity.this, cadUsuarioActivity.class);
        intent.putExtra("id", usuario.getId());
        intent.putExtra("nome", usuario.getNome());
        intent.putExtra("email", usuario.getEmail());
        intent.putExtra("telefone", usuario.getTelefone());
        intent.putExtra("funcao", usuario.getFuncao());
        intent.putExtra("emEdicao", true);

        startActivity(intent);
    }
}
