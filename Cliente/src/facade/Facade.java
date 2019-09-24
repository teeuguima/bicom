/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import interfaces.InterfaceHostAirlines;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import model.Perfil;

/** Classe que simplifica as chamadas dos métodos
 * na classe principal, conectando diretamente ao servidor
 * e solicitando operações.
 *
 * @author Mateus Guimarães
 */
public class Facade {

    private Perfil perfil;
    final Registry registryHost;
    InterfaceHostAirlines serverHost;

    public Facade() throws IOException, FileNotFoundException, ClassNotFoundException, RemoteException, NotBoundException {
        this.registryHost = LocateRegistry.getRegistry("172.16.103.5",5595);
        this.serverHost = (InterfaceHostAirlines) registryHost.lookup("OperacoesHost");

    }
    
    /**Método que realiza o cadastro das informações
     * de um cliente no servidor. retornando verdadeiro
     * para cadastro efetuado ou falso para erro no cadastro.
     * 
     * @param nome
     * @param sobrenome
     * @param cpf
     * @param senha
     * @return booleano
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws IOException 
     */
    public boolean cadastrarPerfil(String nome, String sobrenome, String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        return this.serverHost.cadastrarPerfil(nome, sobrenome, cpf, senha);
    }

    /**Retorna o cpf do perfil
     * armazenado no objeto this.perfil.
     * 
     * @return String
     */
    public String getCpf() {
        return this.perfil.getCpf();
    }
    
    /**Retorna o registro do servidor.
     * 
     * @return Registry
     */
    public Registry getRegistry(){
        return this.registryHost;
    }
    
    /**Método concatena o nome e o sobrenome
     * de um cliente conectado!
     * 
     * 
     * @return String
     */
    public String getNomeCompleto() {
        return this.perfil.getNome() + " " + this.perfil.getSobrenome();
    }

    /**Método que efetua login no servidor, a partir
     * do cpf e a senha cadastrada. Retorna verdadeiro
     * se o login foi efetuado ou falso se não foi possível
     * realizar o mesmo.
     * 
     * @param cpf
     * @param senha
     * @return booleano
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public boolean realizarLogin(String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, FileNotFoundException, ClassNotFoundException {
        this.perfil = this.serverHost.realizarLogin(cpf, senha);
        return this.perfil != null;
    }
    

}
