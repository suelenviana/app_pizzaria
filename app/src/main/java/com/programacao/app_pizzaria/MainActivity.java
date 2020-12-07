package com.programacao.app_pizzaria;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.pedido.novoPedidoActivity;
import com.programacao.app_pizzaria.produto.ProdutoDAO;
import com.programacao.app_pizzaria.produto.cadProdutoActivity;
import com.programacao.app_pizzaria.produto.ListaProdutoActivity;
import com.programacao.app_pizzaria.usuario.ListaUsuarioActivity;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;
import com.programacao.app_pizzaria.usuario.cadUsuarioActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btNovoPedido, btCadProduto, btListProduto, btCadUsuario, btListUsuario;
    Intent intent;
    private SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarConexaoBancoDados();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btNovoPedido = findViewById(R.id.button_novoPedido);
        btCadProduto = findViewById(R.id.button_cadProduto);
        btListProduto = findViewById(R.id.button_listProduto);
        btCadUsuario = findViewById(R.id.button_cadUsuario);
        btListUsuario = findViewById(R.id.button_listUsuario);
        ProdutoDAO.getInstance().criarTabela();
        UsuarioDAO.getInstance().criarTabela();

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
                intent = new Intent(MainActivity.this, ListaProdutoActivity.class);
                startActivity(intent);
                break;
            case R.id.button_listUsuario:
                intent = new Intent(MainActivity.this, ListaUsuarioActivity.class);
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

    private void criarConexaoBancoDados() {
        try {
            // Abertura da conexão
            bancoDados = this.openOrCreateDatabase(ConexaoBancoDados.NOME_BANCO_DADOS, MODE_PRIVATE, null);
            ConexaoBancoDados conexao = new ConexaoBancoDados(bancoDados);
        } catch(Exception e) {
            finish();
        }
    }
}