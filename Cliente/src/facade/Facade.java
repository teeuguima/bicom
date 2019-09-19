/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ControladorDeDados;
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

/**
 *
 * @author Teeu Guima
 */
public class Facade {

    private ControladorDeDados dados;
    private Perfil perfil;
    final Registry registryHost;
    //Registry registryHost = LocateRegistry.getRegistry("172.16.103.11",5595);
    InterfaceHostAirlines serverHost;

    public Facade() throws IOException, FileNotFoundException, ClassNotFoundException, RemoteException, NotBoundException {
        this.registryHost = LocateRegistry.getRegistry("172.16.103.5",5595);
        this.serverHost = (InterfaceHostAirlines) registryHost.lookup("OperacoesHost");

    }

    public boolean cadastrarPerfil(String nome, String sobrenome, String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        return this.serverHost.cadastrarPerfil(nome, sobrenome, cpf, senha);
        //return this.dados.cadastrarPerfil(new Perfil(nome, sobrenome,cpf, dados.cadastrarSenha(senha)));

    }

    public String getCpf() {
        return this.perfil.getCpf();
    }
    
    public Registry getRegistry(){
        return this.registryHost;
    }

    public String getNomeCompleto() {
        return this.perfil.getNome() + " " + this.perfil.getSobrenome();
    }

    public boolean realizarLogin(String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, FileNotFoundException, ClassNotFoundException {
        this.perfil = this.serverHost.realizarLogin(cpf, senha);
        return this.perfil != null;
    }
    /*
    public void salvarDados() throws IOException{
        this.dados.salvandoDados();
    }
    
    public void lerDados() throws IOException, FileNotFoundException, ClassNotFoundException{
        dados.lendoDados();
    }
    
    public void criarArquivo() throws IOException, FileNotFoundException, ClassNotFoundException{
        this.dados.criandoArquivos();
    }
     */

}
