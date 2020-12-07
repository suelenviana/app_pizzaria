package com.programacao.app_pizzaria.produto;

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

public class ProdutoAdapter extends ArrayAdapter<Produto> {

    DataTransferInterface dtInterface;

    public ProdutoAdapter(@NonNull Context context) {
        super(context, R.layout.produto, new ArrayList<>());
        this.dtInterface = (DataTransferInterface) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.produto, parent, false);

        Produto produto = getItem(position);

        TextView descricao = view.findViewById(R.id.descricao);
        TextView precoVenda = view.findViewById(R.id.precoVenda);
        TextView precoCusto = view.findViewById(R.id.precoCusto);

        ImageButton excluir = view.findViewById(R.id.excluir);
        ImageButton editar = view.findViewById(R.id.editar);

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle(Util.MSG_TITULO)
                        .setMessage(Util.MSG_EXCLUIR_PRODUTO)
                        .setPositiveButton(Util.MSG_SIM, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ProdutoDAO.getInstance().excluir(produto);
                                ListaProdutoActivity activity = (ListaProdutoActivity) getContext();
                                activity.listarProdutos();
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
                dtInterface.onEditar(produto);
            }
        });
        descricao.setText(produto.getDescricao());
        precoVenda.setText(produto.getPrecoVenda());
        precoCusto.setText(produto.getPrecoCusto());

        return view;
    }

}
