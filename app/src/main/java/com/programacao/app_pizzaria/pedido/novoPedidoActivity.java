package com.programacao.app_pizzaria.pedido;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.produto.Produto;
import com.programacao.app_pizzaria.produto.ProdutoDAO;
import com.programacao.app_pizzaria.usuario.Usuario;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class novoPedidoActivity extends AppCompatActivity {

    Button btSalvar, btLimpar, btCancelar;
    Intent intent;
    Spinner spinnerUsuario, spinnerItem;
    ListView listaItens;
    List<String> produtosSelecionados;
    ArrayAdapter<String> adapterProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btSalvar = findViewById(R.id.btSalvar);
        btLimpar = findViewById(R.id.btLimpar);
        btCancelar = findViewById(R.id.btCancelar);
        spinnerUsuario = findViewById(R.id.spinnerUsuario);
        spinnerItem = findViewById(R.id.spinnerItem);
        listaItens = findViewById(R.id.listaItens);

        produtosSelecionados = new ArrayList<>();
        adapterProduto = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, produtosSelecionados);
        listaItens.setAdapter(adapterProduto);
        adapterProduto.notifyDataSetChanged();
        List<Usuario> usuarios = UsuarioDAO.getInstance().listar();
        List<String> nomesUsuario = new ArrayList<>();
        for (Usuario u : usuarios) {
            nomesUsuario.add(u.getNome());
        }
        ArrayAdapter<String> adapterUsuario = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nomesUsuario);
        adapterUsuario.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adapterUsuario);

        List<Produto> produtos = ProdutoDAO.getInstance().listar();
        List<String> nomesProdutos = new ArrayList<>();
        nomesProdutos.add("");
        for (Produto p : produtos) {
            nomesProdutos.add(p.getDescricao());
        }
        ArrayAdapter<String> adapterItem = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nomesProdutos);
        adapterItem.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerItem.setAdapter(adapterItem);
        spinnerItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if (item != null && !item.equals("")) {
                    produtosSelecionados.add(item);
                    adapterProduto.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                break;
            case R.id.btLimpar:
                break;
            case R.id.btCancelar:
                finish();
                break;
        }
    }
}