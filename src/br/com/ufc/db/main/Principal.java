package br.com.ufc.db.main;

import java.util.ArrayList;
import java.util.Scanner;
import br.com.ufc.db.dao.*;
import br.com.ufc.db.pojo.*;
import java.util.Locale;

public class Principal {
    
    public static void main(String[] args) {
       ClienteDAO clienteDAO = new ClienteDAO();
       ProdutoDAO produtoDAO = new ProdutoDAO();
       VendaDAO vendaDAO = new VendaDAO();
       
       int opcao;
       Scanner scanner = new Scanner(System.in);
       scanner.useLocale(Locale.ENGLISH);
       boolean fim = false;
       
       while(!fim) {
           System.out.println("| 1 | Cadastrar Cliente");
           System.out.println("| 2 | Listar Cliente");
           System.out.println("| 3 | Deletar Cliente");
           System.out.println("| 4 | Atualizar Cliente");
           System.out.println("| 5 | Cadastrar Produto");
           System.out.println("| 6 | Listar Produtos");
           System.out.println("| 7 | Efetuar Venda");
           System.out.println("| 8 | Listar Vendas");
           System.out.println("| 0 | Sair");
           System.out.print("Digite a sua opção ~> ");
           
           opcao = scanner.nextInt();
           scanner.nextLine();
           
           switch(opcao) {
                case 1: {
                    int idCliente;
                    String nome, email;

                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Digite o nome do Cliente: ");
                    nome = scanner.nextLine();
                    System.out.println("Digite o email do Cliente: ");
                    email = scanner.nextLine();
                    System.out.println("Digite o número para o Cliente: ");
                    idCliente = scanner.nextInt();

                    Cliente cliente = new Cliente(idCliente, nome, email);
                    if (clienteDAO.addCliente(cliente)) {
                        System.out.println("Inserido com sucesso!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.err.println("Erro ao inserir!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }

                    break;
                }
                case 2: {
                    ArrayList<Cliente> listar_cliente = clienteDAO.listaCliente();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    for (Cliente cliente : listar_cliente) {
                        System.out.println(cliente.toString());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 3: {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Digite o número do Cliente que deseja deletar: ");
                    int idcliente = scanner.nextInt();
                    if(clienteDAO.deleteCli(idcliente)) {
                        System.out.println("Deletado com sucesso!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.err.println("Erro ao deletar!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 4: {
                    int idCliente;
                    String nome, email;
                    
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Digite o nome do cliente: ");
                    nome = scanner.nextLine();
                    System.out.println("Digite o email do cliente: ");
                    email = scanner.nextLine();
                    System.out.println("Digite o número do cliente que deseja atualizar: ");
                    idCliente = scanner.nextInt();
                    
                    Cliente cliente = new Cliente(idCliente, nome, email);
                    
                    if(clienteDAO.atualizarCliente(cliente)){
                        System.out.println("Atualizado com sucesso!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.err.println("Erro ao atulizar!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 5: {
                    int idproduto;
                    float valor;
                    String nome, descricao;
                    
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Digite o nome o produto: ");
                    nome = scanner.nextLine();
                    System.out.println("Dite a descrição do produto: ");
                    descricao = scanner.nextLine();
                    System.out.println("Digite o valor do produto: ");
                    valor = scanner.nextFloat();
                    System.out.println("Digite o número do produto: ");
                    idproduto = scanner.nextInt();
                    
                    Produto produto = new Produto(idproduto, nome, descricao, valor);
                    
                    if(produtoDAO.addProduto(produto)) {
                        System.out.println("Inserido com sucesso!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.err.println("Erro ao incerir!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 6: {
                    ArrayList<Produto> listar_produto = produtoDAO.listaProduto();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    for(Produto produto : listar_produto) {
                        System.out.println(produto.toString());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 7: {
                    int cliente_idcliente, produto_idproduto;
                    
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Didite o número do cliente");
                    cliente_idcliente = scanner.nextInt();
                    System.out.println("Digite o número do produto");
                    produto_idproduto = scanner.nextInt();
                    
                    Cliente cliente = clienteDAO.pegarCliente(cliente_idcliente);
                    Produto produto = produtoDAO.pegarProduto(produto_idproduto);
                    
                    if(cliente == null || produto == null){
                        System.err.println("Erro, cliente ou produto não encontrado!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    } 
                    
                    if(vendaDAO.realizarVenda(cliente, produto)){
                        System.out.println("Venda realizada!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    } else {
                        System.err.println("Erro ao realizar venda!");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    
                    break;
                }
                case 8: {
                    ArrayList<Venda> lista_venda = vendaDAO.listarVenda();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    for(Venda venda : lista_venda) {
                        System.out.println(venda.getCliente().getNome()+" comprou o produto: "+venda.getProduto().getNome());
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    break;
                }
                default: {
                    fim = true;
                    break;
                }
           }
       }
       
    }
}
