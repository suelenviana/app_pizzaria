package com.programacao.app_pizzaria.usuario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.programacao.app_pizzaria.banco.ConexaoBancoDados;
import com.programacao.app_pizzaria.banco.DAO;
import com.programacao.app_pizzaria.produto.Produto;

import java.util.ArrayList;
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
    public List<Usuario> listar() {
        Cursor cursor = bancoDados.rawQuery(" SELECT id, nomecompleto, email, telefone, funcao  FROM usuarios", null);
        List<Usuario> listUsuario = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            int iId = cursor.getColumnIndex("id");
            int iNome = cursor.getColumnIndex("nomecompleto");
            int iEmail = cursor.getColumnIndex("email");
            int iTelefone = cursor.getColumnIndex("telefone");
            int iFuncao = cursor.getColumnIndex("funcao");
            while (cursor.moveToNext()) {
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(iId));
                usuario.setNome(cursor.getString(iNome));
                usuario.setEmail(cursor.getString(iEmail));
                usuario.setTelefone(cursor.getString(iTelefone));
                usuario.setFuncao(cursor.getString(iFuncao));
                listUsuario.add(usuario);
            }
        }
        return listUsuario;
    }

    @Override
    public void atualizar(Usuario usuario) {

    }

    @Override
    public void excluir(Usuario usuario) {
        String sql = new StringBuilder("DELETE FROM usuarios WHERE id = ")
                .append(usuario.getId())
                .toString();

        bancoDados.execSQL(sql);
    }
}
