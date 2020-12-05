package com.programacao.app_pizzaria.usuario;

import android.content.Intent;
import android.os.Bundle;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.Util;
import com.programacao.app_pizzaria.produto.Produto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class cadUsuarioActivity extends AppCompatActivity {

    Button btSalvar, btLimpar, btCancelar;
    EditText nomeCompletoE;
    EditText emailE;
    EditText telefoneE;
    RadioGroup funcaoGroup;
    RadioButton radioButton;
    Intent intent;
    Usuario usuario;

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
        funcaoGroup = findViewById(R.id.funcao);
        int idRadioGroupSelecionado = funcaoGroup.getChildAt(0).getId();
        radioButton = funcaoGroup.findViewById(idRadioGroupSelecionado);
        radioButton.setSelected(true);
    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                salvar();
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
        String nomeCompleto = nomeCompletoE.getText().toString();
        String email = emailE.getText().toString();
        String telefone = telefoneE.getText().toString();

        String funcao = radioButton.getText().toString();

        usuario = new Usuario();
        usuario.setNome(nomeCompleto);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setFuncao(funcao);
        if(validaUsuario(usuario)) {
            //insert
            UsuarioDAO.getInstance().adicionar(usuario);
        }

    }

    public boolean validaUsuario(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADUSUARIO_VALIDA_NOME);
            return false;
        } else if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADUSUARIO_VALIDA_EMAIL);
            return false;
        } else if (usuario.getTelefone() == null || usuario.getTelefone().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADUSUARIO_VALIDA_TELEFONE);
            return false;
        } else if (usuario.getFuncao() == null || usuario.getFuncao().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADUSUARIO_VALIDA_FUNCAO);
            return false;
        }
        return true;
    }
}