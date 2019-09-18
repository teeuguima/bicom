package util;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 *
 * @author matee
 */
public class Grafos implements Serializable {

    private String label;
    private ArrayList<Vertex> vertices;

    private Map<Vertex, List<Vertex>> adjVertices;
    static int adcVertex;
    static int adcArestas;

    public Grafos() {
        this("", new ArrayList<Vertex>());
    }

    public Grafos(String aLabel) {
        this(aLabel, new ArrayList<Vertex>());
    }

    public Grafos(String aLabel, ArrayList<Vertex> initialNodes) {
        label = aLabel;
        vertices = initialNodes;
    }

    public void addVertices(Vertex v, Vertex a) {
        adjVertices.putIfAbsent(v, new ArrayList<>());
        adjVertices.putIfAbsent(a, new ArrayList<>());

        adjVertices.get(v).add(a);
        adjVertices.get(a).add(v);
    }

    public void removeVertex(Vertex v) {
        // Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        //adjVertices.remove(new Vertex(label));
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public Vertex get(Vertex v) {
        for (Vertex vertice : vertices) {
            if (vertice.equals(v)) {
                return vertice;
            }
        }
        return null;
        
        
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String newLabel) {
        label = newLabel;
    }

    public ArrayList<Edge> getEdges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (Vertex n : vertices) {
            for (Edge e : n.incidentEdges()) {
                if (!edges.contains(e)) //so that it is not added twice
                {
                    edges.add(e);
                }
            }
        }
        return edges;
    }

    public boolean addVertex(Vertex aVertice) {
        return vertices.add(aVertice);

    }

    public void addEdge(Vertex one, Vertex two, int peso) {
        Edge anEdge = new Edge(one, two, peso);
        one.addIncidentEdge(anEdge);
        two.addIncidentEdge(anEdge);
    }

    public Vertex nodeNamed(String nomeVertice) {
        for (Vertex n : vertices) {
            if (n.getLabel().equals(nomeVertice)) {
                return n;
            }
        }
        return null;
    }

    public boolean contains(Vertex v) {
        return vertices.contains(v);
    }

    public String bfs(Object data) {
        HashSet<Vertex> visited = new HashSet<>();
        Queue<Vertex> fila = new LinkedList<>();
        fila.add(get((Vertex) data));
        visited.add(get((Vertex) data));
        for (int i = 0; !fila.isEmpty(); i++) {
            Vertex v = fila.poll();
            /*
            for (Vertex u : v.getAdjVertices()) {
                if (!visited.contains(u)) {

                    fila.add(u);
                    visited.add(u);
                }
            }
        }
    }
             */
        }
        return null;
    }

    public void removeEdge(String label1, String label2) {
        Vertex v1 = new Vertex(null, null);
        Vertex v2 = new Vertex(null, null);
        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);
        if (eV1 != null) {
            eV1.remove(v2);
        }
        if (eV2 != null) {
            eV2.remove(v1);
        }
    }

    

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(null, null));
    }
}
