package com.webproject.websitepw24.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.webproject.websitepw24.Classes.Produto;

public class ProdutoDAO {
    private String UPDATE_ESTOQUE="update Produtos set estoque=? where id=?";
    private String BUSC_ID= "select * from Produtos where id = ?";
    private String LIST_PRODUTO="select * from Produtos";
    private String CADASTRAR="insert into Produtos (preco ,nome, descricao, estoque) values (?,?,?,?)";

    public void UpdateEstoqueProduto(int estoque,int id) throws URISyntaxException{
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = Conexao.getConnection();

            statement = connection.prepareStatement(UPDATE_ESTOQUE);
            statement.setInt(1,estoque);
            statement.setInt(2,id);
            statement.executeUpdate();
            connection.close();
        }catch(SQLException ex){
            System.out.println("Falha na Conexão! Check output console" + ex.getMessage());
        }
    }
    public Produto getProdutoById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(BUSC_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                produto = new Produto(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"),rs.getInt("preco"),rs.getInt("estoque"));
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return produto;
    }

    public List<Produto> listarTodosProduto() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Produto> listaDeProdutos = new ArrayList<>();

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(LIST_PRODUTO);
            rs = statement.executeQuery();
            while (rs.next()) {
                Produto produtos = new Produto(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"),rs.getInt("preco"),rs.getInt("estoque"));
                listaDeProdutos.add(produtos);
            }
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
        return listaDeProdutos;
    }
    public void cadastrarProduto(Produto produto) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conexao.getConnection();
            statement = connection.prepareStatement(CADASTRAR);

            statement.setInt(1,produto.getPreco());
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getDescricao());
            statement.setInt(4,produto.getEstoque());

            statement.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Check output console" + ex.getMessage());
        }
    }
}
