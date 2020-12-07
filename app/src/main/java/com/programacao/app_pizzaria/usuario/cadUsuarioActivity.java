package com.programacao.app_pizzaria.usuario;

import android.annotation.SuppressLint;
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
    boolean emEdicao;

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
        radioButton.setChecked(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String nome = intent.getStringExtra("nome");
        String email = intent.getStringExtra("email");
        String telefone = intent.getStringExtra("telefone");
        String funcao = intent.getStringExtra("funcao");
        emEdicao = intent.getBooleanExtra("emEdicao", false);

        if (emEdicao) {
            usuario = new Usuario(id, nome, email, telefone, funcao);
            preencherCamposEmEdicao(usuario);
        }
    }

    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                salvar();
                break;
            case R.id.btLimpar:
                limpar();
                break;
            case R.id.btCancelar:
                finish();
                break;
        }
    }

    public void salvar() {
        String nomeCompleto = nomeCompletoE.getText().toString();
        String email = emailE.getText().toString();
        String telefone = telefoneE.getText().toString();

        int idRadioGroupSelecionado = funcaoGroup.getCheckedRadioButtonId();
        radioButton = funcaoGroup.findViewById(idRadioGroupSelecionado);
        String funcao = radioButton.getText().toString();

        if(emEdicao) {
            usuario.setNome(nomeCompleto);
            usuario.setEmail(email);
            usuario.setTelefone(telefone);
            usuario.setFuncao(funcao);
            UsuarioDAO.getInstance().atualizar(usuario);
            limpar();
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADUSUARIO_ATUALIZAR_SUCESSO);
            intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            usuario = new Usuario();
            usuario.setNome(nomeCompleto);
            usuario.setEmail(email);
            usuario.setTelefone(telefone);
            usuario.setFuncao(funcao);
           if(validaUsuario(usuario)) {
               //insert
               UsuarioDAO.getInstance().adicionar(usuario);
               limpar();
               Util.getInstance().mostraMensagemSnackBar(findViewById(R.id.activity_cad_usuario), Util.CADUSUARIO_SALVAR_SUCESSO);
           }
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

    public void limpar() {
        nomeCompletoE.setText("");
        emailE.setText("");
        telefoneE.setText("");
        funcaoGroup.check(-1);
    }

    @SuppressLint("ResourceType")
    public void preencherCamposEmEdicao(Usuario usuario) {
        nomeCompletoE.setText(usuario.getNome());
        emailE.setText(usuario.getEmail());
        telefoneE.setText(usuario.getTelefone());
        if (usuario.getFuncao().equals(Util.USUARIO_FUNCAO_GERENTE)) {
            radioButton = findViewById(funcaoGroup.getChildAt(0).getId());
            radioButton.setChecked(true);
        }
        else if (usuario.getFuncao().equals(Util.USUARIO_FUNCAO_GARCOM)) {
            radioButton = findViewById(funcaoGroup.getChildAt(1).getId());
            radioButton.setChecked(true);
        }
    }
}