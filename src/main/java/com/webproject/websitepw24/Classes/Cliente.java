package com.webproject.websitepw24.Classes;

public class Cliente {
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    public Cliente(Integer id, String nome, String email, String senha){
        this.id    = id;
        this.nome  = nome;
        this.email = email;
        this.senha = senha;

    }
    public Cliente(){}

    //Metodos getters e setters
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    @Override
    public String toString(){
        return "Cliente{ "+
                "id = "+ this.id+
                "|nome = "+this.nome+
                "|email = "+this.email+
                "}";
    }
}
