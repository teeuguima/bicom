/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoRMI;

import interfaces.InterfaceAzul;
import interfaces.InterfaceGol;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import model.Reserva;
import model.Trecho;
import java.util.Set;
import model.Cidade;
import model.Rota;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import interfaces.InterfaceHostAirlines;
import interfaces.InterfaceLatam;
import java.util.HashMap;

/**
 *
 * @author Teeu Guima
 */
public class InterfaceImpl extends UnicastRemoteObject implements InterfaceHostAirlines {

    private ArrayList<Reserva> reservas;
    private ArrayList<Cidade> cidades;
    private Graph<Cidade, Rota> trechos;
    public ArrayList<String> companhia;

    private InterfaceGol golAirlines;
    private InterfaceAzul azulAirlines;
    private InterfaceLatam latamAirlines;

    public InterfaceImpl(InterfaceAzul azulAirlines, InterfaceGol golAirlines, InterfaceLatam latamAirlines) throws RemoteException {
        super();

        this.golAirlines = golAirlines;
        this.azulAirlines = azulAirlines;
        this.latamAirlines = latamAirlines;

        this.reservas = new ArrayList<>();
        this.cidades = new ArrayList<>();
        this.companhia = new ArrayList<>();
        this.trechos = new Multigraph<>(Rota.class);

    }

    private boolean hasCidade(String nome) {
        Iterator iterCidades = cidades.iterator();
        if (iterCidades.hasNext()) {
            Cidade cidade = (Cidade) iterCidades.next();
            if (cidade.getNome().compareTo(nome) == 0) {
                return true;
            }
        }
        return false;
    }

    private Cidade buscarCidade(int idLocal) {
        Iterator iterCidades = cidades.iterator();
        if (iterCidades.hasNext()) {
            Cidade cidade = (Cidade) iterCidades.next();
            if (cidade.getCodigoDoLocal() == idLocal) {
                return cidade;
            }
        }
        return null;
    }

    public void cadastrarCidade(Cidade cidade) {
        if (buscarCidade(cidade.getNome()) == null) {
            this.cidades.add(cidade);
            this.trechos.addVertex(cidade);
        }
    }

    public void adicionarCompanhia(String companhia) {
        if (!hasCompanhia(companhia)) {
            this.companhia.add(companhia);
        }
    }

