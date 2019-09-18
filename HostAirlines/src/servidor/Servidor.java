/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacaoRMI.InterfaceImpl;
import interfaces.InterfaceAzul;
import interfaces.InterfaceGol;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import model.Trecho;
import interfaces.InterfaceHostAirlines;
import interfaces.InterfaceLatam;
import java.util.Iterator;

/**
 *
 * @author Teeu Guima
 */
public class Servidor {
    
    public void buscarTrecho(InterfaceAzul azAir, InterfaceHostAirlines host) throws RemoteException{
        
        Trecho t = azAir.buscarTrecho("São Paulo", "Salvador");
        System.out.println(t.getCompanhia() + t.getPreco() + " " + t.getQuantidade());
        
        ArrayList<Trecho> array = host.buscarTrecho("São Paulo", "Salvador");
        Iterator iterTre = array.iterator();
        while(iterTre.hasNext()){
            Trecho tr = (Trecho) iterTre.next();
            if(tr != null){
                System.out.println("Tem trecho!");
                System.out.println(tr.getCompanhia());
                System.out.println(tr.getOrigem());
                System.out.println(tr.getDestino());
            }
        }
        
        
    }
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            Servidor server = new Servidor();
            
            Registry registryAzul = LocateRegistry.getRegistry(5585);
            InterfaceAzul azulHost = (InterfaceAzul) registryAzul.lookup("AzulServices");
            
            Registry registryGol = LocateRegistry.getRegistry(5555);
            InterfaceGol golHost = (InterfaceGol) registryGol.lookup("GolServices");
            
            Registry registryLatam = LocateRegistry.getRegistry(5575);
            InterfaceLatam latamHost = (InterfaceLatam) registryLatam.lookup("LatamServices");
            
            InterfaceHostAirlines servidor = new InterfaceImpl(azulHost, golHost, latamHost);
            Registry registry = LocateRegistry.createRegistry(5595);

            registry.rebind("OperacoesHost", servidor);
            
            server.buscarTrecho(azulHost, servidor);
            System.out.println("Servidor Inicializado!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
