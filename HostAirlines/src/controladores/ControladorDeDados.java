/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import model.Cidade;
import model.Perfil;
import util.Grafos;
import util.Vertex;

/**
 * Classe responsável pela parte principal do servidor que é armazenar,
 * controlar e realizar operações fundamentais para a aplicação. Cadastrando
 * documentos, perfis, oferecendo serviço de login, busca.
 *
 * @author Mateus Guimarães
 */
public class ControladorDeDados {

    private ArrayList<Perfil> perfis;

    private final String ALGORITHM = "DSA";
    File filePerfis;

    public Grafos grafos;
    public ArrayList listaVertices;

    

    public ControladorDeDados() {

        this.perfis = new ArrayList<>();
        this.filePerfis = null;
        this.grafos = new Grafos();
        this.listaVertices = new ArrayList();
    }

    /**
     * Método que cria um perfil destinado aos clientes que desejam consultar e
     * cadastrar documentos.
     *
     * @param perfil
     * @return boolean de controle do cadastro.
     */
    public boolean cadastrarPerfil(Perfil perfil) {
        if (!hasPerfil(perfil.getCpf())) {
            perfis.add(perfil);
            return true;
        } else {
            return false;
        }
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
     */
    public Perfil realizarLogin(String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (hasPerfil(cpf)) {
            Perfil p = buscarPerfil(cpf);
            if (Arrays.equals(p.getSenhaCriptografada(), validarSenha(senha))) {
                return p;
            } else {
                return null;
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

    /**
     * Método responsável por atualizar o arquivo de persistência de dados! O
     * mesmo atualiza o arquivo com os novos dados inseridos nas estruturas.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void salvandoDados() throws FileNotFoundException, IOException {
        if (this.perfis != null || this.perfis.isEmpty()) {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePerfis));
            output.writeObject(this.perfis);
        }

    }

    /**
     * Método responsável por ler os dados armazenados nos arquivos e
     * sobrescrever!
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void lendoDados() throws FileNotFoundException, IOException, ClassNotFoundException {

        if (filePerfis.length() > 0) {
            ObjectInputStream inputPerfis = new ObjectInputStream(new FileInputStream(filePerfis));
            if (this.perfis.isEmpty()) {
                this.perfis = (ArrayList<Perfil>) inputPerfis.readObject();
                inputPerfis.close();
            }
        }

    }

    /**
     * Método responsável por criar os arquivos de textos essenciais para a
     * persistência de dados do servidor (Cartório).
     *
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public void criandoArquivos() throws IOException, FileNotFoundException, ClassNotFoundException {
        filePerfis = new File("fileperfis.txt");
        if (!filePerfis.exists()) {
            filePerfis = new File("fileperfis.txt");
        }

    }

    public Vertex cadastrarCidades(String nome,String codigoDoLocal, String nomeAeroporto) {
        int codeLoc = Integer.parseInt(codigoDoLocal);
        Cidade cidade = null;

        Vertex vertice = new Vertex(codigoDoLocal, cidade);
        if (this.listaVertices.add(vertice)) {
            if (this.grafos.addVertex(vertice)) {
                return vertice;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Iterator percorreVertices() {
        return this.listaVertices.iterator();
    }

    public Cidade buscaCidade(int codigoLocal) {
        Iterator iter_cidade = percorreVertices();

        while (iter_cidade.hasNext()) {
            Vertex vertice = (Vertex) iter_cidade.next();
            Cidade cidade = vertice.getCidade();
            if (cidade.getCodigoDoLocal() == codigoLocal) {
                return cidade;
            }
        }
        return null;
    }

    public Vertex buscaVertice(String codigoLocal) {
        Iterator iter_Vertices = percorreVertices();
        while (iter_Vertices.hasNext()) {
            Vertex vertices = (Vertex) iter_Vertices.next();
            if (vertices.getLabel().compareTo(codigoLocal) == 0) {
                return vertices;
            }
        }
        return null;
    }
    
    public boolean cadastrarEstradas(String codigoLocalOrigem, String codigoLocalFinal, String distancia) {
        Vertex pontoInicial = buscaVertice(codigoLocalOrigem);
        Vertex pontoFinal = buscaVertice(codigoLocalFinal);
        int peso = Integer.parseInt(distancia);
        if(pontoInicial != null && pontoFinal != null){
             this.grafos.addEdge(pontoInicial, pontoFinal, peso);
             return true;
        }
            return false;
    }

}
