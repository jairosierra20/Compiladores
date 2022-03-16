/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author renat
 */
public class Cuadruplo {
    String Simbolo;
    String Dato1;
    String Dato2;
    String Resultado;
      ArrayList<Tempnuevo> intermedio = new ArrayList();
      int cont = 0;
     

            
    
    

    public Cuadruplo(String Simbolo, String Dato1, String Dato2, String Resultado) {
        this.Simbolo = Simbolo;
        this.Dato1 = Dato1;
        this.Dato2 = Dato2;
        this.Resultado = Resultado;
    }

    public Cuadruplo() {
    }
    
    

    public String getSimbolo() {
        return Simbolo;
    }

    public void setSimbolo(String Simbolo) {
        this.Simbolo = Simbolo;
    }

    public String getDato1() {
        return Dato1;
    }

    public void setDato1(String Dato1) {
        this.Dato1 = Dato1;
    }

    public String getDato2() {
        return Dato2;
    }

    public void setDato2(String Dato2) {
        this.Dato2 = Dato2;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String Resultado) {
        this.Resultado = Resultado;
    }
    
    public Tempnuevo Operacion(String Simbolo, String num1, String num2, Tempnuevo Result){
     //   Tempnuevo temp = new Tempnuevo();
         
       
       Result.setValor( num1+ " " + Simbolo + " "+ num2 );
        
        
        intermedio.add( Result );
        
        
        return Result;
    }
    
    
    
     public Tempnuevo Igualdad(String Simbolo, String num1, Tempnuevo Result){
     //   Tempnuevo temp = new Tempnuevo();    
       
       Result.setValor( num1) ;
        
        
        intermedio.add( Result );
        
        
        return Result;
    }
     
     
     
     public String Igualdad(String Simbolo,  String Result, String num1 ){
   
      Tempnuevo temp = new Tempnuevo(1);

       temp.setNombre(num1);
       temp.setValor(Result);
       
       
        intermedio.add( temp );
        
        
        return Result;
    }
     public void Funciones(String Simbolo,  String Nombre){
         
   
      Tempnuevo temp = new Tempnuevo(1);

       
       temp.setValor(Simbolo+Nombre+":");
       
       
        intermedio.add( temp );
        
        
        return;
    }
     
     
     
     
     public Tempnuevo  Saltos(String Simbolo,  String Result ){

    Tempnuevo temp = new Tempnuevo(1);
           
           temp.setValor(Simbolo + " " + Result);
        //listaverdadera.add(temp.getValor());
          
        
        intermedio.add( temp );
      
        
        
        return temp;
    }
     
     
     public void  OtraCosa(String Simbolo,  String Result ){

    Tempnuevo temp = new Tempnuevo(1);
           
           temp.setValor(Simbolo + " " + Result);
        //listaverdadera.add(temp.getValor());
          
        
        intermedio.add( temp );
      
        
   
    }
     
     
       public Tempnuevo Booleanos(String operador,  String operador1 , String operador2,  String linea ){

           Tempnuevo temp = new Tempnuevo(1);
           
           temp.setValor( "if " + operador1 + " " + operador + " "+ operador2 + " goto " + linea);
          
        //listaverdadera.add(temp.getValor());
        
        intermedio.add( temp );
        
      
        return temp;
       
           
 
    }
       
       
        public void Completo(ArrayList<Integer> E,  String Cuad ){
//
//           Tempnuevo temp = new Tempnuevo(1);
//           
//           temp.setValor( "if" + operador1 + " " + operador + " "+ operador2 + " goto " + linea);
//        

                String nuevoValor = "";
                
                
              for (int i = 0; i < E.size(); i++) {
                  nuevoValor = intermedio.get(E.get(i)).getValor();
                 intermedio.get(E.get(i)).setValor(nuevoValor + Cuad);
            }
              
              
              
 
    }
        
        
         public ArrayList<Integer> Fusion(ArrayList<Integer> E,  ArrayList<Integer> D ){
//
//           Tempnuevo temp = new Tempnuevo(1);
//           
//           temp.setValor( "if" + operador1 + " " + operador + " "+ operador2 + " goto " + linea);
//        

                String nuevoValor = "";
              for (int i = 0; i < D.size(); i++) {
                  E.add(D.get(i));
                 
            }
        
        
       // E = ArrayUtils.addAll(E,D);
       
        return E;
       
         }
         
         
         
         
         public void Impres(String Simbolo){
   
      Tempnuevo temp = new Tempnuevo(1);

       temp.setNombre("");
       temp.setValor("print(" + Simbolo + ")");

        intermedio.add( temp );
       
    }
         
         
         
       
       
     
     
     
     public void impresion(){
     
            System.out.println("<----------------------------------------------------->");
         for (int i = 0; i < intermedio.size()  ; i++) {
             System.out.println( i + ":" + intermedio.get(i).lineaCodigo());
         }
     
     }
     
     

    public ArrayList<Tempnuevo> getIntermedio() {
        return intermedio;
    }
       
       
       
       
       
   
    
    
}
