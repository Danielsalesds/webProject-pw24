package com.webproject.websitepw24.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webproject.websitepw24.Classes.Cliente;
//ATUALIZANDO CLIENTE NO BANCO DE DADOS
public class ClienteDAO {
    private String UPDATE   = "update clientes set nome=?, email=?, senha=? where id=?";
    private String BUSCA_ID = "select * from clientes where id = ?";
    private String LIST_CLIENTE = "select * from clientes";
    private String CADASTRAR = "insert into clientes (nome, email, senha) values (?,?,?)";
    private String VERIFICA_LOGIN = "select * from clientes where email = ? and senha = ?";
//ATUALIZAR DADOS DO CLIENTE NO BABNCO DE DADOS
    public void clienteUpdate(Cliente cliente){
        Connection connection       = null;
        PreparedStatement statement = null;
        try{
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1,cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setInt(4, cliente.getId());
            statement.executeUpdate();
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
    }
//BUSCNDO UM CLIENTE PELO ID
    public Cliente getCliente(Integer id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(BUSCA_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                cliente = new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString(" email"),rs.getString("senha"));
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return cliente;
    }
//UMA LISTA COM TODOS OS CLIENTE DO BANCO DE DDOS
    public List<Cliente> clienteList(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs =null;
        List<Cliente> clienteLista = new ArrayList<>();
        
        try{
            connection = Conexao.getConnection();
            statement   = connection.prepareStatement(LIST_CLIENTE);
            rs = statement.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("senha"));
                clienteLista.add(cliente);
            }
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return clienteLista;
    }
//CADASTRAR CLIENTE NO BANCO DE DADOS
    public void cadastrarCliente(Cliente cliente) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(CADASTRAR);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
    }
//VERIFICAR LOGIN DO CLIENTE
    public boolean verificarLogin(String email, String senha){
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
