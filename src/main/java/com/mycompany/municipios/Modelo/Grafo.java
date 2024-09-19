/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.municipios.Modelo;

/**
 *
 * @author jssol
 */
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.*;

public class Grafo {
    String[] nodos; // Letras de identificación de nodo
    int[][] grafo; // Matriz de distancias entre nodos
    String rutaMasCorta; // distancia más corta
    int longitudMasCorta = Integer.MAX_VALUE; // ruta más corta
    List<Nodo> listos = null; // nodos revisados Dijkstra

    public Grafo(String[] seriesNodos, int[][] matrizAdyacencias) {
        nodos = seriesNodos;
        grafo = matrizAdyacencias;
    }

    // retorna la posición en el arreglo de un nodo específico
    private int posicionNodo(String nodo) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].equals(nodo))
                return i;
        }
        return -1;
    }

    // encuentra la ruta más corta desde un nodo origen a un nodo destino
    public String encontrarRutaMinimaDijkstra(String inicio, String fin) {
        // calcula la ruta más corta del inicio a los demás
        encontrarRutaMinimaDijkstra(inicio);
        // recupera el nodo final de la lista de terminados
        Nodo tmp = new Nodo(fin);
        if (!listos.contains(tmp)) {
            System.out.println("Error, nodo no alcanzable");
            return "Bye";
        }
        tmp = listos.get(listos.indexOf(tmp));
        int distancia = tmp.distancia;
        // crea una pila para almacenar la ruta desde el nodo final al origen
        Stack<Nodo> pila = new Stack<Nodo>();
        while (tmp != null) {
            pila.add(tmp);
            tmp = tmp.procedencia;
        }
        String ruta = "";
        // recorre la pila para armar la ruta en el orden correcto
        while (!pila.isEmpty())
            ruta += (pila.pop().muni + " -> ");
        return ruta + distancia + "km: ";
    }

    // encuentra la ruta más corta desde el nodo inicial a todos los demás
    public void encontrarRutaMinimaDijkstra(String inicio) {
        Queue<Nodo> cola = new PriorityQueue<Nodo>(); // cola de prioridad
        Nodo ni = new Nodo(inicio); // nodo inicial

        listos = new LinkedList<Nodo>();// lista de nodos ya revisados
        cola.add(ni); // Agregar nodo inicial a la cola de prioridad
        while (!cola.isEmpty()) { // mientras que la cola no esta vacia
            Nodo tmp = cola.poll(); // saca el primer elemento
            listos.add(tmp); // lo manda a la lista de terminados
            int p = posicionNodo(tmp.muni);
            for (int j = 0; j < grafo[p].length; j++) { // revisa los nodos hijos del nodo tmp
                if (grafo[p][j] == 0)
                    continue; // si no hay conexión no lo evalua
                if (estaTerminado(j))
                    continue; // si ya fue agregado a la lista de terminados
                Nodo nod = new Nodo(nodos[j], (int) (tmp.distancia + grafo[p][j]), tmp);
                // si no está en la cola de prioridad, lo agrega
                if (!cola.contains(nod)) {
                    cola.add(nod);
                    continue;
                }
                // si ya está en la cola de prioridad actualiza la distancia menor
                for (Nodo x : cola) {
                    // si la distancia en la cola es mayor que la distancia calculada
                    if (nod.muni.equals(x.muni) && x.distancia > nod.distancia) {
                        cola.remove(x); // remueve el nodo de la cola
                        cola.add(nod); // agrega el nodo con la nueva distancia
                        break; // no sigue revisando
                    }
                }
            }
        }
    }

    // verifica si un nodo ya está en lista de terminados
    public boolean estaTerminado(int j) {
        Nodo tmp = new Nodo(nodos[j]);
        return listos.contains(tmp);
    }
    
    public String encontrarRutaMinimaPrim(String inicio, String fin) {
        boolean[] seleccionados = new boolean[nodos.length];
        int[] parent = new int[nodos.length];
        int[] key = new int[nodos.length];

        for (int i = 0; i < nodos.length; i++) {
            key[i] = Integer.MAX_VALUE;
            seleccionados[i] = false;
        }

        key[posicionNodo(inicio)] = 0;
        parent[posicionNodo(inicio)] = -1;

        for (int count = 0; count < nodos.length - 1; count++) {
            int u = minKey(key, seleccionados);
            seleccionados[u] = true;

            for (int v = 0; v < nodos.length; v++) {
                if (grafo[u][v] != 0 && !seleccionados[v] && grafo[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = grafo[u][v];
                }
            }
        }

        return construirRutaDesdeArbol(parent, fin);
    }

    private int minKey(int[] key, boolean[] seleccionados) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < nodos.length; v++) {
            if (!seleccionados[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private String construirRutaDesdeArbol(int[] parent, String destino) {
        int destinoIndex = posicionNodo(destino);
        Stack<String> ruta = new Stack<>();
        int cost = 0;

        while (parent[destinoIndex] != -1) {
            ruta.push(nodos[destinoIndex]);
            cost += grafo[parent[destinoIndex]][destinoIndex];
            destinoIndex = parent[destinoIndex];
        }

        ruta.push(nodos[destinoIndex]); // Add start node

        StringBuilder resultado = new StringBuilder();
        while (!ruta.isEmpty()) {
            resultado.append(ruta.pop());
            if (!ruta.isEmpty()) {
                resultado.append(" -> ");
            }
        }
        resultado.append(" : ").append(cost).append("km");

        return resultado.toString();
    }

    public String encontrarRutaMinimaKruskal(String inicio, String fin) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < grafo.length; i++) {
            for (int j = i + 1; j < grafo[i].length; j++) {
                if (grafo[i][j] > 0) {
                    edges.add(new Edge(i, j, grafo[i][j]));
                }
            }
        }
        Collections.sort(edges);

        int[] parent = new int[nodos.length];
        for (int i = 0; i < nodos.length; i++) {
            parent[i] = i;
        }

        List<Edge> mstEdges = new ArrayList<>();
        for (Edge edge : edges) {
            int root1 = find(parent, edge.src);
            int root2 = find(parent, edge.dest);

            if (root1 != root2) {
                mstEdges.add(edge);
                parent[root1] = root2;
            }
        }

        return construirRutaDesdeArbolMST(mstEdges, inicio, fin);
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private String construirRutaDesdeArbolMST(List<Edge> edges, String inicio, String fin) {
        // Convert edges to an adjacency list or matrix (graph representation)
        int inicioIndex = posicionNodo(inicio);
        int finIndex = posicionNodo(fin);

        // Implement logic to find the path from inicioIndex to finIndex in the MST edges
        // Can make use of BFS or DFS to track the path in MST

        return ""; // The string representing the path with the total cost
    }

    private static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
   
    public String encontrarRutaMinimaBellmanFord(String inicio, String fin) {
        int[] distancias = new int[nodos.length];
        int[] predecesores = new int[nodos.length];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecesores, -1);
        distancias[posicionNodo(inicio)] = 0;

        for (int i = 0; i < nodos.length - 1; i++) {
            for (int u = 0; u < nodos.length; u++) {
                for (int v = 0; v < nodos.length; v++) {
                    if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                        distancias[u] + grafo[u][v] < distancias[v]) {
                        distancias[v] = distancias[u] + grafo[u][v];
                        predecesores[v] = u;
                    }
                }
            }
        }

        // Verificar ciclos negativos
        for (int u = 0; u < nodos.length; u++) {
            for (int v = 0; v < nodos.length; v++) {
                if (grafo[u][v] != 0 && distancias[u] != Integer.MAX_VALUE &&
                    distancias[u] + grafo[u][v] < distancias[v]) {
                    System.out.println("El grafo contiene ciclos de peso negativo");
                    return "Bye";
                }
            }
        }

        return construirRutaBellmanFord(distancias, predecesores, fin);
    }

    private String construirRutaBellmanFord(int[] distancias, int[] predecesores, String fin) {
        int finIndex = posicionNodo(fin);
        if (distancias[finIndex] == Integer.MAX_VALUE) {
            return "No hay ruta desde el inicio hasta el fin";
        }

        Stack<String> ruta = new Stack<>();
        for (int at = finIndex; at != -1; at = predecesores[at]) {
            ruta.push(nodos[at]);
        }

        StringBuilder resultado = new StringBuilder();
        while (!ruta.isEmpty()) {
            resultado.append(ruta.pop());
            if (!ruta.isEmpty()) {
                resultado.append(" -> ");
            }
        }
        resultado.append(" : ").append(distancias[finIndex]).append("km");

        return resultado.toString();
    }
}
