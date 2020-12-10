package com.programacao.app_pizzaria.pedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.programacao.app_pizzaria.R;
import com.programacao.app_pizzaria.Util.DataTransferInterface;

import java.util.ArrayList;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    DataTransferInterface dtInterface;

    public PedidoAdapter(@NonNull Context context) {
        super(context, R.layout.activity_lista_pedido, new ArrayList<>());
        this.dtInterface = (DataTransferInterface) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.pedido, parent, false);

        Pedido pedido = getItem(position);


        TextView idPedido = view.findViewById(R.id.idPedido);
        TextView itemPedido = view.findViewById(R.id.itemPedido);
        TextView formaPagamento = view.findViewById(R.id.FormaPagamento);
        TextView teleEntrega = view.findViewById(R.id.teleEntrega);

        idPedido.setText(String.valueOf(pedido.getId()));
        itemPedido.setText("So Deus sabe");
        formaPagamento.setText(pedido.getFormaPagamento());
        teleEntrega.setText(pedido.getRealizarEntrega());

        return view;
    }
}
