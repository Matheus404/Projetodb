package br.com.ufc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.ufc.db.jdbc.ConnectionFactory;
import br.com.ufc.db.pojo.Cliente;

public class ClienteDAO {
    private Connection conexao;
    
    public ClienteDAO() {}
    
    public boolean addCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(idcliente, nome, email) VALUES(?, ?, ?)";
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            
            int qtdLinhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if (qtdLinhasAfetadas > 0)
                return true;
            return false;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                this.conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean deleteCli(int id) {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";
        this.conexao = new ConnectionFactory().getConnection();
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, id);
            
            int qtdLinhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if (qtdLinhasAfetadas > 0)
                return true;
            return false;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                this.conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public ArrayList<Cliente> listaCliente() {
        String sql = "SELECT * FROM cliente";
        ArrayList<Cliente> lista_cliete = new ArrayList<Cliente>();
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcliente");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                
                Cliente cliente = new Cliente(id, nome, email);
                
                lista_cliete.add(cliente);
            }
            stmt.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                this.conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return lista_cliete;
    }
    
    public boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ? WHERE idcliente = ?";
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setInt(3, cliente.getId());
            
            int qtdLinhasAfetadas = stmt.executeUpdate();
            stmt.close();
            
            if (qtdLinhasAfetadas > 0)
                return true;
            return false;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                this.conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public Cliente pegarCliente(int cliente_idcliente) {
        String sql = "SELECT * FROM cliente WHERE idcliente = ?;";
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, cliente_idcliente);
            
            //stmt.execute();
            
            ResultSet rs =  stmt.executeQuery();
            rs.next();
            
            Cliente cliente = new Cliente(cliente_idcliente, rs.getString("nome"), rs.getString("email"));
            
            stmt.close();
            return cliente;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                this.conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return null;
        
    }
    
    
    
}
