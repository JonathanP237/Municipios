package com.mycompany.municipios.Controlador;

import com.mycompany.municipios.Modelo.Grafo;
import com.mycompany.municipios.Modelo.ArbolExpansionMinima;
import com.mycompany.municipios.Modelo.Municipios;
import com.mycompany.municipios.Modelo.algoritmoA;
import com.mycompany.municipios.Modelo.algoritmoAvaro;
import com.mycompany.municipios.vista.Ventana;

public class Controller {
    private Ventana ventana;

    public Controller(Ventana ventana) {
        this.ventana = ventana;
    }

    public void ejecutarAlgoritmo(int origen, int destino, int opcion) {
        switch (opcion) {
            case 1:
                ventana.textPane.setText(algoritmoAvaro.ejecutarAlgoritmo(origen, destino, Municipios.matrizAdyacencias,
                        Municipios.municipios));
                break;
            case 2:
                ventana.textPane.setText(algoritmoA.ejecutarAlgoritmo(origen, destino, Municipios.matrizAdyacencias,
                        Municipios.municipios));
                break;
            case 3:
                String origin = Municipios.municipios[origen];
                String destiny = Municipios.municipios[destino];

                int[][] matriz = new int[Municipios.matrizAdyacencias.length][Municipios.matrizAdyacencias.length];

                for (int i = 0; i < Municipios.matrizAdyacencias.length; i++) {
                    for (int j = 0; j < Municipios.matrizAdyacencias[i].length; j++) {
                        if (Municipios.matrizAdyacencias[i][j] != -1 && i != j) {
                            matriz[i][j] = Municipios.matrizAdyacencias[i][j];
                        }
                    }
                }
                Grafo grafo = new Grafo(Municipios.municipios, matriz);
                ventana.textPane.setText(grafo.encontrarRutaMinimaDijkstra(origin, destiny));
                break;
            case 4:
                ventana.textPane.setText(ArbolExpansionMinima.ejecutarAlgoritmoPrim(Municipios.matrizAdyacencias, Municipios.municipios));
                break;                
            case 5:                
                ventana.textPane.setText(ArbolExpansionMinima.ejecutarAlgoritmoKruskal(Municipios.matrizAdyacencias, Municipios.municipios));
                break;
            
            case 6:
                String originBell = Municipios.municipios[origen];
                String destinyBell = Municipios.municipios[destino];

                int[][] matrizBell = new int[Municipios.matrizAdyacencias.length][Municipios.matrizAdyacencias.length];

                for (int i = 0; i < Municipios.matrizAdyacencias.length; i++) {
                    for (int j = 0; j < Municipios.matrizAdyacencias[i].length; j++) {
                        if (Municipios.matrizAdyacencias[i][j] != -1 && i != j) {
                            matrizBell[i][j] = Municipios.matrizAdyacencias[i][j];
                        }
                    }
                }
                Grafo grafoBell = new Grafo(Municipios.municipios, matrizBell);
                ventana.textPane.setText(grafoBell.encontrarRutaMinimaBellmanFord(originBell, destinyBell));
                break;
        }
    }
}