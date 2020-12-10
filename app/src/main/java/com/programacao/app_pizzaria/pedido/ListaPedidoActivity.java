package com.programacao.app_pizzaria.pedido;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.DataTransferInterface;
import com.programacao.app_pizzaria.Util.Util;
import com.programacao.app_pizzaria.usuario.ListaUsuarioActivity;
import com.programacao.app_pizzaria.usuario.Usuario;
import com.programacao.app_pizzaria.usuario.UsuarioAdapter;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;
import com.programacao.app_pizzaria.usuario.cadUsuarioActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaPedidoActivity extends AppCompatActivity implements DataTransferInterface<Pedido> {

    List<Pedido> pedidoList;
    PedidoAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar1);
       // setSupportActionBar(toolbar);

        inicializarListView();
        listarPedidos();
    }

    private void inicializarListView() {
        adapter = new PedidoAdapter(this);
        adapter.clear();
        ListView listView = findViewById(R.id.listPedido);
        listView.setAdapter(adapter);
        pedidoList = new ArrayList<Pedido>();
        pedidoList = PedidoDAO.getInstance().listar();
        adapter.notifyDataSetChanged();
    }

    public void listarPedidos() {
        // Limpar a lista
        adapter.clear();

        // Pego os contatos do banco
        List<Pedido> pedidos = PedidoDAO.getInstance().listar();


        // Adicionar os contatos ao adapter
        this.adapter.addAll(pedidos);

        // Notificar as mudanças de dados para o ListView
        this.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Util.REQUEST_CODE_EDITAR) {
//            if (resultCode == RESULT_OK) {
//                listarPedidos();
//            }
//        }
    }

    @Override
    public void onEditar(Pedido pedido) {

    }

    @Override
    public void onExcluir(Pedido pedido) {
    }
}