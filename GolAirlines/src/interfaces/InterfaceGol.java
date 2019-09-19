/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.Reserva;
import model.Trecho;

/**
 *
 * @author Teeu Guima
 */
public interface InterfaceGol extends Remote {
    
    public void cadastrarTrechos(String origem, String destino, int idOrigem, int idDestino, String nomeTrecho, int tempoVoo, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException;
    
    public void editarTrechos(int id, String origem, String destino, ArrayList<String> ida, ArrayList<String> volta, double preco) throws RemoteException;

    public ArrayList<Reserva> buscarReservas(String cpf) throws RemoteException;
    
    public void removerTrechos(int id) throws RemoteException;
    
    public boolean reservarTrecho(String companhia, String origem, String destino, String cpf, String ida, String volta) throws RemoteException;
    
    public void cadastrarCidade(int id, String nome, String aeroporto) throws RemoteException;
    
    public Trecho buscarTrecho(String origem, String destino) throws RemoteException;
    
    public ArrayList<String> listarTrechos() throws RemoteException;
    
    public ArrayList<Trecho> getTrechos() throws RemoteException;
    
}
