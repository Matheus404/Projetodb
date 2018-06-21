package br.com.ufc.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String ip = "localhost";
    private final Integer porta = 5432;
    private final String usuario = "postgres";
    private final String senha = "123";
    private final String banco = "projetodb";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://"+ip+":"+porta+"/"+banco, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
