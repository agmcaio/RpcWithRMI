package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void adicionarConta(String numeroConta, double saldoInicial) throws RemoteException;
    String pesquisarConta(String numeroConta) throws RemoteException;
    void removerConta(String numeroConta) throws RemoteException;
}
