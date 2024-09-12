package com.mycompany.municipios.Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class algoritmoAvaro {
    // Ejecutar algoritmo de búsqueda de ruta más corta
    public static String ejecutarAlgoritmo(int origen, int destino, int[][] matrizAdyacencias, String[] municipios) {
        return calcularRutaAvara(origen, destino, matrizAdyacencias, municipios);
    }

    // Calcular la ruta más corta de acuerdo al algoritmo de búsqueda avara
    public static String calcularRutaAvara(int origen, int destino, int[][] matrAdyacencias, String[] ciudades) {
        // Crear una lista para almacenar la ruta
        ArrayList<Integer> ruta = new ArrayList<>();
        // Crear un conjunto para almacenar los municipios visitados
        Set<Integer> visitados = new HashSet<>();
        
        // Inicializar el valor de la distancia total con el resultado del método de búsqueda
        int distanciaTotal = buscarRuta(origen, destino, ruta, visitados, 0, matrAdyacencias);

        // Si la distancia total es "infinita", no hay ruta disponible
        if (distanciaTotal == Integer.MAX_VALUE) {
            return "No se encontró una ruta entre " + ciudades[origen] + " y " + ciudades[destino];
        }

        // Construir el resultado para mostrar
        StringBuilder resultado = new StringBuilder();
        for (int nodo : ruta) {
            resultado.append(ciudades[nodo]).append(" -> ");
        }
        if (!ruta.isEmpty()) {
            resultado.delete(resultado.length() - 4, resultado.length());  // Quitar la flecha final
        }
        resultado.append("\nDistancia total: ").append(distanciaTotal);

        return resultado.toString() + " km";
    }

    // Buscar la ruta más corta de acuerdo al algoritmo de búsqueda avara
    private static int buscarRuta(int nodoActual, int destino, ArrayList<Integer> ruta, Set<Integer> visitados,
            int distanciaActual, int[][] matrAdyacencias) {
                
        // Agregar el municipio actual a la ruta y a los municipios visitados
        ruta.add(nodoActual);
        visitados.add(nodoActual);

        // Si el municipio actual es el destino, devolvemos la distancia acumulada
        if (nodoActual == destino) {
            return distanciaActual;
        }

        // Inicializar la menor distancia a infinito y siguiente nodo a -1
        int menorDistancia = Integer.MAX_VALUE;
        int siguienteNodo = -1;

        // Buscar el vecino no visitado más cercano
        for (int i = 0; i < matrAdyacencias.length; i++) {
            // Verificamos que haya conexión válida y que no esté visitado
            if (matrAdyacencias[nodoActual][i] > 0 && !visitados.contains(i)) {
                if (matrAdyacencias[nodoActual][i] < menorDistancia) {
                    menorDistancia = matrAdyacencias[nodoActual][i];
                    siguienteNodo = i;
                }
            }
        }

        // Si no se encontró un nodo siguiente válido, retrocedemos
        if (siguienteNodo == -1) {
            ruta.remove(ruta.size() - 1);  // Quitamos el nodo actual de la ruta
            visitados.remove(nodoActual);  // Quitamos el nodo actual de los visitados
            return Integer.MAX_VALUE;      // No hay ruta desde este nodo
        }

        // Continuar hacia el siguiente nodo más cercano y acumular la distancia
        return buscarRuta(siguienteNodo, destino, ruta, visitados, distanciaActual + menorDistancia, matrAdyacencias);
    }
}