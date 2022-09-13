package cli;

import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciarProdutos {

    public List<Produto> lista = new ArrayList<>();


    public static void main(String[] args) {
        GerenciarProdutos gp = new GerenciarProdutos();
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do{
            opcao = constuirMenu(gp, sc);
        }while(opcao!=5);
    }

    private static int constuirMenu(GerenciarProdutos gp, Scanner sc) {

        System.out.println("\n\n----MENU INICIAL----");
        System.out.println("1. Cadastrar Novo Produto");
        System.out.println("2. Entrada de Produtos");
        System.out.println("3. Saída de Produtos");
        System.out.println("4. Ver Estoque Atual");
        System.out.println("5. Sair");
        System.out.println("Digite sua opcao: ");
        int opcao = sc.nextInt();

        switch (opcao){
            case 1:
                gp.execCadastrar(sc);
            break;

            case 2:
                gp.execEntradaProduto(sc);
            break;

            case 3:
                gp.execSaidaProduto(sc);
            break;

            case 4:
                gp.execEstoqueAtual();
            break;

            case 5:
                System.out.println("Finalizando...");
                break;

            default:
                System.out.println("Opcao Invalida.");
        }
        return opcao;
    }
        // CADASTRAR NOVO PRODUTO E ADICIONAR AO ESTOQUE DIRETO
        public void execCadastrar(Scanner sc){

            Produto produto = new Produto();
            System.out.println("\n------ Cadastro de Produtos ------\n");
            System.out.println("Digite o CODIGO do produto: ");
            produto.setCodigoProduto(sc.nextInt());
            sc.nextLine();
            System.out.println("Digite o NOME do produto: ");
            produto.setNomeProduto(sc.nextLine());
            System.out.println("Digite o VALOR do produto: ");
            produto.setValorProduto(sc.nextDouble());
            System.out.println("Digite a QUANTIDADE do produto que esta entrando: ");
            produto.setQtdProduto(sc.nextInt());

            //adicionar na lista
            lista.add(produto);
            System.out.println("Novo Produto Cadastrado e adicionado com sucesso");
            System.out.println(produto);
        }

        // ADICIONAR PRODUTO JA CADASTRADO NO ESTOQUE
        public void execEntradaProduto(Scanner sc){
            System.out.println("\n------- Entrada de Produtos no Estoque ------\n");

            System.out.print("Entre o código do produto: ");
            int codigoProduto = sc.nextInt();
            boolean procurarProduto = false;

            for (Produto produto : lista) {
                if(codigoProduto == produto.getCodigoProduto()) {
                    System.out.println("O estoque atual desse produto é: " + produto.getQtdProduto());
                    System.out.print("Quantidade a ser adicionada: ");
                    produto.setQtdProduto(produto.getQtdProduto() + sc.nextInt());
                    System.out.println("Nova quantidade: " + produto.getQtdProduto());
                    procurarProduto = true;
                }
            }
            if(procurarProduto == false) {
                System.out.println("\n Produto não cadastrado na base.");
            }
        }

       public void execSaidaProduto(Scanner sc) {
           System.out.println("\n------- Saida de Produtos do Estoque ------\n");
           System.out.print("Entre o código do produto: ");
           int codigoProduto = sc.nextInt();
           boolean procurarProduto = false;

           for (Produto produto : lista) {
               if (codigoProduto == produto.getCodigoProduto()) {
                   System.out.println("O estoque atual desse produto é: " + produto.getQtdProduto());
                   System.out.print("Quantidade a ser retirada: ");
                   produto.setQtdProduto(produto.getQtdProduto() - sc.nextInt());
                   System.out.println("Nova quantidade: " + produto.getQtdProduto());
                   procurarProduto = true;
               }
           }
           if (procurarProduto == false) {
               System.out.println("\n Produto não cadastrado na base.");
           }
       }


            public void execEstoqueAtual() {
                System.out.println("\n\t-- Estoque de Produtos (Inventário) --\n");

                double valorTotalEstoque = 0;

                for (Produto produto : lista) {
                    System.out.println(produto);
                    valorTotalEstoque += (produto.getValorProduto() * produto.getQtdProduto());
                }
                System.out.println("Valor total em estoque: R$" + String.format("%.2f", valorTotalEstoque));
            }
}