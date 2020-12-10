package com.programacao.app_pizzaria.pedido;

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
import com.programacao.app_pizzaria.usuario.ListaUsuarioActivity;
import com.programacao.app_pizzaria.usuario.Usuario;
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class NomeProdutoAdapter extends ArrayAdapter<String> {

    DataTransferInterface dtInterface;

    public NomeProdutoAdapter(@NonNull Context context, List<String> nomes) {
        super(context, R.layout.nome_produto, nomes);
        this.dtInterface = (DataTransferInterface) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.nome_produto, parent, false);

        String nomeProduto = getItem(position);

        TextView nome = view.findViewById(R.id.nomeproduto);

        ImageButton excluir = view.findViewById(R.id.excluir);

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dtInterface.onExcluir(position);
            }
        });

        nome.setText(nomeProduto);

        return view;
    }
}
