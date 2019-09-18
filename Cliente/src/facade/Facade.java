/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import controladores.ControladorDeDados;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import model.Perfil;

/**
 *
 * @author Teeu Guima
 */
public class Facade {
    private ControladorDeDados dados;
    private static Facade facade;
    
    
    public Facade() throws IOException, FileNotFoundException, ClassNotFoundException{
        this.dados = new ControladorDeDados();
    }
    
    public static synchronized Facade getInstance() throws IOException, FileNotFoundException, ClassNotFoundException {
        return (facade == null) ? facade = new Facade() : facade;
    }
    
    public boolean cadastrarPerfil(String nome, String sobrenome, String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException{
        
        return this.dados.cadastrarPerfil(new Perfil(nome, sobrenome,cpf, dados.cadastrarSenha(senha)));
   
    }
    
    public String getCpf(){
        return this.dados.getCpf();
    }
    
    public boolean realizarLogin(String cpf, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, FileNotFoundException, ClassNotFoundException{
     
        return this.dados.realizarLogin(cpf, senha);
    }
  
    public void salvarDados() throws IOException{
        this.dados.salvandoDados();
    }
    
    public void lerDados() throws IOException, FileNotFoundException, ClassNotFoundException{
        dados.lendoDados();
    }
    
    public void criarArquivo() throws IOException, FileNotFoundException, ClassNotFoundException{
        this.dados.criandoArquivos();
    }
 
     
}
