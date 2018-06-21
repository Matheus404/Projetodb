package br.com.ufc.db.dao;

import java.sql.Connection;
import br.com.ufc.db.pojo.Produto;
import br.com.ufc.db.jdbc.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    private Connection conexao;
    
    public ProdutoDAO(){}
    
    public boolean addProduto(Produto produto){
        String sql = "INSERT INTO produto(idproduto, nome, descricao, valor) VALUES (?, ?, ?, ?)";
        conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setFloat(4, produto.getValor());
            
            int qtdLinhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (qtdLinhasAfetadas > 0)
                return true;
            return false;
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public boolean deleteProduto(int id){
        String sql = "DELETE FROM produto WHERE idproduto = ?";
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
                conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return false;
    }
    
    public ArrayList<Produto> listaProduto() {
        String sql = "SELECT * FROM produto";
        ArrayList<Produto> lista_produtos = new ArrayList<Produto>();
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int idproduto = rs.getInt("idproduto");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                float valor = rs.getFloat("valor");
                
                Produto produto = new Produto(idproduto, nome, descricao, valor);
                
                lista_produtos.add(produto);
            }
            stmt.close();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        
        return lista_produtos;
    }
    
    public boolean atulizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, descricao = ?, valor = ? WHERE idproduto = ?";
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setFloat(3, produto.getValor());
            stmt.setInt(4, produto.getId());
            
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
    
    public Produto pegarProduto(int produto_idproduto) {
        String sql = "SELECT * FROM produto WHERE idproduto = ?;";
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, produto_idproduto);
            
            //stmt.execute();
            
            ResultSet rs =  stmt.executeQuery();
            rs.next();
            
            Produto produto = new Produto(produto_idproduto, rs.getString("nome"), rs.getString("descricao"), rs.getFloat("valor"));
            
            stmt.close();
            return produto;
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
