/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacaoRMI.InterfaceImpl;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import model.Trecho;
import interfaces.InterfaceAzul;


/**
 *
 * @author Teeu Guima
 */
public class Servidor {
    
    public void cadastrarCidades(InterfaceAzul server) throws RemoteException{
        server.cadastrarCidade(0, "São Paulo", "Congonhas");
        server.cadastrarCidade(1, "Rio de Janeiro", "Galeão");
        server.cadastrarCidade(2, "Salvador", "Luis Eduardo Magalhães");
        server.cadastrarCidade(3, "Belo Horizonte", "Tancredo Neves");
        server.cadastrarCidade(4, "Porto Alegre", "Salgado Filho");
        server.cadastrarCidade(5, "Recife", "Gilberto Freyre");
    }
        
    public void cadastrarTrechos(InterfaceAzul server) throws RemoteException{
        ArrayList<String> datasSPSSAIda = new ArrayList<>();
        ArrayList<String> datasSPSSAVolta = new ArrayList<>();
        
        datasSPSSAIda.add("20/09/2019");
        datasSPSSAIda.add("21/09/2019");
        datasSPSSAIda.add("22/09/2019");
        datasSPSSAIda.add("23/09/2019");
        datasSPSSAIda.add("24/09/2019");
        datasSPSSAIda.add("25/09/2019");
        datasSPSSAIda.add("26/09/2019");
        datasSPSSAIda.add("27/09/2019");
        datasSPSSAIda.add("28/09/2019");
        datasSPSSAIda.add("29/09/2019");
        
        datasSPSSAVolta.add("01/10/2019");
        datasSPSSAVolta.add("02/10/2019");
        datasSPSSAVolta.add("03/10/2019");
        datasSPSSAVolta.add("04/10/2019");
        datasSPSSAVolta.add("05/10/2019");
        datasSPSSAVolta.add("06/10/2019");
        datasSPSSAVolta.add("07/10/2019");
        datasSPSSAVolta.add("08/10/2019");
        datasSPSSAVolta.add("09/10/2019");
        
        server.cadastrarTrechos("São Paulo", "Salvador", 0, 2, "SaoPaulo-Salvador", 2, datasSPSSAIda, datasSPSSAVolta,40, 576.40);
        
        ArrayList<String> datasSSASPIda = new ArrayList<>();
        ArrayList<String> datasSSASPVolta = new ArrayList<>();
        
        datasSSASPIda.add("20/09/2019");
        datasSSASPIda.add("21/09/2019");
        datasSSASPIda.add("22/09/2019");
        datasSSASPIda.add("23/09/2019");
        datasSSASPIda.add("24/09/2019");
        datasSSASPIda.add("25/09/2019");
        datasSSASPIda.add("26/09/2019");
        datasSSASPIda.add("27/09/2019");
        datasSSASPIda.add("28/09/2019");
        datasSSASPIda.add("29/09/2019");
        
        datasSSASPVolta.add("01/10/2019");
        datasSSASPVolta.add("02/10/2019");
        datasSSASPVolta.add("03/10/2019");
        datasSSASPVolta.add("04/10/2019");
        datasSSASPVolta.add("05/10/2019");
        datasSSASPVolta.add("06/10/2019");
        datasSSASPVolta.add("07/10/2019");
        datasSSASPVolta.add("08/10/2019");
        datasSSASPVolta.add("09/10/2019");
        
        server.cadastrarTrechos("Salvador", "São Paulo", 2, 0, "Salvador-SãoPaulo", 2, datasSSASPIda, datasSSASPVolta,40, 986.40);
        
        ArrayList<String> datasRJBHIda = new ArrayList<>();
        ArrayList<String> datasRJBHVolta = new ArrayList<>();
        
        datasRJBHIda.add("20/09/2019");
        datasRJBHIda.add("21/09/2019");
        datasRJBHIda.add("22/09/2019");
        datasRJBHIda.add("23/09/2019");
        datasRJBHIda.add("24/09/2019");
        datasRJBHIda.add("25/09/2019");
        datasRJBHIda.add("26/09/2019");
        datasRJBHIda.add("27/09/2019");
        datasRJBHIda.add("28/09/2019");
        datasRJBHIda.add("29/09/2019");
        
        datasRJBHVolta.add("01/10/2019");
        datasRJBHVolta.add("02/10/2019");
        datasRJBHVolta.add("03/10/2019");
        datasRJBHVolta.add("04/10/2019");
        datasRJBHVolta.add("05/10/2019");
        datasRJBHVolta.add("06/10/2019");
        datasRJBHVolta.add("07/10/2019");
        datasRJBHVolta.add("08/10/2019");
        datasRJBHVolta.add("09/10/2019");
        
        server.cadastrarTrechos("Rio de Janeiro", "Belo Horizonte", 1, 3, "RioDeJaneiro-BeloHorizonte", 2, datasRJBHIda, datasRJBHVolta,40, 668.00);
    
        ArrayList<String> datasSPPAIda = new ArrayList<>();
        ArrayList<String> datasSPPAVolta = new ArrayList<>();
        
        datasSPPAIda.add("20/09/2019");
        datasSPPAIda.add("21/09/2019");
        datasSPPAIda.add("22/09/2019");
        datasSPPAIda.add("23/09/2019");
        datasSPPAIda.add("24/09/2019");
        datasSPPAIda.add("25/09/2019");
        datasSPPAIda.add("26/09/2019");
        datasSPPAIda.add("27/09/2019");
        datasSPPAIda.add("28/09/2019");
        datasSPPAIda.add("29/09/2019");
        
        datasSPPAVolta.add("01/10/2019");
        datasSPPAVolta.add("02/10/2019");
        datasSPPAVolta.add("03/10/2019");
        datasSPPAVolta.add("04/10/2019");
        datasSPPAVolta.add("05/10/2019");
        datasSPPAVolta.add("06/10/2019");
        datasSPPAVolta.add("07/10/2019");
        datasSPPAVolta.add("08/10/2019");
        datasSPPAVolta.add("09/10/2019");
        
        server.cadastrarTrechos("São Paulo", "Porto Alegre", 0, 4, "SaoPaulo-PortoAlegre", 90, datasSPPAIda, datasSPPAVolta,40, 842.70);
    
        ArrayList<String> datasRJSSAIda = new ArrayList<>();
        ArrayList<String> datasRJSSAVolta = new ArrayList<>();
        
        datasRJSSAIda.add("20/09/2019");
        datasRJSSAIda.add("21/09/2019");
        datasRJSSAIda.add("22/09/2019");
        datasRJSSAIda.add("23/09/2019");
        datasRJSSAIda.add("24/09/2019");
        datasRJSSAIda.add("25/09/2019");
        datasRJSSAIda.add("26/09/2019");
        datasRJSSAIda.add("27/09/2019");
        datasRJSSAIda.add("28/09/2019");
        datasRJSSAIda.add("29/09/2019");
        
        datasRJSSAVolta.add("01/10/2019");
        datasRJSSAVolta.add("02/10/2019");
        datasRJSSAVolta.add("03/10/2019");
        datasRJSSAVolta.add("04/10/2019");
        datasRJSSAVolta.add("05/10/2019");
        datasRJSSAVolta.add("06/10/2019");
        datasRJSSAVolta.add("07/10/2019");
        datasRJSSAVolta.add("08/10/2019");
        datasRJSSAVolta.add("09/10/2019");
        
        server.cadastrarTrechos("Rio de Janeiro", "Salvador", 1, 3, "RioDeJaneiro-Salvador", 90, datasRJSSAIda, datasRJSSAVolta,40, 958.90);
    }   
    
    public void buscarTrecho(InterfaceAzul server) throws RemoteException{
        Trecho trechoA = server.buscarTrecho("Rio de Janeiro", "Salvador");
        if(trechoA != null){
            System.out.println(trechoA.getNome() + trechoA.getId() + trechoA.getPreco() + trechoA.getQuantidade());
        }
        
        Trecho trechoB = server.buscarTrecho("São Paulo", "Porto Alegre");
        if(trechoB != null){
            System.out.println(trechoB.getNome() + trechoB.getId() + trechoB.getPreco() + trechoB.getQuantidade());
        }
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            Servidor servidor = new Servidor();
            
            InterfaceAzul servidorAzul = new InterfaceImpl();
            Registry registry = LocateRegistry.createRegistry(5585);

            registry.rebind("AzulServices", servidorAzul);
            
            InterfaceAzul server = (InterfaceAzul) registry.lookup("AzulServices");
            servidor.cadastrarCidades(server);
            servidor.cadastrarTrechos(server);
            
            servidor.buscarTrecho(server);
            
            
            System.out.println("Servidor Inicializado!");
        } catch (RemoteException e) {
            System.out.println(e.toString());
        }

    }

}
