package com.example.livros_projeto_app.model;

public class Livro {
    private long id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private double preco;
    private boolean disponivel;

    public Livro() {}
    public Livro(String titulo, String autor, int anoPublicacao, double preco, boolean disponivel) {
        this.titulo = titulo; this.autor = autor;
        this.anoPublicacao = anoPublicacao; this.preco = preco; this.disponivel = disponivel;
    }

    public long getId() { return id; }              public void setId(long id) { this.id = id; }
    public String getTitulo() { return titulo; }    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }      public void setAutor(String autor) { this.autor = autor; }
    public int getAnoPublicacao() { return anoPublicacao; }  public void setAnoPublicacao(int a) { this.anoPublicacao = a; }
    public double getPreco() { return preco; }      public void setPreco(double preco) { this.preco = preco; }
    public boolean isDisponivel() { return disponivel; } public void setDisponivel(boolean d) { this.disponivel = d; }
}
