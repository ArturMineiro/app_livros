package com.example.livros_projeto_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.livros_projeto_app.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private final SQLiteHelper helper;
    public LivroDAO(Context ctx) { this.helper = new SQLiteHelper(ctx); }

    public long inserir(Livro l) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("titulo", l.getTitulo());
        v.put("autor", l.getAutor());
        v.put("ano", l.getAnoPublicacao());
        v.put("preco", l.getPreco());
        v.put("disponivel", l.isDisponivel()?1:0);
        long id = db.insert("livro", null, v);
        db.close(); return id;
    }

    public int atualizar(Livro l) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("titulo", l.getTitulo()); v.put("autor", l.getAutor());
        v.put("ano", l.getAnoPublicacao()); v.put("preco", l.getPreco());
        v.put("disponivel", l.isDisponivel()?1:0);
        int rows = db.update("livro", v, "id=?", new String[]{String.valueOf(l.getId())});
        db.close(); return rows;
    }

    public int deletar(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rows = db.delete("livro", "id=?", new String[]{String.valueOf(id)});
        db.close(); return rows;
    }

    public List<Livro> listar() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("livro", null, null, null, null, null, "titulo ASC");
        List<Livro> list = new ArrayList<>();
        while (c.moveToNext()) {
            Livro l = new Livro();
            l.setId(c.getLong(c.getColumnIndexOrThrow("id")));
            l.setTitulo(c.getString(c.getColumnIndexOrThrow("titulo")));
            l.setAutor(c.getString(c.getColumnIndexOrThrow("autor")));
            l.setAnoPublicacao(c.getInt(c.getColumnIndexOrThrow("ano")));
            l.setPreco(c.getDouble(c.getColumnIndexOrThrow("preco")));
            l.setDisponivel(c.getInt(c.getColumnIndexOrThrow("disponivel"))==1);
            list.add(l);
        }
        c.close(); db.close();
        return list;
    }
}
