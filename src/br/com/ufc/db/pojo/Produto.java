package br.com.ufc.db.pojo;

public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private float valor;
    
    public Produto(int idProduto, String nome, String descricao, float valor){
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }
    
    public int getId(){
        return idProduto;
    }
    public void setId(int idProduto){
        this.idProduto = idProduto;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public float getValor(){
        return valor;
    }
    public void setValor(float valor){
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return "Id: "+idProduto+"\nNome:"+nome+"\nDescrição: "+descricao+"\nValor: "+valor;
    }
    
}
