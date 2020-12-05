package com.programacao.app_pizzaria;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.pedido.novoPedidoActivity;
import com.programacao.app_pizzaria.produto.cadProdutoActivity;
import com.programacao.app_pizzaria.produto.listProdutoActivity;
import com.programacao.app_pizzaria.usuario.cadUsuarioActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btNovoPedido, btCadProduto, btListProduto, btCadUsuario;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btNovoPedido = findViewById(R.id.button_novoPedido);
        btCadProduto = findViewById(R.id.button_cadProduto);
        btListProduto = findViewById(R.id.button_listProduto);
        btCadUsuario = findViewById(R.id.button_cadUsuario);


    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.button_novoPedido:
                intent = new Intent(MainActivity.this, novoPedidoActivity.class);
                startActivity(intent);
                break;
            case R.id.button_cadProduto:
                intent = new Intent(MainActivity.this, cadProdutoActivity.class);
                startActivity(intent);
                break;
            case R.id.button_listProduto:
                intent = new Intent(MainActivity.this, listProdutoActivity.class);
                startActivity(intent);
                break;
            case R.id.button_cadUsuario:
                intent = new Intent(MainActivity.this, cadUsuarioActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}