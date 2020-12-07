package com.programacao.app_pizzaria.produto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.Util;
import com.programacao.app_pizzaria.usuario.Usuario;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

public class cadProdutoActivity extends AppCompatActivity {

    private Button btSalvar, btLimpar, btCancelar;
    private Intent intent;
    boolean emEdicao;
    private Produto produto;
    private EditText descricao;
    private EditText precoVenda;
    private EditText precoCusto;
    private RadioGroup tipoProduto;
    private int idRadioGroupSelecionado;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btSalvar = findViewById(R.id.btSalvar);
        btLimpar = findViewById(R.id.btLimpar);
        btCancelar = findViewById(R.id.btCancelar);
        descricao = findViewById(R.id.descricao);
        precoVenda = findViewById(R.id.precoVenda);
        precoCusto = findViewById(R.id.precoCusto);
        tipoProduto = findViewById(R.id.tipoPedido);

        int idRadioGroupSelecionado = tipoProduto.getChildAt(0).getId();
        radioButton = tipoProduto.findViewById(idRadioGroupSelecionado);
        radioButton.setChecked(true);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String descricao = intent.getStringExtra("descricao");
        String precoVenda = intent.getStringExtra("precoVenda");
        String precoCusto = intent.getStringExtra("precoCusto");
        String tipoProduto = intent.getStringExtra("tipoProduto");
        emEdicao = intent.getBooleanExtra("emEdicao", false);

        if (emEdicao) {
            produto = new Produto(id, descricao, precoVenda, precoCusto, tipoProduto);
            preencherCamposEmEdicao(produto);
        }

    }

    public void salvar() {
        produto = new Produto();
        produto.setDescricao(descricao.getText().toString());
        produto.setPrecoVenda(precoVenda.getText().toString());
        produto.setPrecoCusto(precoCusto.getText().toString());
        idRadioGroupSelecionado = tipoProduto.getCheckedRadioButtonId();
        radioButton = tipoProduto.findViewById(idRadioGroupSelecionado);
        produto.setTipoProduto(String.valueOf(tipoProduto.indexOfChild(radioButton)));
        if (validaProduto(produto)) {
            System.out.println("Produto salvo");
            ProdutoDAO.getInstance().adicionar(produto);
            limpar();
            //insert aqui.
        }
    }

    public void limpar() {
        descricao.setText("");
        precoVenda.setText("");
        precoCusto.setText("");
        tipoProduto.check(-1);
    }

    @SuppressLint("ResourceType")
    public void preencherCamposEmEdicao(Produto produto) {
        descricao.setText(produto.getDescricao());
        precoVenda.setText(produto.getPrecoVenda());
        precoCusto.setText(produto.getPrecoCusto());
        if (produto.getTipoProduto().equals(Util.PRODUTO_TIPO_ALIMENTO)) {
            radioButton = findViewById(tipoProduto.getChildAt(0).getId());
            radioButton.setChecked(true);
        }
        else if (produto.getTipoProduto().equals(Util.PRODUTO_TIPO_BEBIDA)) {
            radioButton = findViewById(tipoProduto.getChildAt(1).getId());
            radioButton.setChecked(true);
        } else {
            radioButton = findViewById(tipoProduto.getChildAt(2).getId());
            radioButton.setChecked(true);
        }
    }

    public boolean validaProduto(Produto produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADPRODUTO_VALIDA_DESCRICAO);
            return false;
        } else if (produto.getPrecoVenda() == null || produto.getPrecoVenda().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADPRODUTO_VALIDA_PRECO_VENDA);
            return false;
        } else if (produto.getPrecoCusto() == null || produto.getPrecoCusto().isEmpty()) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADPRODUTO_VALIDA_PRECO_CUSTO);
            return false;
        } else if (produto.getTipoProduto() == null || produto.getTipoProduto().isEmpty() || produto.getTipoProduto().equals("-1")) {
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADPRODUTO_VALIDA_TIPO_PRODUTO);
            return false;
        }
        return true;
    }


    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                salvar();
            case R.id.btLimpar:
                limpar();
                break;
            case R.id.btCancelar:
                intent = new Intent(cadProdutoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}