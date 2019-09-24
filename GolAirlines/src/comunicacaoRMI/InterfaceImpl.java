/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacaoRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import model.Reserva;
import model.Trecho;
import model.Cidade;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import interfaces.InterfaceGol;
import org.jgrapht.graph.Multigraph;

/**Classe que implementa a interface que define os métodos necessários para
 * prestação de serviços da Gol Airlines.
 * 
 * @author Mateus Guimarães
 */
public class InterfaceImpl extends UnicastRemoteObject implements InterfaceGol {

    private ArrayList<Trecho> trechosGol;
    private ArrayList<Reserva> reservas;
    private ArrayList<Cidade> cidades;
    private Graph<Cidade, Trecho> trechos;

    private final String companhia = "Gol Airlines";

    public InterfaceImpl() throws RemoteException {
        super();
        this.trechosGol = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.cidades = new ArrayList<>();
        this.trechos = new Multigraph<>(Trecho.class);

    }

    /**
     * Método para conferir se há uma cidade já cadastrada!
     *
     * @param idLocal
     * @return booleano
     */
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

    /**
     * Método para cadastrar uma respectiva cidade e inseri-lá na estrutura de
     * grafo!
     *
     * @param id
     * @param nome
     * @param aeroporto
     * @throws RemoteException
     */
    @Override
    public void cadastrarCidade(int id, String nome, String aeroporto) throws RemoteException {
        System.out.println(nome);
        Cidade cidade = new Cidade(id, nome, aeroporto);
        System.out.println(cidade.getCodigoDoLocal());
        this.cidades.add(cidade);
        this.trechos.addVertex(cidade);

    }

    /**
     * Método que insere um trecho (aresta) no grafo entre duas cidades
     * (vértices) definindo informações sobre ponto de origem e destino.
     *
     * @param origem
     * @param destino
     * @param idOrigem
     * @param idDestino
     * @param nomeTrecho
     * @param tempoVoo
     * @param ida
     * @param volta
     * @param quantidade
     * @param preco
     * @throws RemoteException
     */
    @Override
    public void cadastrarTrechos(String origem, String destino, int idOrigem, int idDestino, String nomeTrecho, int tempoVoo, ArrayList<String> ida, ArrayList<String> volta, int quantidade, double preco) throws RemoteException {
        Cidade cdorigem = buscarCidade(origem);
        Cidade cddestino = buscarCidade(destino);

        if (cdorigem != null && cddestino != null) {

            if (this.trechos.containsVertex(cdorigem) && this.trechos.containsVertex(cddestino)) {
                this.trechos.addEdge(cdorigem, cddestino, new Trecho(this.companhia, nomeTrecho, origem, destino, ida, volta, preco, quantidade));
                this.trechosGol.add(new Trecho(this.companhia, nomeTrecho, origem, destino, ida, volta, preco, quantidade));

            }

        } else if (buscarTrecho(origem, destino) != null) {

        }

    }

    /**
     * Método para buscar cidade pelo nome
     *
     * @param nome
     * @return Object Cidade ou null
     */
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

    /**
     * Método para remover uma passagem pelo data de ida e volta do vôo.
     *
     * @param id
     * @param dataIda
     * @param dataVolta
     */
    public void removerPassagem(int id, String dataIda, String dataVolta) {
        Iterator iterTrechos = trechosGol.iterator();
        while (iterTrechos.hasNext()) {
            Trecho t = (Trecho) iterTrechos.next();
            if (t.getId() == id) {
                this.trechosGol.remove(t);
            }
        }
    }

    /**
     * Método para modificar o trecho de uma rota, removendo as informações
     * antigas e substituindo pelas novas.
     *
     * @param t
     */
    public void alterarTrecho(Trecho t) {
        this.trechos.removeEdge(buscarCidade(t.getOrigem()), buscarCidade(t.getDestino()));
        this.trechos.addEdge(buscarCidade(t.getOrigem()), buscarCidade(t.getDestino()), t);
    }

    /**
     * Método realiza a reserva de um trecho a partir da data de ida e escolha,
     * identificando a reserva pelo cpf do comprador.
     *
     * @param origem
     * @param destino
     * @param cpf
     * @param ida
     * @param volta
     * @return booleano
     * @throws RemoteException
     */
    @Override
    public synchronized boolean reservarTrecho(String origem, String destino, String cpf, String ida, String volta) throws RemoteException {
        Trecho trecho = buscarTrecho(origem, destino);
        if (trecho.hasIda(ida) && trecho.hasVolta(volta)) {

            if (!hasTrechoReservado(cpf, ida, volta)) {

                trecho.removerDataIda(ida);
                trecho.removerDataVolta(volta);
                alterarTrecho(trecho);
                this.reservas.add(new Reserva(cpf, new Trecho(trecho.getNome(), ida, volta, trecho.getPreco())));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Método verifica a existência de um trecho e retorna verdadeiro ou falso;
     *
     * @param trecho
     * @return booleano
     */
    private boolean hasTrecho(Trecho trecho) {
        Iterator iterTrechos = trechosGol.iterator();
        while (iterTrechos.hasNext()) {
            Trecho trechos = (Trecho) iterTrechos.next();
            if (trechos.getId() == trecho.getId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método busca as reservas realizadas por um determinado cliente, usando
     * seu cpf como referência. Retorna então um arraylist contendo todas suas
     * reservas.
     *
     * @param cpf
     * @return ArrayList<Reserva>
     */
    @Override
    public ArrayList<Reserva> buscarReservas(String cpf) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();

        Iterator iterReser = this.reservas.iterator();
        while (iterReser.hasNext()) {
            Reserva r = (Reserva) iterReser.next();
            if (r.getCpf().compareTo(cpf) == 0) {
                reservasEncontradas.add(r);
            }
        }

        return reservasEncontradas;
    }

    /**
     * Método realiza uma busca do trecho, cadastrado no sistema, partindo de
     * uma origem e um destino. Retornando o trecho encontrado.
     *
     * @param origem
     * @param destino
     * @return Object do tipo Trecho.
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

    /**
     * Método confere se um trecho nas determinadas datas ,ida e volta, foram
     * reservados por outra pessoa ou pela mesma. Retornando verdadeiro ou
     * falso.
     *
     * @param cpf
     * @param dataIda
     * @param dataVolta
     * @return booleano
     */
    public boolean hasTrechoReservado(String cpf, String dataIda, String dataVolta) {
        Iterator iterReservas = this.reservas.iterator();
        while (iterReservas.hasNext()) {
            Reserva reserva = (Reserva) iterReservas.next();
            if (reserva.getCpf().compareTo(cpf) == 0) {
                if (reserva.getTrecho().getDataIda().compareTo(dataIda) == 0
                        && reserva.getTrecho().getDataVolta().compareTo(dataVolta) == 0) {
                    return true;

                }
            } else if (!reserva.getCpf().equals(cpf) && reserva.getTrecho().getDataIda().compareTo(dataIda) == 0
                    && reserva.getTrecho().getDataVolta().compareTo(dataVolta) == 0) {
                return false;
            }
        }
        return false;
    }

    /**Método retornará todas os trechos cadastrados no sistema
     * em um ArrayList do tipo Trecho.
     * 
     * @return ArrayList<Trecho>.
     */
    @Override
    public ArrayList<Trecho> getTrechos() {
        return trechosGol;
    }

}
