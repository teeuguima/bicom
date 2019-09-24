/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**Classe que define as informações de uma
 * cidade cadastrada no sistema!
 * 
 * @author Mateus Guimarães
 */
public class Cidade implements Serializable {
    private static final long serialVersionUID = 3L;
    private String nome;
    private String aeroporto;
    private int codigoDoLocal;

    public Cidade(int codigoDoLocal,String nome, String aeroporto) {
        this.nome = nome;
        this.codigoDoLocal = codigoDoLocal;
        this.aeroporto = aeroporto;
    }

    public String getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(String aeroporto) {
        this.aeroporto = aeroporto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoDoLocal() {
        return codigoDoLocal;
    }

    public void setCodigoDoLocal(int codigoDoLocal) {
        this.codigoDoLocal = codigoDoLocal;
    }

       
}
