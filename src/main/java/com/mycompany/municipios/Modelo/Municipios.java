/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.municipios.Modelo;

/**
 *
 * @author JyL
 */
public class Municipios {
        // Matriz de adyacencias
    public static int[][] matrizAdyacencias = {
            // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29
            // 30
            { 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, 83, -1, -1, -1, 204, -1, -1, 100, -1, -1, -1, -1, -1,
                    -1, 109, -1, -1 }, // 1
            { -1, 0, 213, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 234, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, 233 }, // 2
            { -1, 213, 0, -1, -1, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 214, -1, -1, 83, -1, -1,
                    -1, -1, -1, -1 }, // 3
            { -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, 79, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1 }, // 4
            { -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 266, -1, -1, 268, 126, -1, 231, -1, -1, -1, -1,
                    -1, -1, 117, -1 }, // 5
            { -1, -1, 51, -1, -1, 0, -1, -1, 53, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1,
                    -1, -1, -1 }, // 6
            { -1, -1, -1, -1, -1, -1, 0, -1, 77, 207, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, 87, -1, -1 }, // 7
            { -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, 257, -1, -1, -1, -1,
                    -1, -1, -1, -1 }, // 8
            { -1, -1, -1, -1, -1, 53, 77, -1, 0, -1, -1, -1, -1, -1, -1, -1, 90, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1 }, // 9
            { -1, -1, -1, -1, -1, -1, 207, -1, -1, 0, 268, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, 196, -1, -1 }, // 10
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, 268, 0, 134, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1 }, // 11
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 134, 0, -1, -1, -1, -1, 100, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1 }, // 12
            { 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 56, -1, 96, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1 }, // 13
            { 83, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, 56, 0, -1, 83, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1 }, // 14
            { -1, 234, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 247, -1, -1,
                    -1, -1, -1, -1 }, // 15
            { -1, -1, -1, 79, 266, -1, -1, -1, -1, -1, -1, -1, 96, 83, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, 238 }, // 16
            { -1, -1, -1, -1, -1, -1, -1, -1, 90, -1, -1, 100, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 85, -1, -1,
                    -1, -1, -1, -1 }, // 17
            { 204, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 30, 116,
                    -1, -1, -1, -1 }, // 18
            { -1, -1, -1, 56, 268, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 305, -1, -1, 141, -1, -1, -1,
                    -1, -1, -1, -1 }, // 19
            { -1, -1, -1, -1, 126, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 305, 0, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1 }, // 20
            { 100, -1, 214, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1 }, // 21
            { -1, -1, -1, -1, 231, -1, -1, 257, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1,
                    -1, -1, 190, -1 }, // 22
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 141, -1, -1, -1, 0, -1, -1, 122,
                    26, -1, -1, -1 }, // 23
            { -1, -1, 83, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, 247, -1, 85, -1, -1, -1, -1, -1, -1, 0, -1, -1,
                    -1, -1, -1, -1 }, // 24
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, 0, -1, -1,
                    -1, -1, -1 }, // 25
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 116, -1, -1, -1, -1, 122, -1, -1, 0,
                    -1, -1, -1, -1 }, // 26
            { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, -1, -1, -1, 0,
                    -1, -1, -1 }, // 27
            { 109, -1, -1, -1, -1, -1, 87, -1, -1, 196, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, 0, -1, -1 }, // 28
            { -1, -1, -1, -1, 117, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 190, -1, -1, -1, -1,
                    -1, -1, 0, 143 }, // 29
            { -1, 233, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 238, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, 143, 0 } // 30
    };
    // Coordenadas de los municipios en el mapa
    public static int[][] coordenadasMapa = {
        { 348, 449 }, // 1
        { 263, 371 }, // 2
        { 270, 415 }, // 3
        { 394, 403 }, // 4
        { 404, 305 }, // 5
        { 258, 435 }, // 6
        { 278, 468 }, // 7
        { 434, 270 }, // 8
        { 259, 457 }, // 9
        { 287, 548 }, // 10
        { 215, 564 }, // 11
        { 205, 512 }, // 12
        { 352, 423 }, // 13
        { 369, 431 }, // 14
        { 203, 416 }, // 15
        { 366, 390 }, // 16
        { 230, 470 }, // 17
        { 381, 480 }, // 18
        { 412, 380 }, // 19
        { 426, 289 }, // 20
        { 328, 419 }, // 21
        { 373, 244 }, // 22
        { 438, 420 }, // 23
        { 237, 448 }, // 24
        { 365, 500 }, // 25
        { 420, 450 }, // 26
        { 443, 404 }, // 27
        { 315, 475 }, // 28
        { 367, 318 }, // 29
        { 330, 345 } // 30
    };
    // Nombres de los municipios
    public static String[] municipios = {
            "Bogota",
            "Medellin",
            "Manizales",
            "Tunja",
            "Bucaramanga",
            "Pereira",
            "Ibague",
            "Cucuta",
            "Armenia",
            "Neiva",
            "Popayan",
            "Cali",
            "Zipaquira",
            "Choconta",
            "Quibdo",
            "Chiquinquira",
            "Tulua",
            "Villavicencio",
            "Duitama",
            "Pamplona",
            "Villeta",
            "Aguachica",
            "Aguazul",
            "Cartago",
            "Acacias",
            "Villanueva",
            "Yopal",
            "Melgar",
            "Barrancabermeja",
            "PuertoBerrio"
    };
}