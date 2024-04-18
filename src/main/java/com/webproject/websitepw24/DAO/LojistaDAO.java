package com.webproject.websitepw24.DAO;


import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webproject.websitepw24.Classes.Lojista;
//ATRIBUTOS PARA MANIPULAÇÃO DE DADOS NO BANCO
public class LojistaDAO {
    private String UPDATE         = "update lojistas set nome=?, email=?, senha=? where id=?";
    private String BUSCA_ID       = "select * from lojistas where id = ?";
    private String LIST_LOJISTA   = "select * from lojistas";
    private String CADASTRAR      = "insert into lojistas (nome, email, senha) values (?,?,?)";
    private String VERIFICA_LOGIN = "select * from lojistas where email = ? and senha = ?";
//ATUALIZAR DADOS DO CLIENTE NO BABNCO DE DADOS
    public void lojistaUpdate(Lojista lojista) throws URISyntaxException{
        Connection connection       = null;
        PreparedStatement statement = null;
        try{
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1,lojista.getNome());
            statement.setString(2, lojista.getEmail());
            statement.setString(3, lojista.getSenha());
            statement.setInt(4, lojista.getId());
            statement.executeUpdate();
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
    }
//BUSCNDO UM CLIENTE PELO ID
    public Lojista getLojista(Integer id) throws URISyntaxException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Lojista lojista= null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(BUSCA_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                lojista = new Lojista(rs.getInt("id"),rs.getString("nome"),rs.getString(" email"),rs.getString("senha"));
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return lojista;
    }
//UMA LISTA COM TODOS OS CLIENTE DO BANCO DE DDOS
    public List<Lojista> lojistaList() throws URISyntaxException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs =null;
        List<Lojista> lojistaLista = new ArrayList<>();
        
        try{
            connection = Conexao.getConnection();
            statement   = connection.prepareStatement(LIST_LOJISTA);
            rs = statement.executeQuery();
            while (rs.next()) {
                Lojista lojista = new Lojista(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"));
                lojistaLista.add(lojista);
            }
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return lojistaLista;
    }
//CADASTRAR CLIENTE NO BANCO DE DADOS
    public void cadastrarCliente(Lojista lojista) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(CADASTRAR);

            statement.setString(1, lojista.getNome());
            statement.setString(2, lojista.getEmail());
            statement.setString(3, lojista.getSenha());
            statement.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Connection Failed! Check output console" + ex.getMessage());
        }
    }
//VERIFICAR LOGIN DO CLIENTE
    public boolean verificarLogin(String email, String senha) throws URISyntaxException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean logado = false;

        try{
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(VERIFICA_LOGIN);
            statement.setString(1,email);
            statement.setString(2,senha);
            rs = statement.executeQuery();
            if(rs.next()){
                logado = true;
            }
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return logado;
    }
    
}
