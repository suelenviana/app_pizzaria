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
import com.programacao.app_pizzaria.usuario.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    DataTransferInterface dtInterface;

    public PedidoAdapter(@NonNull Context context) {
        super(context, R.layout.pedido, new ArrayList<>());
        this.dtInterface = (DataTransferInterface) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View view = layoutInflater.inflate(R.layout.pedido, parent, false);

        Pedido pedido = getItem(position);

        List<PedidoItem> itens = PedidoDAO.getInstance().listarPedidoItensPorPedidoId(pedido.getId());
        StringBuilder nomesProdutos = new StringBuilder();
        pedido.setItens(itens);

        for (PedidoItem i : itens) {
            nomesProdutos.append(i.getNomeProduto())
                    .append("; ");
        }
        TextView idPedido = view.findViewById(R.id.idPedido);
        TextView itemPedido = view.findViewById(R.id.itemPedido);
        TextView formaPagamento = view.findViewById(R.id.FormaPagamento);
        TextView teleEntrega = view.findViewById(R.id.teleEntrega);
        TextView nomeUsuario = view.findViewById(R.id.nomeUsuario);

        idPedido.setText(String.valueOf(pedido.getId()));
        itemPedido.setText(nomesProdutos);
        formaPagamento.setText(pedido.getFormaPagamento());
        teleEntrega.setText(pedido.getRealizarEntrega());
        nomeUsuario.setText(pedido.getNomeUsuario());

        ImageButton excluir = view.findViewById(R.id.excluir);
        ImageButton editar = view.findViewById(R.id.editar);

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle(Util.MSG_TITULO)
                        .setMessage(Util.MSG_EXCLUIR_PEDIDO)
                        .setPositiveButton(Util.MSG_SIM, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PedidoDAO.getInstance().excluir(pedido);
                                PedidoDAO.getInstance().removerItensPorIdPedido(pedido.getId());
                                ListaPedidoActivity activity = (ListaPedidoActivity) getContext();
                                activity.listarPedidos();
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
                dtInterface.onEditar(pedido);
            }
        });

        return view;
    }
}
