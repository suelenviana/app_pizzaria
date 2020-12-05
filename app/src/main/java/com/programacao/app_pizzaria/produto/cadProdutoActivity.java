package com.programacao.app_pizzaria.produto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;

public class cadProdutoActivity extends AppCompatActivity {

    Button btSalvar, btLimpar, btCancelar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);
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
                intent = new Intent(cadProdutoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}