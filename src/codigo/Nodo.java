/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;



public class Nodo {
    private String nombre;
    private ArrayList<Nodo> hijos;
    private Listas hijos2 = new Listas();
    private String valor;
    private int numNodo;
    
    public Nodo(String nombre)
    {
        setNombre(nombre);
        hijos = new ArrayList<>();
        //setValor("");
        setNumNodo(0);
    }
    
    public void addHijo(Nodo hijo)
    {
        hijos.add(hijo);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the hijos
     */
    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the 
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the numNodo
     */
    public int getNumNodo() {
        return numNodo;
    }

    /**
     * @param numNodo the numNodo to set
     */
    public void setNumNodo(int numNodo) {
        this.numNodo = numNodo;
    }
    
    

    public Listas getHijos2() {
        return hijos2;
    }

    public void setHijos2(Listas hijos2) {
        this.hijos2 = hijos2;
    }

    
    
    
    
}
