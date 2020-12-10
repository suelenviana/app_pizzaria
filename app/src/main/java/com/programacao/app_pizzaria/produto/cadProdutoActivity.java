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
    private EditText descricaoE;
    private EditText precoVendaE;
    private EditText precoCustoE;
    private RadioGroup tipoProdutoR;
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
        descricaoE = findViewById(R.id.descricao);
        precoVendaE = findViewById(R.id.precoVenda);
        precoCustoE = findViewById(R.id.precoCusto);
        tipoProdutoR = findViewById(R.id.tipoPedido);

        int idRadioGroupSelecionado = tipoProdutoR.getChildAt(0).getId();
        radioButton = tipoProdutoR.findViewById(idRadioGroupSelecionado);
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
        String descricao = descricaoE.getText().toString();
        String precoVenda = precoVendaE.getText().toString();
        String precoCusto = precoCustoE.getText().toString();
        idRadioGroupSelecionado = tipoProdutoR.getCheckedRadioButtonId();
        radioButton = tipoProdutoR.findViewById(idRadioGroupSelecionado);
        String tipoProduto = String.valueOf(tipoProdutoR.indexOfChild(radioButton));

        if (emEdicao) {
            produto.setDescricao(descricao);
            produto.setPrecoVenda(precoVenda);
            produto.setPrecoCusto(precoCusto);
            produto.setTipoProduto(tipoProduto);
            ProdutoDAO.getInstance().atualizar(produto);
            limpar();
            Util.getInstance().mostraMensagem(getBaseContext(), Util.CADPRODUTO_ATUALIZAR_SUCESSO);
            intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            produto = new Produto();
            produto.setDescricao(descricao);
            produto.setPrecoVenda(precoVenda);
            produto.setPrecoCusto(precoCusto);
            produto.setTipoProduto(tipoProduto);
            if (validaProduto(produto)) {
                ProdutoDAO.getInstance().adicionar(produto);
                limpar();
                Util.getInstance().mostraMensagemSnackBar(findViewById(R.id.activity_cad_produto), Util.CADPRODUTO_SALVAR_SUCESSO);
            }
        }
    }

    public void limpar() {
        descricaoE.setText("");
        precoVendaE.setText("");
        precoCustoE.setText("");
        tipoProdutoR.check(-1);
    }

    @SuppressLint("ResourceType")
    public void preencherCamposEmEdicao(Produto produto) {
        descricaoE.setText(produto.getDescricao());
        precoVendaE.setText(produto.getPrecoVenda());
        precoCustoE.setText(produto.getPrecoCusto());
        if (produto.getTipoProduto().equals(Util.PRODUTO_TIPO_ALIMENTO)) {
            radioButton = findViewById(tipoProdutoR.getChildAt(0).getId());
            radioButton.setChecked(true);
        }
        else if (produto.getTipoProduto().equals(Util.PRODUTO_TIPO_BEBIDA)) {
            radioButton = findViewById(tipoProdutoR.getChildAt(1).getId());
            radioButton.setChecked(true);
        } else {
            radioButton = findViewById(tipoProdutoR.getChildAt(2).getId());
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
                finish();
                break;
        }
    }
}