package com.webproject.websitepw24.Classes;

public class Lojista {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    public Lojista(Integer id,String nome,String email,String senha){
        this.id = id;
        this.nome = nome;
        this.email=email;
        this.senha=senha;
    }
    public Lojista(){}
//Metodos setters e getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Lojista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email=" + email +
                '}';
    }
}
