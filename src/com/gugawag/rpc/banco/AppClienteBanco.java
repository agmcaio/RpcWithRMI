package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        Scanner entrada = new Scanner(System.in);
        int opcao;

        do {
            menu();
            opcao = entrada.nextInt();
            entrada.nextLine(); // Limpar buffer
            switch (opcao) {
                case 1:
                    System.out.println("Digite o número da conta:");
                    String contaSaldo = entrada.nextLine();
                    System.out.println(banco.saldo(contaSaldo));
                    break;
                case 2:
                    System.out.println(banco.quantidadeContas());
                    break;
                case 3:
                    System.out.println("Digite o número da nova conta:");
                    String novaConta = entrada.nextLine();
                    System.out.println("Digite o saldo inicial:");
                    double saldoInicial = entrada.nextDouble();
                    banco.adicionarConta(novaConta, saldoInicial);
                    System.out.println("Conta adicionada com sucesso!");
                    break;
                case 4:
                    System.out.println("Digite o número da conta para pesquisa:");
                    String contaPesquisa = entrada.nextLine();
                    System.out.println(banco.pesquisarConta(contaPesquisa));
                    break;
                case 5:
                    System.out.println("Digite o número da conta para remoção:");
                    String contaRemover = entrada.nextLine();
                    banco.removerConta(contaRemover);
                    System.out.println("Conta removida com sucesso!");
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("9 - Sair");
        System.out.println("Caio André Guilherme de Miranda");
    }

}
