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
public class Etiquetas {
    
     String Verdadero ;
     String Falso ;
    int cont;
     String nombre;
     
    public Etiquetas(int contador) {
         nombre =  "etq" + contador;
         cont = contador;
    }

     
     
     
    public String getVerdadero() {
        return Verdadero;
    }

    public void setVerdadero(String Verdadero) {
        this.Verdadero = Verdadero;
    }

    public String getFalso() {
        return Falso;
    }

    public void setFalso(String Falso) {
        this.Falso = Falso;
    }
     
     
     
     
     
     
  
    
    
    
}
