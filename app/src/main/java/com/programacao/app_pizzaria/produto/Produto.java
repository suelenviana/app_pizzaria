package com.programacao.app_pizzaria.produto;


public class Produto {

    private String descricao;
    private String tipoProduto;
    private String precoVenda;
    private String precoCusto;

    public Produto(String descricao, String tipoProduto, String precoVenda, String precoCusto) {
        this.descricao = descricao;
        this.tipoProduto = tipoProduto;
        this.precoVenda = precoVenda;
        this.precoCusto = precoCusto;
    }

    public Produto() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(String precoCusto) {
        this.precoCusto = precoCusto;
    }
}
