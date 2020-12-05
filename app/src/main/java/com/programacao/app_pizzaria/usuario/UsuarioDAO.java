package com.programacao.app_pizzaria.usuario;

import android.database.sqlite.SQLiteDatabase;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.banco.DAO;

import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {

    private static SQLiteDatabase bancoDados = ConexaoBancoDados.getInstance();
    private static UsuarioDAO instance;

    public static UsuarioDAO getInstance(){
        if (instance == null) return new UsuarioDAO();
        return instance;
    }

    @Override
    public void criarTabela() {
        String sql = new StringBuilder("")
                .append("CREATE TABLE IF NOT EXISTS usuarios (")
                .append("   id INTEGER PRIMARY KEY,")
                .append("   nomecompleto VARCHAR(250) NOT NULL,")
                .append("   email VARCHAR(50) NOT NULL,")
                .append("   telefone VARCHAR(25) NOT NULL,")
                .append("   funcao VARCHAR(100) NOT NULL")
                .append(")")
                .toString();

        bancoDados.execSQL(sql);
    }

    @Override
    public void adicionar(Usuario usuario) {
        String sql = new StringBuilder("INSERT INTO usuarios(nomecompleto, email, telefone, funcao) VALUES (\"")
                .append(usuario.getNome()).append("\", \"")
                .append(usuario.getEmail()).append("\", \"")
                .append(usuario.getTelefone()).append("\", \"")
                .append(usuario.getFuncao()).append("\")")
                .toString();

        bancoDados.execSQL(sql);
    }

    @Override
    public List<Object> listar() {
        return null;
    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public void excluir(Usuario usuario) {

    }
}
