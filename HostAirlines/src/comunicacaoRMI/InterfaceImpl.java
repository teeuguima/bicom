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
import interfaces.InterfaceHostAirlines;
import interfaces.InterfaceLatam;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Perfil;

/**Classe responsável por tratar as solicitações dos clientes que 
 * desejam reservar passagens aéreas nas companhias cadastradas!
 *
 * @author Mateus Guimarães
 */
public class InterfaceImpl extends UnicastRemoteObject implements InterfaceHostAirlines {


    private ArrayList<Reserva> reservas;
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
        this.perfis = new ArrayList<>();

    }  

    /**Método para realizar uma reserva dos trechos 
     * escolhidos por um cliente.
     * 
     * @param cpf
     * @param companhia
     * @param origem
     * @param destino
     * @param ida
     * @param volta
     * @return
     * @throws RemoteException 
     */
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
    
/**Método de busca das reservas realizadas por um cliente,
 * retornando seu histórico de compra. 
 * 
 * @param companhia
 * @param cpf
 * @return ArrayList<Reserva>
 * @throws RemoteException 
 */
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
    
    /**
     * 
     * @param origem
     * @param destino
     * @return
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Trecho> buscarTrecho(String origem, String destino) throws RemoteException {
        ArrayList<Trecho> trechosEncontrados = new ArrayList<>();
        Trecho azul = azulAirlines.buscarTrecho(origem, destino);
        if (azul != null) {
            trechosEncontrados.add(azul);
        }
        Trecho gol = golAirlines.buscarTrecho(origem, destino);
        if (gol != null) {
            trechosEncontrados.add(gol);
        }
        Trecho latam = latamAirlines.buscarTrecho(origem, destino);
        if (latam != null) {
            trechosEncontrados.add(latam);
        }

        return trechosEncontrados;
    }

    /**Método que retorna os trechos disponíveis na companhia
     * Azul Airlines
     * 
     * @return ArrayList<Trecho>
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Trecho> getTrechosAzul() throws RemoteException {
        return azulAirlines.getTrechos();
    }

    /**Método que retorna os trechos disponíveis na companhia
     * Gol Airlines
     * 
     * @return ArrayList<Trecho>
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Trecho> getTrechosGol() throws RemoteException {
        return golAirlines.getTrechos();
    }

    /**Método que retorna os trechos disponíveis na companhia
     * Latam Airlines
     * 
     * @return ArrayList<Trecho>
     * @throws RemoteException 
     */
    @Override
    public ArrayList<Trecho> getTrechosLatam() throws RemoteException {
        return latamAirlines.getTrechos();
    }

    /**Método responsável por cadastrar os usuários 
     * no sistema, para que se tenha o controle sobre 
     * histórico de reservas e a solicitação de reservas.
     * 
     * @param nome
     * @param sobrenome
     * @param cpf
     * @param senha
     * @return boolean
     * @throws RemoteException 
     */
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
