package com.example.livros_projeto_app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "biblioteca.db";
    private static final int DB_VERSION = 1;

    public SQLiteHelper(Context ctx) { super(ctx, DB_NAME, null, DB_VERSION); }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE livro (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "ano INTEGER," +
                "preco REAL," +
                "disponivel INTEGER NOT NULL DEFAULT 0)");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS livro");
        onCreate(db);
    }
}
