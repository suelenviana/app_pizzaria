package com.programacao.app_pizzaria.produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.programacao.app_pizzaria.MainActivity;
import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.Util;

public class cadProdutoActivity extends AppCompatActivity {

    private Button btSalvar, btLimpar, btCancelar;
    private Intent intent;
    private Produto produto;
    private EditText descricao;
    private EditText precoVenda;
    private EditText precoCusto;
    private RadioGroup tipoProduto;
    private int idRadioGroupSelecionado;
    View radioButton;

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
    }

    public boolean validaProduto(Produto produto) {
        if (produto.getDescricao() == null || produto.getDescricao().isEmpty()) {
            Util.getInstance().mostraMensagem(Util.CADPRODUTO_VALIDA_DESCRICAO);
            return false;
        } else if (produto.getPrecoVenda() == null || produto.getPrecoVenda().isEmpty()) {
            Util.getInstance().mostraMensagem(Util.CADPRODUTO_VALIDA_PRECO_VENDA);
            return false;
        } else if (produto.getPrecoCusto() == null || produto.getPrecoCusto().isEmpty()) {
            Util.getInstance().mostraMensagem(Util.CADPRODUTO_VALIDA_PRECO_CUSTO);
            return false;
        } else if (produto.getTipoProduto() == null || produto.getTipoProduto().isEmpty()) {
            Util.getInstance().mostraMensagem(Util.CADPRODUTO_VALIDA_TIPO_PRODUTO);
            return false;
        }
        return true;
    }


    public void salvar() {
        produto = new Produto();
        produto.setDescricao(descricao.getText().toString());
        produto.setPrecoVenda(precoVenda.getText().toString());
        produto.setPrecoCusto(precoVenda.getText().toString());
        idRadioGroupSelecionado = tipoProduto.getCheckedRadioButtonId();
        radioButton = tipoProduto.findViewById(idRadioGroupSelecionado);
        produto.setTipoProduto(String.valueOf(tipoProduto.indexOfChild(radioButton)));
        if (validaProduto(produto)) {
            System.out.println("Produto salvo");
            //insert aqui.
        }
    }


    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.btSalvar:
                salvar();
            case R.id.btLimpar:
                break;
            case R.id.btCancelar:
                intent = new Intent(cadProdutoActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}