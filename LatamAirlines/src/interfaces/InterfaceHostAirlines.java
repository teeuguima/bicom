/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import model.Cidade;
import model.Trecho;
import org.jgrapht.Graph;

/**
 *
 * @author Teeu Guima
 */
public interface InterfaceHostAirlines extends Remote {
    
    public void cadastrarRota(String companhia, String origem, String destino, int idOrigem, int idDestino, String nomeTrecho, int tempoVoo, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException;
    
    public void editarTrechos(String companhia, String origem, String destino, ArrayList<String> ida, ArrayList<String> volta,int quantidade, double preco) throws RemoteException;

    public void removerTrechos(String companhia, String origem, String destino) throws RemoteException;
    
    public void reservarTrecho(String cpf,String companhia, String nomeTrecho, String origem, String destino,  String ida, String volta) throws RemoteException;
    
    public void cadastrarCidade(Cidade cidade) throws RemoteException;
    
    public ArrayList<Trecho> buscarTrecho(String origem, String destino, String ida, String volta) throws RemoteException; 
    
    public HashMap<String, ArrayList<Trecho>>  buscarTrecho(String origem, String destino) throws RemoteException; 
    
    public void adicionarCompanhia(String companhia) throws RemoteException;
  
    
}
