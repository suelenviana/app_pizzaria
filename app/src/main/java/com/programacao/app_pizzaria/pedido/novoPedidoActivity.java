package com.programacao.app_pizzaria.pedido;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class novoPedidoActivity extends AppCompatActivity {

    Button btSalvar, btLimpar, btCancelar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btSalvar = findViewById(R.id.btSalvar);
        btLimpar = findViewById(R.id.btLimpar);
        btCancelar = findViewById(R.id.btCancelar);
    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                break;
            case R.id.btLimpar:
                break;
            case R.id.btCancelar:
                intent = new Intent(novoPedidoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}