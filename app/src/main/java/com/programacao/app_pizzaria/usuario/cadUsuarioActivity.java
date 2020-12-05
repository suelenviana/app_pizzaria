package com.programacao.app_pizzaria.usuario;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cadUsuarioActivity extends AppCompatActivity {

    Button btSalvar, btLimpar, btCancelar;
    EditText nomeCompletoE;
    EditText emailE;
    EditText telefoneE;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btSalvar = findViewById(R.id.btSalvar);
        btLimpar = findViewById(R.id.btLimpar);
        btCancelar = findViewById(R.id.btCancelar);
        nomeCompletoE = findViewById(R.id.nome_ompleto);
        emailE = findViewById(R.id.email);
        telefoneE = findViewById(R.id.telefone);

    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:

                break;
            case R.id.btLimpar:
                break;
            case R.id.btCancelar:
                intent = new Intent(cadUsuarioActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void salvar() {

    }
}