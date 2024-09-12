package com.mycompany.municipios.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class algoritmoA {

    static HashMap<Integer, Integer> distanciasBogota = new HashMap<>();
    static HashMap<Integer, Integer> distanciasCucuta = new HashMap<>();
    
    // Adicionar datos de distancias entre municipios
    public static void adicionarDatosBogota() {
        distanciasBogota.put(1, 0);
        distanciasBogota.put(2, 241);
        distanciasBogota.put(3, 165);
        distanciasBogota.put(4, 126);
        distanciasBogota.put(5, 294);
        distanciasBogota.put(6, 179);
        distanciasBogota.put(7, 131);
        distanciasBogota.put(8, 401);
        distanciasBogota.put(9, 177);
        distanciasBogota.put(10, 233);
        distanciasBogota.put(11, 373);
        distanciasBogota.put(12, 301);
        distanciasBogota.put(13, 47);
        distanciasBogota.put(14, 73);
        distanciasBogota.put(15, 306);
        distanciasBogota.put(16, 110);
        distanciasBogota.put(17, 242);
        distanciasBogota.put(18, 112);
        distanciasBogota.put(19, 174);
        distanciasBogota.put(20, 341);
        distanciasBogota.put(21, 61);
        distanciasBogota.put(22, 410);
        distanciasBogota.put(23, 179);
        distanciasBogota.put(24, 204);
        distanciasBogota.put(25, 82);
        distanciasBogota.put(26, 129);
        distanciasBogota.put(27, 201);
        distanciasBogota.put(28, 77);
        distanciasBogota.put(29, 269);
        distanciasBogota.put(30, 190);
    }

    public static void adicionarDatosCucuta() {
        distanciasCucuta.put(1, 568);
        distanciasCucuta.put(2, 579);
        distanciasCucuta.put(3, 829);
        distanciasCucuta.put(4, 429);
        distanciasCucuta.put(5, 294);
        distanciasCucuta.put(6, 198);
        distanciasCucuta.put(7, 878);
        distanciasCucuta.put(8, 0);
        distanciasCucuta.put(9, 789);
        distanciasCucuta.put(10, 875);
        distanciasCucuta.put(11, 1147);
        distanciasCucuta.put(12, 968);
        distanciasCucuta.put(13, 546);
        distanciasCucuta.put(14, 496);
        distanciasCucuta.put(15, 806);
        distanciasCucuta.put(16, 459);
        distanciasCucuta.put(17, 877);
        distanciasCucuta.put(18, 752);
        distanciasCucuta.put(19, 358);
        distanciasCucuta.put(20, 74);
        distanciasCucuta.put(21, 647);
        distanciasCucuta.put(22, 253);
        distanciasCucuta.put(23, 517);
        distanciasCucuta.put(24, 780);
        distanciasCucuta.put(25, 712);
        distanciasCucuta.put(26, 638);
        distanciasCucuta.put(27, 491);
        distanciasCucuta.put(28, 683);
        distanciasCucuta.put(29, 311);
        distanciasCucuta.put(30, 531);
    }

    // Ejecutar algoritmo A* deacuerdo al destino
    public static String ejecutarAlgoritmo(int origen, int destino, int[][] matrizAdyacencias, String[] municipios) {
        if (destino == 1) {
            adicionarDatosBogota();
            return calcularRutaAStar(origen, destino, matrizAdyacencias, municipios, distanciasBogota);
        } else {
            adicionarDatosCucuta();
            return calcularRutaAStar(origen, destino, matrizAdyacencias, municipios, distanciasCucuta);
        }

    }

    // Calcular la ruta A* deacuerdo al destino
    public static String calcularRutaAStar(int origen, int destino, int[][] matrAdyacencias, String[] ciudades,
            Map<Integer, Integer> distanciasDestino) {
        // Crear una cola de prioridad para almacenar los municipios abiertos
        PriorityQueue<Nodo> abiertos = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        // Crear un conjunto para almacenar los municipios cerrados es decir los municipios ya visitados y evaluados
        Set<Integer> cerrados = new HashSet<>();
        // Crear un mapa para almacenar los valores de gScore, fScore y los municipios padres 
        // gscore = distancia desde el origen hasta el nodo actual fScore = gScore + distancia estimada hasta el destino
        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> fScore = new HashMap<>();
        // Crear un mapa para almacenar los municipios padres de cada municipio visitado 
        Map<Integer, Integer> cameFrom = new HashMap<>();

        // Inicializar los valores de gScore y fScore en infinito
        for (int i = 0; i < ciudades.length; i++) {
            gScore.put(i, Integer.MAX_VALUE);
            fScore.put(i, Integer.MAX_VALUE);
        }

        // Inicializar los valores de gScore y fScore en 0 para el municipio de origen
        gScore.put(origen, 0);
        fScore.put(origen, distanciasDestino.get(origen + 1));

        // Agregar el municipio de origen a la cola de prioridad
        abiertos.add(new Nodo(origen, fScore.get(origen)));

        // Mientras la cola de prioridad no esté vacía se evaluarán los municipios
        while (!abiertos.isEmpty()) {
            Nodo actual = abiertos.poll();
            // Si el municipio actual es el municipio destino se reconstruye la ruta
            if (actual.id == destino) {
                return reconstruirRuta(cameFrom, actual.id, ciudades, matrAdyacencias);
            }
            // Se añade el municipio actual al conjunto de municipios cerrados
            cerrados.add(actual.id);

            // Se evalúan los municipios adyacentes al municipio actual y se actualizan los valores de gScore y fScore
            for (int i = 0; i < matrAdyacencias.length; i++) {
                // Si el municipio adyacente no está en el conjunto de nodos cerrados se evalúa
                if (matrAdyacencias[actual.id][i] != -1 && !cerrados.contains(i)) {
                    int tentativeGScore = gScore.get(actual.id) + matrAdyacencias[actual.id][i];
                    // Si el municipio adyacente no está en la cola de prioridad se añade
                    if (tentativeGScore < gScore.get(i)) {
                        cameFrom.put(i, actual.id);
                        gScore.put(i, tentativeGScore);
                        fScore.put(i, tentativeGScore + distanciasDestino.get(i + 1));
                        final int nodo = i;                         
                        if (abiertos.stream().noneMatch(n -> n.id == nodo)) {
                            abiertos.add(new Nodo(nodo, fScore.get(nodo)));
                        }
                    }
                }
            }
        }

        return "No hay ruta disponible";
    }
    // Reconstruir la ruta 
    private static String reconstruirRuta(Map<Integer, Integer> cameFrom, int actual, String[] ciudades,
            int[][] matrAdyacencias) {
        List<String> ruta = new ArrayList<>();
        int distanciaTotal = 0;

        // Se reconstruye la ruta desde el municipio destino hasta el municipio origen
        while (cameFrom.containsKey(actual)) {
            ruta.add(ciudades[actual]);
            int previo = cameFrom.get(actual);
            distanciaTotal += matrAdyacencias[previo][actual];
            actual = previo;
        }

        ruta.add(ciudades[actual]);
        Collections.reverse(ruta);

        return String.join(" -> ", ruta) + " | Distancia total: " + distanciaTotal + " km";
    }

    private static class Nodo {
        int id;
        int fScore;

        Nodo(int id, int fScore) {
            this.id = id;
            this.fScore = fScore;
        }
    }
}
