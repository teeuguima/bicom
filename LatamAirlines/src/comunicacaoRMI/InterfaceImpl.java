/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoRMI;

import interfaces.InterfaceHostAirlines;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import model.Reserva;
import model.Trecho;
import interfaces.InterfaceLatam;
import model.Cidade;
import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 *
 * @author Teeu Guima
 */
public class InterfaceImpl extends UnicastRemoteObject implements InterfaceLatam {

    private ArrayList<Trecho> trechosLatam;
    private ArrayList<Reserva> reservas;
    private ArrayList<Cidade> cidades;
    private Graph<Cidade, Trecho> trechos;
    private final String companhia = "Latam Airlines";

    public InterfaceImpl() throws RemoteException {
        super();
        this.trechosLatam = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.cidades = new ArrayList<>();
        this.trechos = new Multigraph<>(Trecho.class);

    }

    private boolean hasCidade(int idLocal) {
        Iterator iterCidades = cidades.iterator();
        if (iterCidades.hasNext()) {
            Cidade cidade = (Cidade) iterCidades.next();
            if (cidade.getCodigoDoLocal() == idLocal) {
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

    @Override
    public void cadastrarCidade(int id, String nome, String aeroporto) throws RemoteException {
        System.out.println(nome);
        Cidade cidade = new Cidade(id, nome, aeroporto);
        System.out.println(cidade.getCodigoDoLocal());
        this.cidades.add(cidade);
        this.trechos.addVertex(cidade);

    }

    @Override
    public void cadastrarTrechos(String origem, String destino, int idOrigem, int idDestino, String nomeTrecho, int tempoVoo, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException {
        Cidade cdorigem = buscarCidade(origem);
        Cidade cddestino = buscarCidade(destino);

        if (cdorigem != null && cddestino != null) {
            System.out.println("Entrou");
            System.out.println(origem + " " + destino);
            if (this.trechos.containsVertex(cdorigem) && this.trechos.containsVertex(cddestino)) {
                System.out.println("Entrou Aqui!");
                this.trechos.addEdge(cdorigem, cddestino, new Trecho(this.companhia, nomeTrecho, origem, destino, ida, volta, preco, quantidade));
                this.trechosLatam.add(new Trecho(this.companhia, nomeTrecho, origem, destino, ida, volta, preco, quantidade));
                //this.host.cadastrarRota(this.companhia, origem, destino, idOrigem, idDestino, nomeTrecho, tempoVoo, ida, volta, quantidade, preco);
            }

        } else if (buscarTrecho(origem, destino) != null) {

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

    @Override
    public void editarTrechos(int id, String origem, String destino, ArrayList<String> ida, ArrayList<String> volta, double preco) throws RemoteException {
        Iterator iterTrechos = trechosLatam.iterator();
        while (iterTrechos.hasNext()) {
            Trecho trechos = (Trecho) iterTrechos.next();
            if (trechos.getId() == id) {
                trechos.setOrigem(origem);
                trechos.setIda(ida);
                trechos.setVolta(volta);
                trechos.setPreco(preco);
            }
        }
    }

    @Override
    public void removerTrechos(int id) throws RemoteException {
        Iterator iterTrechos = trechosLatam.iterator();
        while (iterTrechos.hasNext()) {
            Trecho trechos = (Trecho) iterTrechos.next();
            if (trechos.getId() == id) {
                this.trechosLatam.remove(trechos);
            }
        }
    }
    
    public void alterarTrecho(Trecho t){
        this.trechos.removeEdge(buscarCidade(t.getOrigem()), buscarCidade(t.getDestino()));
        this.trechos.addEdge(buscarCidade(t.getNome()),buscarCidade(t.getDestino()), t);
    }

    @Override
    public boolean reservarTrecho(String companhia, String origem, String destino, String cpf, String ida, String volta) throws RemoteException {
        Trecho trecho = buscarTrecho(origem, destino);
        if (trecho.getIda(ida) != null && trecho.getVolta(volta) != null) {
            if (!hasTrechoReservado(trecho)) {
                trecho.removerDataIda(ida);
                trecho.removerDataVolta(volta);
                alterarTrecho(trecho);
                this.reservas.add(new Reserva(cpf, new Trecho(trecho.getNome(), trecho.getDataIda(), trecho.getDataVolta(), trecho.getPreco())));
                return true;
            }
        }
        return false;
    }
    
    public boolean hasTrechoReservado(Trecho trecho) {
        Iterator iterReservas = this.reservas.iterator();
        while (iterReservas.hasNext()) {
            Reserva reserva = (Reserva) iterReservas.next();
            if (reserva.getTrecho().equals(trecho)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasTrecho(Trecho trecho) {
        Iterator iterTrechos = trechosLatam.iterator();
        while (iterTrechos.hasNext()) {
            Trecho trechos = (Trecho) iterTrechos.next();
            if (trechos.getId() == trecho.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param origem
     * @param destino
     * @param ida
     * @param volta
     * @return
     */
    @Override
    public Trecho buscarTrecho(String origem, String destino) {
        ArrayList<String> trechoEncontrado = new ArrayList<>();

        Trecho trecho = this.trechos.getEdge(buscarCidade(origem), buscarCidade(destino));
        if (trecho != null) {
            return trecho;
        } else {
            return null;
        }
    }

    public boolean hasReserva(String cpf, String nomeTrecho) {
        Iterator iterReservas = this.reservas.iterator();
        while (iterReservas.hasNext()) {
            Reserva reserv = (Reserva) iterReservas.next();
            if (reserv.getCpf().equals(cpf) && reserv.getTrecho().getNome().compareTo(nomeTrecho) == 0) {
                return true;
            }

        }
        return false;
    }

    @Override
    public ArrayList<String> listarTrechos() {
        ArrayList<String> trechos = new ArrayList<>();

        this.trechosLatam.forEach((trecho) -> {
            trechos.add(trecho.toString());
        });
        return trechos;
    }

    @Override
    public ArrayList<Trecho> getTrechos() {
        return trechosLatam;
    }

    public Graph getCidadesCadastradas() {
        return this.trechos;
    }

}