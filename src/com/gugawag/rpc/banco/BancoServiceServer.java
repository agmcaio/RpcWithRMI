package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new ArrayList<>();
        contas.add(new Conta("1", 100.00));
        contas.add(new Conta("2", 156.00));
        contas.add(new Conta("3", 950.00));
    }

    @Override
    public double saldo(String contaNumero) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(contaNumero)) {
                return conta.getSaldo();
            }
        }
        return 0.0;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void adicionarConta(String numeroConta, double saldoInicial) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                throw new RemoteException("Conta já existente!");
            }
        }
        contas.add(new Conta(numeroConta, saldoInicial));
    }

    @Override
    public String pesquisarConta(String numeroConta) throws RemoteException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                return conta.toString();
            }
        }
        return "Conta não encontrada.";
    }

    @Override
    public void removerConta(String numeroConta) throws RemoteException {
        Conta contaRemover = null;
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                contaRemover = conta;
                break;
            }
        }
        if (contaRemover != null) {
            contas.remove(contaRemover);
        } else {
            throw new RemoteException("Conta não encontrada para remoção.");
        }
    }
}
