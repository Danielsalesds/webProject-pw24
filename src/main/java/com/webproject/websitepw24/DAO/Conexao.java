package com.webproject.websitepw24.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Atributos para configuração da conexão
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";
    //Metodo para conectar ao banco de dados
    public static Connection getConnection() throws SQLException {
        Connection conexao = null;
        try {
            // Carregar o driver JDBC
            Class.forName("org.postgresql.Driver");
            // Estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL não encontrado!");
            e.printStackTrace();
        }
        return conexao;
    }
    public static void desconectar(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão!");
                e.printStackTrace();
            }
        }
    }

    
}
