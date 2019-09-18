package model;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teeu Guima
 */
public class Perfil implements Serializable{
    private static final long serialVersionUID = -7401900171994267556L;
    
    private String nome;
    private String sobrenome;
    private String cpf;
    private byte[] senhaCriptografada;

    public Perfil(String nome, String sobrenome, String cpf, byte[] senhaCriptografada) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public byte[] getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(byte[] senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }
    
    
}
