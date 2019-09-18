/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Teeu Guima
 */
public class Trecho implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id = 0;
    private static int ID = 0;
    private String companhia;

    private String nome;
    private String origem;
    private String destino;

    private String dataIda;
    private String dataVolta;

    private ArrayList<String> ida;
    private ArrayList<String> volta;

    private int quantidade = 0;
    private double preco;

    public Trecho(String companhia, String nome, String origem, String destino, ArrayList<String> ida, ArrayList<String> volta, double preco, int quantidade) {
        this.id = ID++;
        this.companhia = companhia;
        this.origem = origem;
        this.destino = destino;
        this.ida = ida;
        this.volta = volta;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Trecho(String nome, String ida, String volta, double preco) {
        this.id = ID++;
        this.dataIda = ida;
        this.dataVolta = volta;
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getIda(String ida) {
        Iterator iterIdas = this.ida.iterator();
        while (iterIdas.hasNext()) {
            String data = (String) iterIdas.next();
            if (data.compareTo(ida) == 0) {
                return data;
            }
        }
        return null;
    }

    public void setIda(ArrayList<String> ida) {
        this.ida = ida;
    }

    public String getVolta(String volta) {
        Iterator iterVoltas = this.volta.iterator();
        while (iterVoltas.hasNext()) {
            String data = (String) iterVoltas.next();
            if (data.compareTo(volta) == 0) {
                return data;
            }
        }
        return null;
    }

    public void setVolta(ArrayList<String> volta) {
        this.volta = volta;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDataIda() {
        return dataIda;
    }

    public void setDataIda(String dataIda) {
        this.dataIda = dataIda;
    }

    public String getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(String dataVolta) {
        this.dataVolta = dataVolta;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
    public void removerDataIda(String ida){
        Iterator iterIdas = this.ida.iterator();
        while (iterIdas.hasNext()) {
            String data = (String) iterIdas.next();
            if (data.compareTo(ida) == 0) {
                    this.ida.remove(data);
            }
        }
    }
    
    public void removerDataVolta(String volta){
        Iterator iterVoltas = this.volta.iterator();
        while (iterVoltas.hasNext()) {
            String data = (String) iterVoltas.next();
            if (data.compareTo(volta) == 0) {
                this.volta.remove(data);
            }
        }
    }
    

    @Override
    public String toString() {

        return "Trecho:" + id + "\nOrigem: " + nome + "\nIda: " + ida + "\nVolta:" + volta + "\n Preco: " + preco;
    }

}
