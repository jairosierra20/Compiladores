/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

public class Fila {
    String Type;
    String nombreVar;
    //int ambito=0;
    Funcion TypeFuncion;
    Record TypeRecords;
    String Ambito = "";
   int offset = 0;
    
    public Fila() {
       
    }

    public Fila(String Type, String nombreVar, String Ambito, int offset) {
        this.Type = Type;
        this.nombreVar = nombreVar;
        this.Ambito = Ambito;
       this.offset = offset;
      
    }

    public Fila(Funcion TypeFuncion, String nombreVar, String Ambito) {
        this.nombreVar = nombreVar;
        this.TypeFuncion = TypeFuncion;
        this.Type = TypeFuncion.getTipo();
        this.Ambito = Ambito;
        
    }
    public Fila(Record TypeRecords, String nombreVar, String Ambito) {
        this.nombreVar = nombreVar;
        this.TypeRecords = TypeRecords;
        this.Type = TypeRecords.getTipo();
        this.Ambito = Ambito;
        
    }
    
    

    public String getType() {
        return Type;
    }

    public String getNombreVar() {
        return nombreVar;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setNombreVar(String nombreVar) {
        this.nombreVar = nombreVar;
    }

    public Funcion getTypeFuncion() {
        return TypeFuncion;
    }

    public void setTypeFuncion(Funcion TypeFuncion) {
        this.TypeFuncion = TypeFuncion;
    }
    
    
    public Record getTypeRecord() {
        return TypeRecords;
    }

    public String getAmbito() {
        return Ambito;
    }

    public void setAmbito(String Ambito) {
        this.Ambito = Ambito;
    }
    
    
    
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    
    
    
    
    
    
    
  
    
    
    
    
    
    
}
