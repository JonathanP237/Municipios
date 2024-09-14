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

}
