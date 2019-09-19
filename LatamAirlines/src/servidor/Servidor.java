/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacaoRMI.InterfaceImpl;
import interfaces.InterfaceHostAirlines;
import java.rmi.RemoteException;
import interfaces.InterfaceLatam;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

/**
 *
 * @author Teeu Guima
 */
public class Servidor {
    public void cadastrarCidades(InterfaceLatam server) throws RemoteException {
        server.cadastrarCidade(1, "Rio de Janeiro", "Galeão");
        server.cadastrarCidade(11, "Curitiba", "Bacacheri");
        server.cadastrarCidade(28, "Natal", "Augusto Severo");
        server.cadastrarCidade(40, "Santa Catarina", "Forquilhinha");
        server.cadastrarCidade(63, "São José dos Campos", " Professor Urbano Ernesto Stumpf");
    }

    public void cadastrarTrechos(InterfaceLatam server) throws RemoteException {
        ArrayList<String> datasRJCURIda = new ArrayList<>();
        ArrayList<String> datasRJCURVolta = new ArrayList<>();

        datasRJCURIda.add("20/09/2019");
        datasRJCURIda.add("21/09/2019");
        datasRJCURIda.add("22/09/2019");
        datasRJCURIda.add("23/09/2019");
        datasRJCURIda.add("24/09/2019");
        datasRJCURIda.add("25/09/2019");
        datasRJCURIda.add("26/09/2019");
        datasRJCURIda.add("27/09/2019");
        datasRJCURIda.add("28/09/2019");
        datasRJCURIda.add("29/09/2019");

        datasRJCURVolta.add("01/10/2019");
        datasRJCURVolta.add("02/10/2019");
        datasRJCURVolta.add("03/10/2019");
        datasRJCURVolta.add("04/10/2019");
        datasRJCURVolta.add("05/10/2019");
        datasRJCURVolta.add("06/10/2019");
        datasRJCURVolta.add("07/10/2019");
        datasRJCURVolta.add("08/10/2019");
        datasRJCURVolta.add("09/10/2019");
        datasRJCURVolta.add("10/10/2019");
        
        server.cadastrarTrechos("Rio de Janeiro", "Curitiba", 0, 2, "RiodeJaneiro-Curitiba", 2, datasRJCURIda, datasRJCURVolta, 40, 576.40);
/*
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

        server.cadastrarTrechos("Rio de Janeiro", "Belo Horizonte", 1, 3, "RioDeJaneiro-BeloHorizonte", 2, datasRJBHIda, datasRJBHVolta, 40, 668.00);

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

        server.cadastrarTrechos("São Paulo", "Porto Alegre", 0, 4, "SaoPaulo-PortoAlegre", 90, datasSPPAIda, datasSPPAVolta, 40, 842.70);

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

        server.cadastrarTrechos("Rio de Janeiro", "Salvador", 1, 3, "RioDeJaneiro-Salvador", 90, datasRJSSAIda, datasRJSSAVolta, 40, 958.90);
   */
}
    
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        try {
            Servidor servidor = new Servidor();
            
            
            
            
            InterfaceLatam servidorLatam = new InterfaceImpl();
            Registry registry = LocateRegistry.createRegistry(5575);
            registry.rebind("LatamServices", servidorLatam);

            servidor.cadastrarCidades(servidorLatam);
            servidor.cadastrarTrechos(servidorLatam);
            
            System.out.println("Servidor Inicializado!");
        } catch (RemoteException e) {
            System.out.println(e.toString());
        }

    }

}
