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
import com.programacao.app_pizzaria.Util.DataTransferInterface;
import com.programacao.app_pizzaria.Util.Util;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    DataTransferInterface dtInterface;

    public UsuarioAdapter(@NonNull Context context) {
        super(context, R.layout.usuario, new ArrayList<>());
        this.dtInterface = (DataTransferInterface) context;
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
                        .setTitle(Util.MSG_TITULO)
                        .setMessage(Util.MSG_EXCLUIR_USUARIO)
                        .setPositiveButton(Util.MSG_SIM, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UsuarioDAO.getInstance().excluir(usuario);
                                ListaUsuarioActivity activity = (ListaUsuarioActivity) getContext();
                                activity.listarUsuarios();
                                Util.getInstance().mostraMensagem(activity.getBaseContext(), Util.MSG_EXCLUIDO);
                            }
                        })
                        .setNegativeButton(Util.MSG_NAO, null)
                        .show();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtInterface.onEditar(usuario);
            }
        });
        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());
        telefone.setText(usuario.getTelefone());
        funcao.setText(usuario.getFuncao());
        
        return view;
    }
}
