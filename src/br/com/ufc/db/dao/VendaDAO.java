package br.com.ufc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.ufc.db.jdbc.ConnectionFactory;
import br.com.ufc.db.pojo.*;

public class VendaDAO {
    private Connection conexao;
    
    public VendaDAO() {
        
    }
    
    public boolean realizarVenda(Cliente cliente, Produto produto) {
        String sql = "INSERT INTO venda(cliente_idcliente, produto_idproduto) VALUES(?, ?)";
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setInt(1, cliente.getId());
            stmt.setInt(2, produto.getId());
                
            int qtdLinhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if(qtdLinhasAfetadas > 0)
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
    
    public ArrayList<Venda> listarVenda() {
        String sql = "SELECT * FROM venda";
        ArrayList<Venda> lista_venda = new ArrayList<Venda>();
        
        this.conexao = new ConnectionFactory().getConnection();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                
                int produto_idproduto = Integer.parseInt(rs.getString("produto_idproduto"));
                ProdutoDAO produtoDAO = new ProdutoDAO();
                Produto produto = produtoDAO.pegarProduto(produto_idproduto);
                
                int cliente_idcliente = Integer.parseInt(rs.getString("cliente_idcliente"));
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente cliente = clienteDAO.pegarCliente(cliente_idcliente);
                
                Venda venda = new Venda(produto, cliente);
                
                lista_venda.add(venda);
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
        
        return lista_venda;
    }
    
    
}
