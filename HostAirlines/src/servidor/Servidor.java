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

/**Classe principal responsável por inicializar o
 * servidor RMI e se conectar com os outros servidores
 * das companhias aéreas.
 *
 * @author Mateus Guimarães 
 */
public class Servidor {
    
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
            
            
            System.out.println("Servidor Inicializado!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
