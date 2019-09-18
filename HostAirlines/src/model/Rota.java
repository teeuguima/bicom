/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Teeu Guima
 */
public class Rota {

    //public ArrayList<String> companhia;
    private ArrayList<Trecho> trechos;
    private String nomeTrecho;

    public Rota() {
        this.trechos = new ArrayList<>();
        //  this.companhia = new ArrayList<>();
    }

    public void adicionarRota(String companhia, String nome, Trecho trecho) {

        if (!hasTrecho(companhia)) {
            Trecho t = new Trecho(companhia, trecho.getNome(), trecho.getOrigem(), trecho.getDestino(), trecho.getDatasIdas(), trecho.getDatasVoltas(), trecho.getPreco(), trecho.getQuantidade());
            this.trechos.add(t);
        }

    }

    public void alterarRota(String companhia, int quantidade, ArrayList<String> ida, ArrayList<String> volta, double preco) {
        if (hasTrecho(companhia)) {
            Iterator iterCompanhia = trechos.iterator();
            while (iterCompanhia.hasNext()) {
                Trecho t = (Trecho) iterCompanhia.next();
                if (t.getCompanhia().compareTo(companhia) == 0) {
                    this.trechos.remove(t);
                    t.setQuantidade(quantidade);
                    t.setDatasIdas(ida);
                    t.setDatasVoltas(volta);
                    t.setPreco(preco);
                    this.trechos.add(t);
                }
            }

        }

    }

    public void removerTrecho(String companhia) {
        if (hasTrecho(companhia)) {
            Iterator iterCompanhia = trechos.iterator();
            while (iterCompanhia.hasNext()) {
                Trecho t = (Trecho) iterCompanhia.next();
                if (t.getCompanhia().compareTo(companhia) == 0) {
                    this.trechos.remove(t);
                }
            }

        }
    }

    public Trecho buscarTrecho(String companhia) {
        Iterator iterCompanhia = trechos.iterator();
        while (iterCompanhia.hasNext()) {
            Trecho trecho = (Trecho) iterCompanhia.next();
            if (trecho.getCompanhia().compareTo(companhia) == 0) {
                return trecho;
            }
        }
        return null;
    }

    public boolean hasTrecho(String companhia) {
        Iterator iterCompanhia = trechos.iterator();
        while (iterCompanhia.hasNext()) {
            Trecho trecho = (Trecho) iterCompanhia.next();
            if (trecho.getCompanhia().compareTo(companhia) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public Trecho buscarTrecho(String companhia, String ida, String volta){
        Trecho t = buscarTrecho(companhia);
        this.trechos.remove(t);
        t.removerDatas(ida, volta);
        this.trechos.add(t);
        return new Trecho(companhia, ida, volta, t.getPreco());
    }
    
     
    
    public Trecho trechoEncontrado(String ida, String volta){
        Iterator iterTrecho = trechos.iterator();
        while(iterTrecho.hasNext()){
            Trecho t = (Trecho) iterTrecho.next();
            if(t.getDataIda().compareTo(ida) == 0 && t.getDataVolta().compareTo(volta)==0){
                return t;
            }
        }
        return null;
    }
    
    public ArrayList<Trecho> getTrechos(){
        return this.trechos;
    }

    public String getNomeTrecho() {
        return nomeTrecho;
    }

    public void setNomeTrecho(String nomeTrecho) {
        this.nomeTrecho = nomeTrecho;
    }
    
    
}
