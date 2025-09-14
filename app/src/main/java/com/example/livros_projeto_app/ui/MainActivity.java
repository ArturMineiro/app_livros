package com.example.livros_projeto_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.livros_projeto_app.R;
import com.example.livros_projeto_app.adapter.LivroAdapter;
import com.example.livros_projeto_app.data.LivroDAO;
import com.example.livros_projeto_app.model.Livro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LivroDAO dao;
    private final List<Livro> data = new ArrayList<>();
    private LivroAdapter adapter;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        dao = new LivroDAO(this);

        ListView list = findViewById(R.id.listViewLivros);
        adapter = new LivroAdapter(this, data, new LivroAdapter.OnRowActionListener() {
            @Override public void onEdit(Livro l) {
                Intent it = new Intent(MainActivity.this, LivroFormActivity.class);
                it.putExtra("edit_id", l.getId());
                startActivity(it);
            }

            @Override public void onDelete(Livro l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Excluir")
                        .setMessage("Excluir \"" + l.getTitulo() + "\"?")
                        .setPositiveButton("Sim", (d, w) -> { dao.deletar(l.getId()); load(); })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
        list.setAdapter(adapter);

        load();

        ((FloatingActionButton)findViewById(R.id.fabAdd)).setOnClickListener(v ->
                startActivity(new Intent(this, LivroFormActivity.class))
        );

        // Se quiser manter clique do item para editar:
        list.setOnItemClickListener((AdapterView<?> p, View v, int pos, long id) -> {
            Livro l = data.get(pos);
            Intent it = new Intent(this, LivroFormActivity.class);
            it.putExtra("edit_id", l.getId());
            startActivity(it);
        });

        // Long click para excluir (opcional, já tem botão):
        list.setOnItemLongClickListener((p, v, position, id) -> {
            Livro l = data.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir")
                    .setMessage("Excluir \"" + l.getTitulo() + "\"?")
                    .setPositiveButton("Sim", (d, w) -> { dao.deletar(l.getId()); load(); })
                    .setNegativeButton("Não", null)
                    .show();
            return true;
        });
    }

    @Override protected void onResume() { super.onResume(); load(); }

    private void load() {
        data.clear();
        data.addAll(dao.listar());
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}