    public boolean hasCompanhia(String companhia) {
        Iterator iterCompanhia = this.companhia.iterator();
        while (iterCompanhia.hasNext()) {
            String comp = (String) iterCompanhia.next();
            if (comp.compareTo(companhia) == 0) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarRota(String companhia, String origem, String destino, int idOrigem, int idDestino, String nomeTrecho, int tempoVoo, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException {
        Cidade cdorigem = buscarCidade(origem);
        Cidade cddestino = buscarCidade(destino);

        if (cdorigem != null && cddestino != null) {
            System.out.println("Entrou");
            System.out.println(origem + " " + destino);
            if (this.trechos.containsVertex(cdorigem) && this.trechos.containsVertex(cddestino)) {
                //this.rotas.addVertex(cdorigem);
                //this.rotas.addVertex(cddestino);
                System.out.println("Entrou Aqui!");
                if (!this.trechos.containsEdge(cdorigem, cddestino)) {
                    Rota rota = new Rota();
                    rota.adicionarRota(companhia, nomeTrecho, new Trecho(companhia, origem, destino, nomeTrecho, ida, volta, preco, quantidade));
                    this.trechos.addEdge(cdorigem, cddestino, rota);
                } else {
                    Rota rota = this.trechos.getEdge(cdorigem, cddestino);
                    rota.adicionarRota(companhia, nomeTrecho, new Trecho(companhia, origem, destino, nomeTrecho, ida, volta, preco, quantidade));
                    this.trechos.addEdge(cdorigem, cddestino, rota);
                }
            }

        } else {

        }

    }

    private Cidade buscarCidade(String nome) {
        Iterator iterCidades = cidades.iterator();
        while (iterCidades.hasNext()) {
            Cidade cidade = (Cidade) iterCidades.next();
            if (cidade.getNome().compareTo(nome) == 0) {
                return cidade;
            }
        }
        return null;
    }

    public void editarTrechos(String companhia, String origem, String destino, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException {
        if (hasCompanhia(companhia)) {
            if (hasCidade(origem) && hasCidade(destino)) {
                Rota rota = this.trechos.getEdge(buscarCidade(origem), buscarCidade(destino));
                rota.alterarRota(companhia, quantidade, ida, volta, preco);
            }
        }
    }

    public void removerTrechos(String companhia, String origem, String destino) throws RemoteException {
        if (hasCompanhia(companhia)) {
            if (hasCidade(origem) && hasCidade(destino)) {
                Cidade corigem = buscarCidade(origem);
                Cidade cdestino = buscarCidade(destino);
                Rota rota = this.trechos.getEdge(corigem, cdestino);
                this.trechos.removeEdge(rota);
                rota.removerTrecho(companhia);
                this.trechos.addEdge(corigem, cdestino, rota);

            }
        }

    }

    @Override
    public boolean reservarTrecho(String cpf, String companhia, String origem, String destino, String ida, String volta) throws RemoteException {
        
        if(companhia.compareTo("Azul Airlines")==0){
            return this.azulAirlines.reservarTrecho(companhia, origem, destino, cpf, ida, volta);
        }
        if(companhia.compareTo("Gol Airlines")==0){
             return this.golAirlines.reservarTrecho(companhia, origem, destino, cpf, ida, volta);
        }
        if(companhia.compareTo("Latam Airlines")==0){
            return this.latamAirlines.reservarTrecho(companhia, origem, destino, cpf, ida, volta);
        }
        return false;
    }

    public boolean hasReserva(String cpf, String ida, String volta) {
        Iterator iterReservas = this.reservas.iterator();
        while (iterReservas.hasNext()) {
            Reserva reserv = (Reserva) iterReservas.next();
            if (reserv.getCpf().equals(cpf) && reserv.getTrecho().getDataIda().compareTo(ida) == 0 && reserv.getTrecho().getDataVolta().compareTo(volta) == 0) {
                return true;
            }

        }
        return false;
    }

    public Graph getCidadesCadastradas() {
        return this.trechos;
    }
/*
    public HashMap<String, ArrayList<Trecho>> buscarTrecho(String origem, String destino) throws RemoteException {
        HashMap<String, ArrayList<Trecho>> trechosCompanhias = new HashMap<>();

        ArrayList<Trecho> azulTrechos = new ArrayList<>();
        ArrayList<Trecho> golTrechos = new ArrayList<>();
        ArrayList<Trecho> latamTrechos = new ArrayList<>();

        // ArrayList<Trecho> trechosEncontrados = new ArrayList<>();
        Cidade cdOrigem = buscarCidade(origem);
        Cidade cdDestino = buscarCidade(destino);

        if (cdOrigem != null && cdDestino != null) {
            System.out.println(cdOrigem.getNome());
            System.out.println(cdDestino.getNome());
            Set<Rota> rotas = this.trechos.getAllEdges(cdOrigem, cdDestino);

            if (rotas != null) {
                System.out.println("Não tem rota!");
            }
            Iterator iterRotas = rotas.iterator();

            while (iterRotas.hasNext()) {
                Rota r = (Rota) iterRotas.next();
                System.out.println(r.getNomeTrecho());
                azulTrechos.add(r.buscarTrecho("Azul Airlines"));
                golTrechos.add(r.buscarTrecho("Gol Airlines"));
                latamTrechos.add(r.buscarTrecho("Latam Airlines"));
            }
        }

        trechosCompanhias.put("Azul Airlines", azulTrechos);
        trechosCompanhias.put("Gol Airlines", golTrechos);
        trechosCompanhias.put("Latam Airlines", latamTrechos);

        //return trechosEncontrados;
        return trechosCompanhias;
    }
*/
    @Override
    public ArrayList<Trecho> buscarTrecho(String origem, String destino) throws RemoteException {
        ArrayList<Trecho> trechosEncontrados = new ArrayList<>();
        Trecho azul = azulAirlines.buscarTrecho(origem, destino);
        if(azul != null){
            System.out.println("Tem objeto!");
            trechosEncontrados.add(azul);
            System.out.println(azul.getOrigem() + azul.getDestino());
        }
        Trecho gol = golAirlines.buscarTrecho(origem, destino);
        if(gol != null){
            trechosEncontrados.add(gol);
            System.out.println("Tem objeto!");
        }
        Trecho latam = latamAirlines.buscarTrecho(origem, destino);
        if(latam != null){
            trechosEncontrados.add(latam);
            System.out.println("Tem objeto!");
        }
        
        
        

        return trechosEncontrados;
    }

    @Override
    public ArrayList<Trecho> getTrechosAzul() throws RemoteException {
        return azulAirlines.getTrechos();
    }

    @Override
    public ArrayList<Trecho> getTrechosGol() throws RemoteException {
        return golAirlines.getTrechos();
    }

    @Override
    public ArrayList<Trecho> getTrechosLatam() throws RemoteException {
        return latamAirlines.getTrechos();
    }

}
