/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import comunicacaoRMI.InterfaceImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfaces.InterfaceGol;
import java.rmi.NotBoundException;
import java.util.ArrayList;

/**
 *
 * @author Teeu Guima
 */
public class Servidor {

    public void cadastrarCidades(InterfaceGol server) throws RemoteException {
        server.cadastrarCidade(0, "São Paulo", "Congonhas");
        server.cadastrarCidade(1, "Rio de Janeiro", "Galeão");
        server.cadastrarCidade(6, "Chapecó", "Congonhas");
        server.cadastrarCidade(7, "Fortaleza", "Galeão");
        server.cadastrarCidade(8, "Paraíba", "Luis Eduardo Magalhães");
        server.cadastrarCidade(10, "Goiania", "Tancredo Neves");
        server.cadastrarCidade(11, "Curitiba", "Bacacheri");
        server.cadastrarCidade(12, "Brasília", "Gilberto Freyre");
    }

    public void cadastrarTrechos(InterfaceGol server) throws RemoteException {
        ArrayList<String> datasCHFORIda = new ArrayList<>();
        ArrayList<String> datasCHFORVolta = new ArrayList<>();

        datasCHFORIda.add("20/09/2019");
        datasCHFORIda.add("21/09/2019");
        datasCHFORIda.add("22/09/2019");
        datasCHFORIda.add("23/09/2019");
        datasCHFORIda.add("24/09/2019");
        datasCHFORIda.add("25/09/2019");
        datasCHFORIda.add("26/09/2019");
        datasCHFORIda.add("27/09/2019");
        datasCHFORIda.add("28/09/2019");
        datasCHFORIda.add("29/09/2019");

        datasCHFORVolta.add("01/10/2019");
        datasCHFORVolta.add("02/10/2019");
        datasCHFORVolta.add("03/10/2019");
        datasCHFORVolta.add("04/10/2019");
        datasCHFORVolta.add("05/10/2019");
        datasCHFORVolta.add("06/10/2019");
        datasCHFORVolta.add("07/10/2019");
        datasCHFORVolta.add("08/10/2019");
        datasCHFORVolta.add("09/10/2019");
        datasCHFORVolta.add("10/10/2019");

        server.cadastrarTrechos("Chapecó", "Fortaleza", 6, 7, "Chapecó-Fortaleza", 2, datasCHFORIda, datasCHFORVolta, 40, 576.40);

        ArrayList<String> datasPBGOIda = new ArrayList<>();
        ArrayList<String> datasPBGOVolta = new ArrayList<>();

        datasPBGOIda.add("20/09/2019");
        datasPBGOIda.add("21/09/2019");
        datasPBGOIda.add("22/09/2019");
        datasPBGOIda.add("23/09/2019");
        datasPBGOIda.add("24/09/2019");
        datasPBGOIda.add("25/09/2019");
        datasPBGOIda.add("26/09/2019");
        datasPBGOIda.add("27/09/2019");
        datasPBGOIda.add("28/09/2019");
        datasPBGOIda.add("29/09/2019");

        datasPBGOVolta.add("01/10/2019");
        datasPBGOVolta.add("02/10/2019");
        datasPBGOVolta.add("03/10/2019");
        datasPBGOVolta.add("04/10/2019");
        datasPBGOVolta.add("05/10/2019");
        datasPBGOVolta.add("06/10/2019");
        datasPBGOVolta.add("07/10/2019");
        datasPBGOVolta.add("08/10/2019");
        datasPBGOVolta.add("09/10/2019");
        datasPBGOVolta.add("10/10/2019");
        server.cadastrarTrechos("Paraíba", "Goiania", 8, 10, "Paraíba-Goiania", 2, datasPBGOIda, datasPBGOVolta, 40, 668.00);

        ArrayList<String> datasCUBRAIda = new ArrayList<>();
        ArrayList<String> datasCUBRAVolta = new ArrayList<>();

        datasCUBRAIda.add("20/09/2019");
        datasCUBRAIda.add("21/09/2019");
        datasCUBRAIda.add("22/09/2019");
        datasCUBRAIda.add("23/09/2019");
        datasCUBRAIda.add("24/09/2019");
        datasCUBRAIda.add("25/09/2019");
        datasCUBRAIda.add("26/09/2019");
        datasCUBRAIda.add("27/09/2019");
        datasCUBRAIda.add("28/09/2019");
        datasCUBRAIda.add("29/09/2019");

        datasCUBRAVolta.add("01/10/2019");
        datasCUBRAVolta.add("02/10/2019");
        datasCUBRAVolta.add("03/10/2019");
        datasCUBRAVolta.add("04/10/2019");
        datasCUBRAVolta.add("05/10/2019");
        datasCUBRAVolta.add("06/10/2019");
        datasCUBRAVolta.add("07/10/2019");
        datasCUBRAVolta.add("08/10/2019");
        datasCUBRAVolta.add("09/10/2019");
        datasCUBRAVolta.add("10/10/2019");
        
        server.cadastrarTrechos("Curitiba", "Brasília", 11, 12, "Curitiba-Brasília", 90, datasCUBRAIda, datasCUBRAVolta, 40, 842.70);

        ArrayList<String> datasFORBRAIda = new ArrayList<>();
        ArrayList<String> datasFORBRAVolta = new ArrayList<>();

        datasFORBRAIda.add("20/09/2019");
        datasFORBRAIda.add("21/09/2019");
        datasFORBRAIda.add("22/09/2019");
        datasFORBRAIda.add("23/09/2019");
        datasFORBRAIda.add("24/09/2019");
        datasFORBRAIda.add("25/09/2019");
        datasFORBRAIda.add("26/09/2019");
        datasFORBRAIda.add("27/09/2019");
        datasFORBRAIda.add("28/09/2019");
        datasFORBRAIda.add("29/09/2019");

        datasFORBRAVolta.add("01/10/2019");
        datasFORBRAVolta.add("02/10/2019");
        datasFORBRAVolta.add("03/10/2019");
        datasFORBRAVolta.add("04/10/2019");
        datasFORBRAVolta.add("05/10/2019");
        datasFORBRAVolta.add("06/10/2019");
        datasFORBRAVolta.add("07/10/2019");
        datasFORBRAVolta.add("08/10/2019");
        datasFORBRAVolta.add("09/10/2019");
        datasFORBRAVolta.add("10/10/2019");
        server.cadastrarTrechos("Fortaleza", "Brasília", 7, 12, "Fortaleza-Brasília", 90, datasFORBRAIda, datasFORBRAVolta, 40, 958.90);
    
        ArrayList<String> datasPBCURIda = new ArrayList<>();
        ArrayList<String> datasPBCURVolta = new ArrayList<>();

        datasPBCURIda.add("20/09/2019");
        datasPBCURIda.add("21/09/2019");
        datasPBCURIda.add("22/09/2019");
        datasPBCURIda.add("23/09/2019");
        datasPBCURIda.add("24/09/2019");
        datasPBCURIda.add("25/09/2019");
        datasPBCURIda.add("26/09/2019");
        datasPBCURIda.add("27/09/2019");
        datasPBCURIda.add("28/09/2019");
        datasPBCURIda.add("29/09/2019");

        datasPBCURVolta.add("01/10/2019");
        datasPBCURVolta.add("02/10/2019");
        datasPBCURVolta.add("03/10/2019");
        datasPBCURVolta.add("04/10/2019");
        datasPBCURVolta.add("05/10/2019");
        datasPBCURVolta.add("06/10/2019");
        datasPBCURVolta.add("07/10/2019");
        datasPBCURVolta.add("08/10/2019");
        datasPBCURVolta.add("09/10/2019");
        datasPBCURVolta.add("10/10/2019");
        server.cadastrarTrechos("Paraíba", "Curitiba", 8, 11, "Paraíba-Curitiba", 2, datasPBCURIda, datasPBCURVolta, 40, 576.40);

        ArrayList<String> datasCHGOIda = new ArrayList<>();
        ArrayList<String> datasCHGOVolta = new ArrayList<>();

        datasCHGOIda.add("20/09/2019");
        datasCHGOIda.add("21/09/2019");
        datasCHGOIda.add("22/09/2019");
        datasCHGOIda.add("23/09/2019");
        datasCHGOIda.add("24/09/2019");
        datasCHGOIda.add("25/09/2019");
        datasCHGOIda.add("26/09/2019");
        datasCHGOIda.add("27/09/2019");
        datasCHGOIda.add("28/09/2019");
        datasCHGOIda.add("29/09/2019");

        datasCHGOVolta.add("01/10/2019");
        datasCHGOVolta.add("02/10/2019");
        datasCHGOVolta.add("03/10/2019");
        datasCHGOVolta.add("04/10/2019");
        datasCHGOVolta.add("05/10/2019");
        datasCHGOVolta.add("06/10/2019");
        datasCHGOVolta.add("07/10/2019");
        datasCHGOVolta.add("08/10/2019");
        datasCHGOVolta.add("09/10/2019");
        datasCHGOVolta.add("10/10/2019");
        server.cadastrarTrechos("Chapecó", "Goiania", 6, 10, "Chapecó-Goiania", 2, datasCHGOIda, datasCHGOVolta, 40, 668.00);

        ArrayList<String> datasCURFORIda = new ArrayList<>();
        ArrayList<String> datasCURFORVolta = new ArrayList<>();

        datasCURFORIda.add("20/09/2019");
        datasCURFORIda.add("21/09/2019");
        datasCURFORIda.add("22/09/2019");
        datasCURFORIda.add("23/09/2019");
        datasCURFORIda.add("24/09/2019");
        datasCURFORIda.add("25/09/2019");
        datasCURFORIda.add("26/09/2019");
        datasCURFORIda.add("27/09/2019");
        datasCURFORIda.add("28/09/2019");
        datasCURFORIda.add("29/09/2019");

        datasCURFORVolta.add("01/10/2019");
        datasCURFORVolta.add("02/10/2019");
        datasCURFORVolta.add("03/10/2019");
        datasCURFORVolta.add("04/10/2019");
        datasCURFORVolta.add("05/10/2019");
        datasCURFORVolta.add("06/10/2019");
        datasCURFORVolta.add("07/10/2019");
        datasCURFORVolta.add("08/10/2019");
        datasCURFORVolta.add("09/10/2019");
        datasCURFORVolta.add("10/10/2019");
        
        server.cadastrarTrechos("Curitiba", "Fortaleza", 7, 11, "Curitiba-Fortaleza", 90, datasCURFORIda, datasCURFORVolta, 40, 842.70);
        ArrayList<String> datasSPRJIda = new ArrayList<>();
        ArrayList<String> datasSPRJVolta = new ArrayList<>();

        datasSPRJIda.add("20/09/2019");
        datasSPRJIda.add("21/09/2019");
        datasSPRJIda.add("22/09/2019");
        datasSPRJIda.add("23/09/2019");
        datasSPRJIda.add("24/09/2019");
        datasSPRJIda.add("25/09/2019");
        datasSPRJIda.add("26/09/2019");
        datasSPRJIda.add("27/09/2019");
        datasSPRJIda.add("28/09/2019");
        datasSPRJIda.add("29/09/2019");

        datasSPRJVolta.add("01/10/2019");
        datasSPRJVolta.add("02/10/2019");
        datasSPRJVolta.add("03/10/2019");
        datasSPRJVolta.add("04/10/2019");
        datasSPRJVolta.add("05/10/2019");
        datasSPRJVolta.add("06/10/2019");
        datasSPRJVolta.add("07/10/2019");
        datasSPRJVolta.add("08/10/2019");
        datasSPRJVolta.add("09/10/2019");
        datasSPRJVolta.add("10/10/2019");
        server.cadastrarTrechos("São Paulo", "Rio de Janeiro", 0, 1, "SãoPaulo-RioDeJaneiro", 110, datasSPRJIda, datasSPRJVolta, 80, 755.00);
        /*
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

        server.cadastrarTrechos("São Paulo", "Salvador", 0, 2, "SaoPaulo-Salvador", 2, datasSPSSAIda, datasSPSSAVolta, 40, 576.40);

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
            Servidor server = new Servidor();          
            
            InterfaceGol servidorGol = new InterfaceImpl();
            Registry registry = LocateRegistry.createRegistry(5555);
            registry.rebind("GolServices", servidorGol);
            
            server.cadastrarCidades(servidorGol);
            server.cadastrarTrechos(servidorGol);
            System.out.println("Servidor Inicializado!");
        } catch (RemoteException e) {
            System.out.println(e.toString());
        }

    }

}
