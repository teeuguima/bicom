package util;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Cidade;
import model.Trecho;

/**
 *
 * @author matee
 */
public class Vertex implements Comparable, Serializable {
    
    
    
    private ArrayList<Edge> neighborhood;
    private String ident; //identificação
    private Trecho trecho; //Cidade cadastrada
    private Cidade cidade;
    private ArrayList<Edge> incidentEdges; //Aresta

    public Vertex(String ident, Cidade cidade) {
        this.cidade = cidade;
        this.ident = ident;
        this.neighborhood = new ArrayList<>();
        this.incidentEdges = new ArrayList<>();
    }
    
   
    public void addNeighbor(Edge edge) {
        if (this.neighborhood.contains(edge)) {
            return;
        }
        this.neighborhood.add(edge);
    }

    public boolean containsNeighbor(Edge other) {
        return this.neighborhood.contains(other);
    }

    public Edge getNeighbor(int index) {
        return this.neighborhood.get(index);
    }

    public Edge removeNeighbor(int index) {
        return this.neighborhood.remove(index);
    }

    public void removeNeighbor(Edge e) {
        this.neighborhood.remove(e);
    }

    public int getNeighborCount() {
        return this.neighborhood.size();
    }

    public String getLabel() {
        return this.ident;
    }

    public String toString() {
        return "Vertex" + ident;
    }

    public int hashCode() {
        return this.ident.hashCode();
    }

    public ArrayList<Edge> incidentEdges() {
        return incidentEdges;
    }

    public void addIncidentEdge(Edge e) {
        incidentEdges.add(e);
    }

    public boolean equals(Object other) {
        if (!(other instanceof Vertex)) {
            return false;
        }

        Vertex v = (Vertex) other;
        return this.ident.equals(v.ident);
    }

    public ArrayList<Edge> getNeighbors() {
        return new ArrayList<>(this.neighborhood);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }


    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
