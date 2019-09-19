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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Perfil;

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

    private ArrayList<Perfil> perfis;
    private final String ALGORITHM = "DSA";

    public InterfaceImpl(InterfaceAzul azulAirlines, InterfaceGol golAirlines, InterfaceLatam latamAirlines) throws RemoteException {
        super();

        this.golAirlines = golAirlines;
        this.azulAirlines = azulAirlines;
        this.latamAirlines = latamAirlines;

        this.reservas = new ArrayList<>();
        this.cidades = new ArrayList<>();
        this.companhia = new ArrayList<>();
        this.trechos = new Multigraph<>(Rota.class);
        this.perfis = new ArrayList<>();

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

    @Override
    public boolean reservarTrecho(String cpf, String companhia, String origem, String destino, String ida, String volta) throws RemoteException {

        if (companhia.compareTo("Azul Airlines") == 0) {
            return this.azulAirlines.reservarTrecho(origem, destino, cpf, ida, volta);

        }
        if (companhia.compareTo("Gol Airlines") == 0) {
            return this.golAirlines.reservarTrecho(companhia, origem, destino, cpf, ida, volta);
        }
        if (companhia.compareTo("Latam Airlines") == 0) {
            return this.latamAirlines.reservarTrecho(companhia, origem, destino, cpf, ida, volta);
        }
        return false;
    }

    @Override
    public ArrayList<Reserva> buscarReservas(String companhia, String cpf) throws RemoteException {
        if (companhia.compareTo("Azul Airlines") == 0) {
            return this.azulAirlines.buscarReservas(cpf);
        } else if (companhia.compareTo("Gol Airlines") == 0) {
            return this.golAirlines.buscarReservas(cpf);
        } else if (companhia.compareTo("Latam Airlines") == 0) {
            return this.latamAirlines.buscarReservas(cpf);
        }
        return null;
    }

    @Override
    public ArrayList<Trecho> buscarTrecho(String origem, String destino) throws RemoteException {
        ArrayList<Trecho> trechosEncontrados = new ArrayList<>();
        Trecho azul = azulAirlines.buscarTrecho(origem, destino);
        if (azul != null) {
            System.out.println("Tem objeto!");
            trechosEncontrados.add(azul);
            System.out.println(azul.getOrigem() + azul.getDestino());
        }
        Trecho gol = golAirlines.buscarTrecho(origem, destino);
        if (gol != null) {
            trechosEncontrados.add(gol);
            System.out.println("Tem objeto!");
        }
        Trecho latam = latamAirlines.buscarTrecho(origem, destino);
        if (latam != null) {
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

    @Override
    public boolean cadastrarPerfil(String nome, String sobrenome, String cpf, String senha) throws RemoteException {
        try {
            Perfil p = new Perfil(nome, sobrenome, cpf, cadastrarSenha(senha));
            if (!hasPerfil(p.getCpf())) {
                perfis.add(p);
                return true;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

            Logger.getLogger(InterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método para conferir a existência de um perfil, através do cpf!
     *
     * @param cpf
     * @return boolean, true se existir, false se não há perfil com o cpf no
     * sistema.
     */
    public boolean hasPerfil(String cpf) {
        Iterator iterPerfis = perfis.iterator();
        while (iterPerfis.hasNext()) {
            Perfil perfil = (Perfil) iterPerfis.next();
            if (perfil.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método que realiza uma busca na lista de perfis cadastrados, conferindo o
     * cpf recebido. Se há perfil com o cpf pertencente, o perfil é retornado,
     * não há perfil retorno é nulo.
     *
     * @param cpf
     * @return Perfil ou null;
     */
    public Perfil buscarPerfil(String cpf) {
        Iterator iterPerfis = perfis.iterator();
        while (iterPerfis.hasNext()) {
            Perfil perfil = (Perfil) iterPerfis.next();
            if (perfil.getCpf().equals(cpf)) {
                return perfil;
            }
        }
        return null;
    }

    /**
     * Método responsável por gerar uma hash da senha cadastrada pelo cliente,
     * utilizando uma criptografia baseada no algoritmo de criptografia SHA-256.
     *
     * @param senha
     * @return Um array de bytes da senha criptografada.
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public byte[] cadastrarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte senhaCripto[] = md.digest(senha.getBytes("UTF-8"));
        return senhaCripto;
    }

    /**
     * Método responsável por conferir se a senha emitida pelo usuário é
     * idêntica a que o mesmo cadastrou.
     *
     *
     * @param senha
     * @return Um array de bytes com a senha criptografada.
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public byte[] validarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte senhaCripto[] = md.digest(senha.getBytes("UTF-8"));
        return senhaCripto;
    }

    /**
     * Método responsável por realizar login no servidor.
     *
     * @param cpf
     * @param senha
     * @return Objeto do tipo Perfil
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws java.rmi.RemoteException
     */
    @Override
    public Perfil realizarLogin(String cpf, String senha) throws RemoteException {
        System.out.println(cpf);
        if (hasPerfil(cpf)) {
            //System.out.println("Entrou");
            Perfil p = buscarPerfil(cpf);
            try {
                if (Arrays.equals(p.getSenhaCriptografada(), validarSenha(senha))) {
                    return p;
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(InterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * Método responsável por gerar chaves públicas e privadas a partir do cpf
     * do cliente, gerando uma sequência aleatória com o array de bytes desta
     * string.
     *
     * @param cpf
     * @return KeyPair, contendo a chave pública e privada.
     * @throws NoSuchAlgorithmException
     */
    public KeyPair chavesDeSeguranca(String cpf) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        SecureRandom secRand = new SecureRandom(cpf.getBytes());
        kpg.initialize(512, secRand);

        KeyPair keyP = kpg.generateKeyPair();
        return keyP;
    }

    /**
     * Método que gera uma hash utilizando o cpf do cliente, retornando a hash a
     * ser carimbada na inscrição de um documento.
     *
     * @param cpf
     * @return Array de byte, contendo a hash baseada no cpf do cliente.
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public byte[] geradorDeHash(String cpf) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte hashPosse[] = md.digest(cpf.getBytes("UTF-8"));
        return hashPosse;
    }

}
