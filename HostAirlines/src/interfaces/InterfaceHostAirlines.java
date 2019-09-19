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
import model.Perfil;
import model.Reserva;
import model.Trecho;
import org.jgrapht.Graph;

/**
 *
 * @author Teeu Guima
 */
public interface InterfaceHostAirlines extends Remote {
    
    public boolean reservarTrecho(String cpf,String companhia, String origem, String destino,  String ida, String volta) throws RemoteException;
    
    public ArrayList<Trecho> buscarTrecho(String origem, String destino) throws RemoteException; 

    public ArrayList<Reserva> buscarReservas(String companhia, String cpf) throws RemoteException;
    
    public ArrayList<Trecho> getTrechosAzul() throws RemoteException;
    
    public ArrayList<Trecho> getTrechosGol() throws RemoteException;
    
    public ArrayList<Trecho> getTrechosLatam() throws RemoteException;
    
    public boolean cadastrarPerfil(String nome, String sobrenome, String cpf, String senha) throws RemoteException;
    
    public Perfil realizarLogin(String cpf, String senha) throws RemoteException;
}
