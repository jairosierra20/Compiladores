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
public class Record {

    ArrayList<Fila> RecordVariables = new ArrayList();
    String tipo;
    
    int tam = 0;

    public Record() {
    }

    public Record(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Fila> getRecordVariables() {
        return RecordVariables;
    }

    public void setRecordVariables(ArrayList<Fila> RecordVariables) {
        this.RecordVariables = RecordVariables;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void obtenerVariables() {
        String variables = "";

        String variables2 = "";
        String variables3 = "";

        //Record((n x Integer) x (i x Integer) x (q x Boolean) x (nen x String))
        variables = tipo.substring(6, tipo.length());
        variables = variables + "@";

        //( nx Integer) x (i x Integer) x (q x Boolean) x (nen x String)  1
        variables = variables.replaceAll("\\(", "").replaceAll("\\)", "");
        variables = variables.replaceAll(" ", "&");

//        System.out.println("antez del relpal:" + variables);

        System.out.println("");
        //  Integer x i x Integer x q x Boolean x nen x @  1

//        System.out.println("el tipo esta asi:" + tipo);
//
//        System.out.println("la variable asi:" + variables);

        int i = 0;
        int z = 0;

        while (i < variables.length()) {

            if (variables.charAt(i) == '@') {

                System.out.println("jajaja");
                variables3 = variables.substring(0, i);
                

                RecordVariables.add(new Fila(variables3, variables2, "P.S", tam));
                
                

                break;

            }

            if (variables.charAt(i) == '&') {
                if (variables.charAt(i + 1) == 'x') {

                    z++;

                }
                if (z % 2 != 0) {
                    variables2 = variables.substring(0, i);

                    variables = variables.substring(i, variables.length());
                    variables = variables.replaceFirst("&x&", "");

                } else if ((z != 0) && (z % 2 == 0)) {
                    variables3 = variables.substring(0, (i));
                    variables = variables.substring(i, variables.length());
                    variables = variables.replaceFirst("&x&", "");

                }

            }

            if ((!variables2.equals("")) && (!variables3.equals(""))) {

                RecordVariables.add(new Fila(variables3, variables2, "P.S", tam));
                
               switch (variables3) {
                    case "Integer":
                        tam = tam + 4;
                        break;
                    case "Boolean":
                        tam = tam + 2;
                        break;
                    case "String":
                        tam = tam + 10;
                        break;
                    default:
                        break;
                } 

               
                variables2 = "";
                variables3 = "";
                i = -1;
            }

            // System.out.println("El funal del ciclo; " + variables.length() + " estado: " + variables + " en el paso " + i );
            i++;

        }

    }

    public String buscarVariable(String n) {
        String valido = "";
        int j = 0;

        for (int i = 0; i < RecordVariables.size(); i++) {

            if (n.equals(RecordVariables.get(i).getNombreVar())) {

                return RecordVariables.get(i).getType();

            }

            j++;

        }

        if (j == (RecordVariables.size())) {
            return "error_tipo";
        }

        return valido;
    }
    
    
    
    public int buscarOffset(String n) {
        int valido = 0;
        int j = 0;

        for (int i = 0; i < RecordVariables.size(); i++) {

            if (n.equals(RecordVariables.get(i).getNombreVar())) {

                return RecordVariables.get(i).getOffset();

            }

            j++;

        }

        if (j == (RecordVariables.size())) {
            return -1;
        }

        return 2;
    }
    
    
    public String error(){
        return "error_tipo";
    }

    public void impresion() {

        for (int i = 0; i < RecordVariables.size(); i++) {
            System.out.println("Nombre variable: " + RecordVariables.get(i).getNombreVar() + " Tipo: " + RecordVariables.get(i).getType()+ "offset" + RecordVariables.get(i).getOffset());

        }

    }

}
