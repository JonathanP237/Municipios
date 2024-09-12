package com.mycompany.municipios.Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class algoritmoAvaro {
    
    public static String ejecutarAlgoritmo(int origen, int destino, int[][] matrizAdyacencias, String[] municipios) {
        return calcularRutaAvaraConBacktracking(origen, destino, matrizAdyacencias, municipios);
    }

    public static String calcularRutaAvaraConBacktracking(int origen, int destino, int[][] matrizAdyacencias, String[] municipios) {
        ArrayList<Integer> ruta = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        int[] distanciaTotal = {0};  // Usamos un array para poder pasar por referencia la distancia total

        boolean encontrado = buscarRuta(origen, destino, ruta, visitados, 0, distanciaTotal, matrizAdyacencias);

        if (!encontrado) {
            return "No se encontró una ruta entre " + municipios[origen] + " y " + municipios[destino];
        }

        // Construir la cadena de salida con la ruta y la distancia total
        StringBuilder resultado = new StringBuilder();
        for (int nodo : ruta) {
            resultado.append(municipios[nodo]).append(" -> ");
        }
        resultado.setLength(resultado.length() - 4); // Eliminar la última flecha

        resultado.append(" | Distancia total: ").append(distanciaTotal[0]).append(" km");

        return resultado.toString();
    }

    private static boolean buscarRuta(int nodoActual, int destino, ArrayList<Integer> ruta, Set<Integer> visitados,
                                      int distanciaActual, int[] distanciaTotal, int[][] matrizAdyacencias) {
        ruta.add(nodoActual);
        visitados.add(nodoActual);

        // Si hemos llegado al destino, retornamos true
        if (nodoActual == destino) {
            distanciaTotal[0] = distanciaActual;  // Guardar la distancia total
            return true;
        }

        // Buscar la ciudad más cercana no visitada
        List<Integer> adyacentes = new ArrayList<>();
        for (int i = 0; i < matrizAdyacencias.length; i++) {
            if (matrizAdyacencias[nodoActual][i] != -1 && !visitados.contains(i)) {
                adyacentes.add(i);
            }
        }

        // Ordenar los municipios adyacentes por distancia (comportamiento avaro)
        adyacentes.sort((a, b) -> Integer.compare(matrizAdyacencias[nodoActual][a], matrizAdyacencias[nodoActual][b]));

        // Intentar cada adyacente (backtracking si no se encuentra un camino)
        for (int adyacente : adyacentes) {
            int distancia = matrizAdyacencias[nodoActual][adyacente];
            if (buscarRuta(adyacente, destino, ruta, visitados, distanciaActual + distancia, distanciaTotal, matrizAdyacencias)) {
                return true;
            }
        }

        // Si no encontramos ruta, retrocedemos
        ruta.remove(ruta.size() - 1);
        visitados.remove(nodoActual);
        return false;
    }
}