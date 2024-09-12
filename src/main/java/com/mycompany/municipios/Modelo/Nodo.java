/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.municipios.Modelo;

/**
 *
 * @author jssol
 */
public class Nodo implements Comparable<Nodo> {
    String muni;
    int  distancia   = Integer.MAX_VALUE;
    Nodo procedencia = null;
    
    public Nodo(String x, double d, Nodo p) { 
        muni=x; distancia=(int) d; procedencia=p; 
    }
    
    public Nodo(String x) {  
        this(x, 0, null); 
    }
    
    public int compareTo(Nodo tmp) { 
        return this.distancia-tmp.distancia; 
    }
    
    
    public boolean equals(Object o) {
        Nodo tmp = (Nodo) o;
        if(this.muni.equals(tmp.muni)) return true;
        return false;
    }
}