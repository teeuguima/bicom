/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**Classe que define as informações sobre 
 * a reserva realizada por um perfil cadastrado
 * no sistema! Tendo como informações um trecho
 * selecionado e o cpf do cliente.
 * 
 * @author Mateus Guimarães
 */
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    String cpf;
    Trecho trecho;
    
    public Reserva(String cpf, Trecho trecho){
        this.cpf = cpf;
        this.trecho = trecho;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }
    
    
    
}
