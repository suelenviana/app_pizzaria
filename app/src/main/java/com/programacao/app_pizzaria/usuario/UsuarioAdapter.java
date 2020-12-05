package com.programacao.app_pizzaria.usuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.programacao.app_pizzaria.R;

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
        
        return view;
    }
}
