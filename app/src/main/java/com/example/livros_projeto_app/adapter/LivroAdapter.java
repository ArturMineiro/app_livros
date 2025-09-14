package com.example.livros_projeto_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.livros_projeto_app.R;
import com.example.livros_projeto_app.model.Livro;

import java.util.List;

public class LivroAdapter extends BaseAdapter {

    public interface OnRowActionListener {
        void onEdit(Livro l);
        void onDelete(Livro l);
    }

    private final Context ctx;
    private final List<Livro> data;
    private final OnRowActionListener listener;

    public LivroAdapter(Context ctx, List<Livro> data, OnRowActionListener listener) {
        this.ctx = ctx;
        this.data = data;
        this.listener = listener;
    }

    @Override public int getCount() { return data.size(); }
    @Override public Object getItem(int position) { return data.get(position); }
    @Override public long getItemId(int position) { return data.get(position).getId(); }

    static class VH {
        TextView titulo, autorAno, precoDisp;
        ImageButton btnEditar, btnExcluir;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH h;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_livro, parent, false);
            h = new VH();
            h.titulo = convertView.findViewById(R.id.txtTitulo);
            h.autorAno = convertView.findViewById(R.id.txtAutorAno);
            h.precoDisp = convertView.findViewById(R.id.txtPrecoDisp);
            h.btnEditar = convertView.findViewById(R.id.btnEditar);
            h.btnExcluir = convertView.findViewById(R.id.btnExcluir);
            convertView.setTag(h);
        } else {
            h = (VH) convertView.getTag();
        }

        final Livro l = data.get(position);
        h.titulo.setText(l.getTitulo());
        h.autorAno.setText(l.getAutor() + " • " + l.getAnoPublicacao());
        h.precoDisp.setText(String.format("R$ %.2f • %s", l.getPreco(), l.isDisponivel() ? "Disp." : "Indisp."));

        h.btnEditar.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(l);
        });

        h.btnExcluir.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(l);
        });

        return convertView;
    }
}
