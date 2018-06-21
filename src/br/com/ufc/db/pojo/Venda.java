package br.com.ufc.db.pojo;


public class Venda {
    private Produto produto;
    private Cliente cliente;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda(Produto produto, Cliente cliente) {
        super();
        this.produto = produto;
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "Id do cliente: "+cliente+"\n"+
                "Id do produto: "+produto;
    }
    
}
