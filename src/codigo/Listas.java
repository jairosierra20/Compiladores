/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;

/**
 *
 * @author renat
 */

public class Listas {
    ArrayList<Integer> Verdadero = new ArrayList();
    ArrayList<Integer> Falso = new ArrayList();
    ArrayList<Integer> Siguiente = new ArrayList();
    ArrayList<Integer> List = new ArrayList();

    public Listas() {
        
        
    }
    
    

    public ArrayList<Integer> getVerdadero() {
        return Verdadero;
    }

    public void setVerdadero(ArrayList<Integer> Verdadero) {
        this.Verdadero = Verdadero;
    }

    public ArrayList<Integer> getFalso(){
        return Falso;
    }

    public void setFalso(ArrayList<Integer> Falso) {
        this.Falso = Falso;
    }
    
    public ArrayList<Integer> getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(ArrayList<Integer> Siguiente) {
        this.Siguiente = Siguiente;
    }

    public ArrayList<Integer> getList() {
        return List;
    }

    public void setList(ArrayList<Integer> List) {
        this.List = List;
    }

    public ArrayList<String> getLista2() {
        return Lista2;
    }

    public void setLista2(ArrayList<String> Lista2) {
        this.Lista2 = Lista2;
    }
    
    
     ArrayList<String> Lista2 = new ArrayList();
    
}
