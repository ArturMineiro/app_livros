package com.example.livros_projeto_app.ui;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livros_projeto_app.R;
import com.example.livros_projeto_app.data.LivroDAO;
import com.example.livros_projeto_app.model.Livro;

public class LivroFormActivity extends AppCompatActivity {
    private EditText edtTitulo, edtAutor, edtAno, edtPreco; private CheckBox chkDisp;
    private LivroDAO dao; private Long editId = null;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b); setContentView(R.layout.activity_livro_form);
        dao = new LivroDAO(this);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtAutor = findViewById(R.id.edtAutor);
        edtAno = findViewById(R.id.edtAno);
        edtPreco = findViewById(R.id.edtPreco);
        chkDisp = findViewById(R.id.chkDisponivel);
        Button btnSalvar = findViewById(R.id.btnSalvar);

        if (getIntent().hasExtra("edit_id")) {
            editId = getIntent().getLongExtra("edit_id", -1);
            for (Livro l : dao.listar()) if (l.getId()==editId) { // simples; em projeto real busque por id
                edtTitulo.setText(l.getTitulo());
                edtAutor.setText(l.getAutor());
                edtAno.setText(String.valueOf(l.getAnoPublicacao()));
                edtPreco.setText(String.valueOf(l.getPreco()));
                chkDisp.setChecked(l.isDisponivel());
                break;
            }
        }

        btnSalvar.setOnClickListener(v -> {
            Livro l = new Livro(
                    edtTitulo.getText().toString(),
                    edtAutor.getText().toString(),
                    parseInt(edtAno.getText().toString()),
                    parseDouble(edtPreco.getText().toString()),
                    chkDisp.isChecked()
            );
            if (editId == null) {
                dao.inserir(l);
            } else {
                l.setId(editId);
                dao.atualizar(l);
            }
            finish();
        });
    }

    private int parseInt(String s){ try { return Integer.parseInt(s); } catch (Exception e){return 0;} }
    private double parseDouble(String s){ try { return Double.parseDouble(s); } catch (Exception e){return 0.0;} }
}
