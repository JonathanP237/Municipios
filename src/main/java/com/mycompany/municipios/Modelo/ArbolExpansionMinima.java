package com.mycompany.municipios.Modelo;

import java.util.*;

class Arista implements Comparable<Arista> {
    int origen, destino, peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista otra) {
        return this.peso - otra.peso;
    }
}

public class ArbolExpansionMinima {
    private static int[] padre;

    public static int find(int i) {
        if (padre[i] != i) {
            padre[i] = find(padre[i]);
        }
        return padre[i];
    }

    public static void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);
        if (ri != rj) {
            padre[ri] = rj;
        }
    }

    public static String ejecutarAlgoritmoKruskal(int[][] matrizAdyacencias, String[] municipios) {
        int n = matrizAdyacencias.length;
        List<Arista> aristas = new ArrayList<>();

        // Crear lista de aristas a partir de la matriz de adyacencias
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrizAdyacencias[i][j] > 0) {
                    aristas.add(new Arista(i, j, matrizAdyacencias[i][j]));
                }
            }
        }

        // Ordenar aristas por peso
        Collections.sort(aristas);

        // Inicializar Union-Find
        padre = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
        }

        StringBuilder resultado = new StringBuilder();
        for (Arista arista : aristas) {
            int origen = arista.origen;
            int destino = arista.destino;
            if (find(origen) != find(destino)) {
                resultado.append(municipios[origen]).append(" -> ").append(municipios[destino]).append("\n");
                union(origen, destino);
            }
        }

        return resultado.toString();
    }

    public static String ejecutarAlgoritmoPrim(int[][] matrizAdyacencias, String[] municipios) {
        int n = matrizAdyacencias.length;
        boolean[] visitado = new boolean[n];
        PriorityQueue<Arista> pq = new PriorityQueue<>();
        StringBuilder resultado = new StringBuilder();

        // Iniciar desde el primer nodo
        visitado[0] = true;
        for (int j = 0; j < n; j++) {
            if (matrizAdyacencias[0][j] > 0) {
                pq.add(new Arista(0, j, matrizAdyacencias[0][j]));
            }
        }

        while (!pq.isEmpty()) {
            Arista arista = pq.poll();
            if (!visitado[arista.destino]) {
                visitado[arista.destino] = true;
                resultado.append(municipios[arista.origen]).append(" -> ").append(municipios[arista.destino]).append("\n");

                for (int j = 0; j < n; j++) {
                    if (matrizAdyacencias[arista.destino][j] > 0 && !visitado[j]) {
                        pq.add(new Arista(arista.destino, j, matrizAdyacencias[arista.destino][j]));
                    }
                }
            }
        }

        return resultado.toString();
    }
}