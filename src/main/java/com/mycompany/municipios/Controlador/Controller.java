package com.mycompany.municipios.Controlador;

import com.mycompany.municipios.Modelo.Grafo;
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
                System.out.print("\nOrigen: " + origin + " - Destino: " + destiny + "\n");

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
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                System.out.println(opcion);
                break;
        }
    }
}