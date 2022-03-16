/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;


public class Tabla {
    
    
  
    ArrayList<Fila> listaSimbolo = new ArrayList();

    public Tabla() {
    }

    public ArrayList<Fila> getListaSimbolo() {
        
        return listaSimbolo;
    }

    public void setListaSimbolo(ArrayList<Fila> listaSimbolo) {
        this.listaSimbolo = listaSimbolo;
    }
    
    
    
    
    // busca si esta en la tabla y revisa el tipo
    
    public String BuscarTipo(Fila n) {
        boolean encontradoN = false;
        boolean encontradoT = false;
        int d = 0;
        
        for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.getNombreVar().equals(listaSimbolo.get(i))) {
                encontradoN = true;
                d = i;
                break;
            }else{
                encontradoN = false;
            }
            
        }
        
        
        
        if (n.getType().equals(listaSimbolo.get(d).Type)) {
            encontradoT = true;
        }else{
             encontradoT = false;
             }
        
        
        if (encontradoN ==  false) {
            return "Error de Tipo, No se ha encontrado la variable" + n.getNombreVar();
        }else if(encontradoN ==  true && encontradoT == false){
          return "Error de Tipo, La variable " + n.getNombreVar() + "no concuerda con el tipo" + n.getType()  ;
        }else{
           return "Encontrado";
        }
        
        
        
    }
    
    
    //// encuentra el tipo a partir del nombre
    public String BuscarTipoN(String n) {
        Fila valEncontrado = new Fila() ;
         int j = 0;
        
         
        
        
        for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.equals(listaSimbolo.get(i).getNombreVar())) {
                valEncontrado = listaSimbolo.get(i);
                
                break;
            }
            j++;
        }
           if (j == (listaSimbolo.size())) {
            return "error_tipo";
        }

     
        return  valEncontrado.getType();

    }
    
    
    
    /// apartir del nombre encuentra el tipo especifico de la funcion en formato type1xtype2 --> retorno
    
    public String BuscarTipoFuncion(String n) {
        int j = 0;
        Fila valEncontrado = new Fila() ;
     
         for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.equals(listaSimbolo.get(i).getNombreVar())) {
                valEncontrado = listaSimbolo.get(i);
                
                break;
            }
            j++;
        }
           if (j == (listaSimbolo.size())) {
            return "error_tipo";
        }

        
        return  valEncontrado.getTypeFuncion().getTipo();

    }
  
    
    
    
    
    /// devuelve la funcion a partir del nombre en caso de que este sea una funcion
    public Funcion esFuncion(String n) {
        Fila valEncontrado = new Fila() ;
     
        int j = 0;
        
        
        for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.equals(listaSimbolo.get(i).getNombreVar())) {
                valEncontrado = listaSimbolo.get(i);
                                if (!(valEncontrado.getType().contains("Record"))) {
                    return  valEncontrado.getTypeFuncion();
                }
                break;
            }
            j++;
        }
        
        
    

        return  valEncontrado.getTypeFuncion() ;

    }
    
    
    public Record esRecord(String n) {
        
        Fila valEncontrado = new Fila() ;
     
        int j = 0;
        
         System.out.println("aquie voy a tronar ");
       
        
        for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.equals(listaSimbolo.get(i).getNombreVar()) ) {
               
                
                
                
                valEncontrado = listaSimbolo.get(i);
                
                System.out.println("por aquie andamos " + valEncontrado.getNombreVar());
                
                if (valEncontrado.getTypeRecord().getTipo().contains("Record") && (valEncontrado.Ambito).equals("P.S")) {
                   
                    return  valEncontrado.getTypeRecord();
                }
                
            }
            j++;
        }
        return  valEncontrado.getTypeRecord() ;

    }
    
    
    public String BuscarTipoRecord(String n) {
        int j = 0;
        Fila valEncontrado = new Fila() ;
     
         for (int i = 0; i < listaSimbolo.size(); i++) {
            if (n.equals(listaSimbolo.get(i).getNombreVar())) {
                valEncontrado = listaSimbolo.get(i);
                if (valEncontrado.getType().contains("Record")) {
                    return  valEncontrado.getTypeRecord().getTipo();
                }
                
                break;
            }
            j++;
        }
           if (j == (listaSimbolo.size())) {
            return "error_tipo";
        }

        
        return  valEncontrado.getTypeFuncion().getTipo();

    }
    
    
    
     public Fila BuscarFila(String n, String amb) {
        int j = 0;
        Fila valEncontrado = new Fila() ;
     
         for (int i = 0; i < listaSimbolo.size(); i++) {
            if ((n.equals(listaSimbolo.get(i).getNombreVar())) && (amb.contains(listaSimbolo.get(i).getAmbito()))) {
                valEncontrado = listaSimbolo.get(i);
            
                    return  valEncontrado;
                
         
            }
            j++;
        }
           

        
        return  valEncontrado;

    }
    
    
}
