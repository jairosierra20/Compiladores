/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

/**
 *
 * @author renat
 */
public class Funcion {
    String argumentos;
    String retorno;
    
    
    public Funcion() {
       
    }

    public Funcion(String argumentos, String retorno) {
        this.argumentos = argumentos;
        this.retorno = retorno;
    }

    public String getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(String argumentos) {
        this.argumentos = argumentos;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    
    public String getTipo() {
        return argumentos + "->" + retorno;
    }

    
    
    
    
}
