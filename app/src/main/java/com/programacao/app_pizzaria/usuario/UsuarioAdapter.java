package com.programacao.app_pizzaria.usuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.Util;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    public UsuarioAdapter(@NonNull Context context) {
        super(context, R.layout.usuario, new ArrayList<>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.usuario, parent, false);

        Usuario usuario = getItem(position);

        TextView nome = view.findViewById(R.id.nome);
        TextView email = view.findViewById(R.id.email);
        TextView telefone = view.findViewById(R.id.telefone);
        TextView funcao = view.findViewById(R.id.funcao);

        ImageButton excluir = view.findViewById(R.id.excluir);
        ImageButton editar = view.findViewById(R.id.editar);

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Confirmação")
                        .setMessage("Deseja excluir o contato?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Exclui do banco de dados
                                UsuarioDAO.getInstance().excluir(usuario);

                                // Lista os contatos atualizados
                                ListaUsuarioActivity activity = (ListaUsuarioActivity) getContext();
                                activity.listarUsuarios();
                                Util.getInstance().mostraMensagem(activity.getBaseContext(), "Excluído");
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());
        telefone.setText(usuario.getTelefone());
        funcao.setText(usuario.getFuncao());
        
        return view;
    }
}
