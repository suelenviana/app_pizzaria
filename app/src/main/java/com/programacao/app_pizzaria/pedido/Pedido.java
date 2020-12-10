package com.programacao.app_pizzaria.pedido;

public class Pedido {

    private int id;
    private String nomeUsuario;
    private String formaPagamento;
    private String realizarEntrega;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getRealizarEntrega() {
        return realizarEntrega;
    }

    public void setRealizarEntrega(String realizarEntrega) {
        this.realizarEntrega = realizarEntrega;
    }

}
