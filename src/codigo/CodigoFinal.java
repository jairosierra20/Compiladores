/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.util.ArrayList;

/**
 *
 * @author Jairo Sierra
 */
public class CodigoFinal {

    ArrayList< String> lineas = new ArrayList();
    ArrayList< String> lineas2 = new ArrayList();
    ArrayList< String> list_globales = new ArrayList();
    ArrayList< String> list_locales = new ArrayList();
    ArrayList< String> funciones = new ArrayList();
      ArrayList<Integer> parametrosF = new ArrayList();
    int [] registrosA = new int[4];
    ArrayList< String> list_messages = new ArrayList();
    ArrayList<String> enAmbitoActual= new ArrayList();
    int posicionActual = 0;
    Cuadruplo cuadruplos;
    int offSu = 0;
    int offM = 0;
    int offF = 0;
    Tabla tabla;
    String salto2 = "";
    String salto = "";
    String salto3 = "";
    int j = 3;
    int k = 0;
    int flagFuncion = 0;
     int parametros = 0;
    int flag = 0;
    String funcionA = "";
    int flag2 = 0;
    int ciclo = 0;
    int flag3 = 0;
    int hola = 0;
    int HayFuncion = 0;
    // Manejo de Offsets
    int localoffsetvar = 0;
    int current_sp = 0;

    public CodigoFinal(Cuadruplo cuadruplos, Tabla tabla) {
        this.cuadruplos = cuadruplos;
        this.tabla = tabla;
    }

    public CodigoFinal() {
    }

    public Cuadruplo getCuadruplos() {
        return cuadruplos;
    }

    public void setCuadruplos(Cuadruplo cuadruplos) {
        this.cuadruplos = cuadruplos;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    private void generarHeader() {
        // Crear todo lo que van en el header, variables globales y mensajes
        lineas.add("    .data");
        lineas.add("         ");
        GenerarOffset();
        GenVariableLists();
        
        

        // Create Global Messages
        String valor = "";
        int messages = 1;
        for (int i = 0; i < cuadruplos.getIntermedio().size(); i++) {
            if (cuadruplos.getIntermedio().get(i).getValor().contains("print") && cuadruplos.getIntermedio().get(i).getValor().contains("\"")) {
                valor = cuadruplos.getIntermedio().get(i).getValor().replace("print", "");
                valor = valor.replaceAll("\\(", "").replaceAll("\\)", "");
                lineas.add("_mesg" + messages + ":   .asciiz   " + valor);
                list_messages.add("_mesg" + messages + "," + valor);
                messages++;
            }
        }

        lineas.add(".text");
        lineas.add(".globl main");
        // TODO > Booleanos
        //        lineas.add("_true:    .asciiz");
        //        lineas.add("_false:     .asciiz");
        //        lineas.add("bufferboolean:      .space8");
        lineas.add("");

    }

    public void generarBody() {
        // Create main, structs, aritmeticos, ifs y demas
        lineas.add("            ");
                lineas.add("main:");
                lineas.add("move $fp,$sp");
                lineas.add("            ");
        localoffsetvar = 4;
        Boolean ismain = false;

        for (int i = 0; i < cuadruplos.getIntermedio().size(); i++) {
            if (cuadruplos.getIntermedio().get(i).getValor().contains("etqMAIN")) {
                ismain = true;
            }
            
            
            
            if (ismain == true){
                // Hacer todo lo del Main
                
                if (cuadruplos.getIntermedio().get(i).getValor().contains("etqMAIN")) {
                    addStackSpace(i, "etqMAIN");
                }
                if ((cuadruplos.getIntermedio().get(i).getValor().contains("print"))) {
                    createPrints(i);
                    
                    ////para los aritmeticos
                }
                if ((cuadruplos.getIntermedio().get(i).getValor().contains("Read"))) {
                    createReads(i);
                    
                    ////para los aritmeticos
                }else if((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("Param"))){
                    int regCont = 0;
                    if (regCont == 3) {
                            regCont = 0;
                        
                    }
                        String var = cuadruplos.getIntermedio().get(i).lineaCodigo().replace("Param", "");
                        parametros++;
                        
                         if((var.trim()).matches("[0-9]+")){
                                 lineas.add("li $a0," + var.trim());
                             }else{
                  lineas.add("lw $a"+regCont+"," + ConvertirOFsset(var.trim()));}
                         
                      
                        regCont++;
                
                } else if ((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("Call"))){
                String var = cuadruplos.getIntermedio().get(i).lineaCodigo().split(",")[0];
                var = var.replaceAll("Call", "");
                 lineas.add("jal " + var);
                funcionA = var;
                
                
                parametrosF.add(parametros);
                funciones.add(var);
                
                
                parametros=0;
              
                  
                 
                }else if ((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("= ret"))){
                String var = cuadruplos.getIntermedio().get(i).lineaCodigo().split("=")[0];
                var = var.replaceAll("Call", "");
                lineas.add("sw $v0,"+ ConvertirOFsset(var));
       
                
                }
                else if (((cuadruplos.getIntermedio().get(i).getValor().contains("*")) || (cuadruplos.getIntermedio().get(i).getValor().contains("+") || (cuadruplos.getIntermedio().get(i).getValor().contains("-"))) || (cuadruplos.getIntermedio().get(i).getValor().contains("/")))) {
                    if ((cuadruplos.getIntermedio().get(i).getNombre().matches("t[0-9]+"))) {

                        Aritmeticos(i);
                        lineas.add("move $t3,$t2");

                    }

                } else if ((cuadruplos.getIntermedio().get(i).getValor().matches("t[0-9]+"))) {
                    lineas.add("sw $t3," + ConvertirOFsset(cuadruplos.getIntermedio().get(i).getNombre()));

                } else if ((cuadruplos.getIntermedio().get(i).getValor().matches("[0-9]+"))) {
                    lineas.add("li $t0," + cuadruplos.getIntermedio().get(i).getValor());
                    lineas.add("sw $t0," + ConvertirOFsset(cuadruplos.getIntermedio().get(i).getNombre()));
                ////acabamos con los aritmeticos
                } else {
                    Ciclos(i);
                }
            } 

            if (ismain == false){
                
              
            }
            if (cuadruplos.getIntermedio().get(i).getValor().contains("endMAIN")) {
                ismain = false;
                localoffsetvar = 0;
            } 
        }

        lineas.add("li $v0, 10");
        lineas.add("syscall");
        
        if (!funciones.isEmpty()) {
           
            for (int i = 0; i < funciones.size(); i++) {
                posicionActual = i;
                flagFuncion = 1;
                 for (int l = 0; l < cuadruplos.getIntermedio().size(); l++) {
                        if (cuadruplos.getIntermedio().get(l).lineaCodigo().contains(funciones.get(i).trim())) {
                            Funcion(l,i);
                            break;
                        }
                        
                    }
                 
                 
                 
                 
                 lineas.add("move $sp,"+ "$fp" );
                 lineas.add("lw $fp,"+ "-4($sp)" );
                 lineas.add("lw $ra,"+ "-8($fp)" );
                 int tempR = -8; 
               for (int q = 0; q < parametrosF.get(i); q++) {
                    tempR = tempR-4;
                    lineas.add("lw $s" + q  + "," + tempR +"($sp)" );
                }
               
                lineas.add("jr $ra" );
                
            }
  
        }
         

    }
    
    
    public void Funcion(int l,int i){
        System.out.println("nememes "+ i);
        enAmbitoActual  = GenerarAmbito("P.F", "."+(i+1));
        int cont = 0;
         if (cuadruplos.getIntermedio().get(l).lineaCodigo().contains("etq")) {
                    
                lineas.add(cuadruplos.getIntermedio().get(l).lineaCodigo().replace("etq", ""));
                int tempR = -8;
                lineas.add("sw $fp, -4($sp)" );
                lineas.add("sw $ra, -8($sp)" );
                
                for (int q = 0; q < parametrosF.get(i); q++) {
                    tempR = tempR-4;
                    lineas.add("sw $s" + q  + "," + tempR +"($sp)" );
                }
                
                
                lineas.add("move $fp ,$sp");
                System.out.println("locales: " + enAmbitoActual.size() +    "variabasd: " +parametrosF.get(i) );
                lineas.add("sub $sp,$sp,"+ (((enAmbitoActual.size()-parametrosF.get(i))*4)+(tempR*-1)));
                
                
                 for (int q = 0; q < parametrosF.get(i); q++) {
                    
                    lineas.add("move $a" + q  + "," + "$s" +q);
                }
                
                cont = cont + l;
                
                
                while(!cuadruplos.getIntermedio().get(cont).lineaCodigo().contains("end")){
                    System.out.println(cuadruplos.getIntermedio().get(cont).lineaCodigo());
                    CuerpoFunciones(cont);
                    cont++;
                
                }
                
                
                
                
                
                    
                }
         
         
    
    }
    
    
      public void CuerpoFunciones(int i){
        
        if ((cuadruplos.getIntermedio().get(i).getValor().contains("print"))) {
                    createPrints(i);
                    
                    ////para los aritmeticos
                }
                if ((cuadruplos.getIntermedio().get(i).getValor().contains("Read"))) {
                    createReads(i);
                    
                    ////para los aritmeticos
                }else if((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("Param"))){
                    int regCont = 0;
                    if (regCont == 3) {
                            regCont = 0;
                        
                    }
                        String var = cuadruplos.getIntermedio().get(i).lineaCodigo().replace("Param", "");
                        parametros++;
                        
                        
                           if(var.matches("[0-9]+")){
                lineas.add("li $a"+regCont+"," + ConvertirOFsset(var.trim()));
                             }else{
                lineas.add("lw $a"+regCont+"," + ConvertirOFsset(var.trim()));
                           }
                        
                        
                        regCont++;
                
                } else if ((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("Call"))){
                String var = cuadruplos.getIntermedio().get(i).lineaCodigo().split(",")[0];
                var = var.replaceAll("Call", "");
                 lineas.add("jal " + var);
                funcionA = var;
                
                
                parametrosF.add(parametros);
                funciones.add(var);
                
                
                parametros=0;
              
                  
                 
                }else if ((cuadruplos.getIntermedio().get(i).lineaCodigo().contains("= ret"))){
                String var = cuadruplos.getIntermedio().get(i).lineaCodigo().split("=")[0];
                var = var.replaceAll("Call", "");
                lineas.add("sw $v0,"+ ConvertirOFsset(var));
       
                
                }
                else if (((cuadruplos.getIntermedio().get(i).getValor().contains("*")) || (cuadruplos.getIntermedio().get(i).getValor().contains("+") || (cuadruplos.getIntermedio().get(i).getValor().contains("-"))) || (cuadruplos.getIntermedio().get(i).getValor().contains("/")))) {
                    if ((cuadruplos.getIntermedio().get(i).getNombre().matches("t[0-9]+"))) {

                        Aritmeticos(i);
                        lineas.add("move $t3,$t2");

                    }

                }else if((cuadruplos.getIntermedio().get(i).getValor().contains("Return"))){
                     String var =cuadruplos.getIntermedio().get(i).getValor();
                             var= var.replaceAll("Return", "");
                             if(var.matches("[0-9]+")){
                                 lineas.add("li $v0," + var.trim());
                             }else{
                 lineas.add("lw $v0," + ConvertirOFsset(var.trim()));
                             }
                
                } else if ((cuadruplos.getIntermedio().get(i).getValor().matches("t[0-9]+"))) {
                    lineas.add("sw $t3," + ConvertirOFsset(cuadruplos.getIntermedio().get(i).getNombre()));

                } else if ((cuadruplos.getIntermedio().get(i).getValor().matches("[0-9]+"))) {
                    lineas.add("li $t0," + cuadruplos.getIntermedio().get(i).getValor());
                    lineas.add("sw $t0," + ConvertirOFsset(cuadruplos.getIntermedio().get(i).getNombre()));
                ////acabamos con los aritmeticos
                }else {
                    Ciclos(i);
                }
            

          
    
    
    }
    
    
    
    
  
    
    
    public void crearParamatros(){
    
    
    
    
    }

    public void addStackSpace(int cuad_row, String function_name) {
        ArrayList<String> varible_lists = getFunctionsVariables(cuad_row, function_name);
        int current_offset = 0;
        if (varible_lists.size() > 0) {
            System.out.println("valor de la lista ");
            current_offset = varible_lists.size() * 4;
        }
        lineas.add(" sub $sp, $sp, " + (current_offset + 8));
        lineas.add("");
        current_sp = (current_offset + 8);
    }

    public ArrayList<String> getFunctionsVariables(int cuad_row, String function_name) {
        String function = function_name.replace("etq", "").replace(":", "");
        int index = cuad_row;
        Boolean exists = false;
        ArrayList<String> variable_lists = new ArrayList();
        while (!(cuadruplos.intermedio.get(index).lineaCodigo().contains("end" + function))) {
            if (cuadruplos.intermedio.get(index).lineaCodigo().contains("=") &&  !(cuadruplos.intermedio.get(index).getNombre().matches("t[0-9]+"))) {
                for (int i = 0; i < variable_lists.size(); i++) {
                    if (cuadruplos.intermedio.get(index).lineaCodigo().split("=")[0].trim().equals(variable_lists.get(i))) {
                        exists = true;
                    }
                }
                if (exists == false) {
                    variable_lists.add(cuadruplos.intermedio.get(index).lineaCodigo().split("=")[0].trim());
                  
                } else {
                    exists = false;
                }
            }
            index++;
        }
        
        enAmbitoActual = variable_lists;
        return variable_lists;
    }

    public void GenVariableLists() {
        for (int i = 0; i < tabla.getListaSimbolo().size(); i++) {
            if (tabla.getListaSimbolo().get(i).getAmbito().equals("P") && tabla.getListaSimbolo().get(i).getType().equals("Integer")) {
                // GLOBAL VARIABLES
                lineas.add(tabla.getListaSimbolo().get(i).getNombreVar() + ":  .word 0");
                list_globales.add(tabla.getListaSimbolo().get(i).getNombreVar() + ",Integer");
            } else if (tabla.getListaSimbolo().get(i).getAmbito().equals("P") && tabla.getListaSimbolo().get(i).getType().equals("Boolean")) {
                lineas.add(tabla.getListaSimbolo().get(i).getNombreVar() + ":  .byte 0");
                list_globales.add(tabla.getListaSimbolo().get(i).getNombreVar() + ",Boolean");
            } else {
                // LOCAL VARIABLES
                list_locales.add(
                        tabla.getListaSimbolo().get(i).getNombreVar() + "," + tabla.getListaSimbolo().get(i).getOffset() + "," + tabla.getListaSimbolo().get(i).getAmbito());
            }
        }
    }

    public void createReads(int i){
        lineas.add(" ");
        lineas.add("li $v0, 5");
        lineas.add("syscall");
        String current_quad = cuadruplos.intermedio.get(i).getNombre();
        lineas.add("sw "+"$v0,"+ConvertirOFsset(current_quad));
         lineas.add(" ");
        
    }
    public void createPrints(int i) {
        lineas.add(" ");
        String current_quad = cuadruplos.intermedio.get(i).lineaCodigo();
        current_quad = current_quad.replace("print", "");
        current_quad = current_quad.replaceAll("\\(", "").replaceAll("\\)", "");
        // Begin quad validation base on the parameter sent.
        if (current_quad.contains("\"")) {
            for (int j = 0; j < list_messages.size(); j++) {
                if (list_messages.get(j).contains(current_quad)) {
                    String current_message = list_messages.get(j).split(",")[0];
                    lineas.add("li $v0, 4");
                    lineas.add("la $a0, " + current_message);
                    lineas.add("syscall");
                }
            }
        } else {
            // loop over the local variables
            for (int j = 0; j < list_locales.size(); j++) {
                if (current_quad.equals(list_locales.get(j).split(",")[0])) {
                    lineas.add("li $v0, 1");
                    // TODO ver lógica de offset para variables locales.
                    lineas.add("lw $a0," + ConvertirOFsset(list_locales.get(j).split(",")[0]) /*+ "($fp)"*/);
                    lineas.add("syscall");
                    localoffsetvar += 4;
                }
            }

            for (int j = 0; j < list_globales.size(); j++) {
                if (current_quad.equals(list_globales.get(j).split(",")[0]) && list_globales.get(j).split(",")[1].equals("Integer")) {
                    lineas.add("li $v0, 1");
                    // TODO > Ver si le vamos a poner a las variables globales _
                    lineas.add("lw $a0, " + list_globales.get(j).split(",")[0]);
                    lineas.add("syscall");
                }
            }
        }
        lineas.add(" ");
    }

    public Boolean isLocal(String nombre_variable) {
        for (int i = 0; i < list_locales.size(); i++) {
            if (list_locales.get(i).split(",")[0].equals(nombre_variable)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isGlobal(String nombre_variable) {
        for (int i = 0; i < list_globales.size(); i++) {
            if (list_globales.get(i).split(",")[0].equals(nombre_variable)) {
                return true;
            }
        }
        return false;
    }

public void Ciclos(int i) {
        String dato1 = "";
        String dato2 = "";
        String todo = "";

        todo = cuadruplos.intermedio.get(i).lineaCodigo();

        if (cuadruplos.intermedio.get(i).lineaCodigo().contains("if")) {
            if (cuadruplos.intermedio.get(i).lineaCodigo().contains("<>")) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }

                todo = cuadruplos.intermedio.get(i).lineaCodigo();
                dato1 = todo.substring(2, todo.indexOf("<>"));
                dato2 = todo.substring(todo.indexOf("<>") + 2, todo.indexOf("goto"));
                salto = todo.substring(todo.indexOf("goto") + 5, todo.length());
                todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                todo = cuadruplos.intermedio.get(i).lineaCodigo();

                lineas.add("lw $t0," + ConvertirOFsset(dato1));
                if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                lineas.add("bne,$t0,$t1,etq" + salto);
                if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }
                flag = 1;
            }

            else if (cuadruplos.intermedio.get(i).lineaCodigo().contains(">=")) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }

                todo = cuadruplos.intermedio.get(i).lineaCodigo();
                dato1 = todo.substring(2, todo.indexOf(">="));
                dato2 = todo.substring(todo.indexOf(">=") + 2, todo.indexOf("goto"));
                salto = todo.substring(todo.indexOf("goto") + 5, todo.length());
                todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                salto2 = todo.substring(todo.indexOf("GOTO") + 6, todo.length());
                todo = cuadruplos.intermedio.get(i).lineaCodigo();

                lineas.add("lw $t0," + ConvertirOFsset(dato1));
                if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                lineas.add("bge,$t0,$t1,etq" + salto);
                if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }

                flag = 1;
            } else if (cuadruplos.intermedio.get(i).lineaCodigo().contains("<=")) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }

                todo = cuadruplos.intermedio.get(i).lineaCodigo();
                dato1 = todo.substring(2, todo.indexOf("<="));
                dato2 = todo.substring(todo.indexOf("<=") + 2, todo.indexOf("goto"));
                salto = todo.substring(todo.indexOf("goto") + 5, todo.length());
                todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                todo = cuadruplos.intermedio.get(i).lineaCodigo();

                lineas.add("lw $t0," + ConvertirOFsset(dato1));
                if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                lineas.add("ble,$t0,$t1,etq" + salto);
                if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }
                flag = 1;
            } else if ((cuadruplos.intermedio.get(i).lineaCodigo().contains(">"))) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }
                //todo = cuadruplos.intermedio.get(i).lineaCodigo();
                // if dato > dato3 goto 3
                dato1 = todo.substring(2, todo.indexOf(">"));
                dato2 = todo.substring(todo.indexOf(">") + 2, todo.indexOf("goto"));
                salto = todo.substring(todo.indexOf("goto") + 5, todo.length());

                todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                todo = cuadruplos.intermedio.get(i).lineaCodigo();

                lineas.add("lw $t0," + ConvertirOFsset(dato1));
                if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                lineas.add("bgt,$t0,$t1,etq" + salto);
               if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }
                flag = 1;
            } else if (cuadruplos.intermedio.get(i).lineaCodigo().contains("<")) {
                if (cuadruplos.intermedio.get(i + 1).lineaCodigo().contains("GOTO")) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }
                
                    todo = cuadruplos.intermedio.get(i).lineaCodigo();
                    dato1 = todo.substring(2, todo.indexOf("<"));
                    dato2 = todo.substring(todo.indexOf("<") + 2, todo.indexOf("goto"));
                    salto = todo.substring(todo.indexOf("goto") + 5, todo.length());
                    todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                    salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                    todo = cuadruplos.intermedio.get(i).lineaCodigo();

                    lineas.add("lw $t0," + ConvertirOFsset(dato1));
                    if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                    lineas.add("blt,$t0,$t1,etq" + salto);
                    if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }

                    flag = 1;
                } else {
                    if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    hola = i;
                    flag3 = 1;
                }

                    todo = cuadruplos.intermedio.get(i).lineaCodigo();
                    dato1 = todo.substring(2, todo.indexOf("<"));
                    dato2 = todo.substring(todo.indexOf("<") + 2, todo.indexOf("goto"));
                    salto = todo.substring(todo.indexOf("goto") + 5, todo.length());

                    todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                    salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                    todo = cuadruplos.intermedio.get(i).lineaCodigo();

                    lineas.add("lw $t0," + ConvertirOFsset(dato1));
                    if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }

                    lineas.add("blt,$t1,$t0,etq" + salto);
                    if (cuadruplos.intermedio.get(i + 1).lineaCodigo().contains("GOTO")) {
                        if (salto2 != "") {
                            lineas.add("b etq" + salto2);
                        }
                        lineas.add("etq" + salto + ":");

                        flag = 1;
                    }
                }

            } else if (cuadruplos.intermedio.get(i).lineaCodigo().contains("=")) {
                if (flag3 == 0) {
                    lineas.add("etq" + i + ":");
                    flag3 = 1;
                }

                todo = cuadruplos.intermedio.get(i).lineaCodigo();
                dato1 = todo.substring(2, todo.indexOf("="));
                dato2 = todo.substring(todo.indexOf("=") + 2, todo.indexOf("goto"));
                salto = todo.substring(todo.indexOf("goto") + 5, todo.length());
                 
                todo = cuadruplos.intermedio.get(i + 1).lineaCodigo();
                salto2 = todo.substring(todo.indexOf("goto") + 6, todo.length());
                todo = cuadruplos.intermedio.get(i).lineaCodigo();

                lineas.add("lw $t0," + ConvertirOFsset(dato1));
                if ((dato2.trim()).matches("[0-9]+")) {
                        lineas.add("li $t1," + dato2);
                    } else {
                        lineas.add("lw $t1," + ConvertirOFsset(dato2));
                    }
                lineas.add("beq,$t0,$t1,etq" + salto);
                if(Integer.parseInt(salto.trim())==i+2){
                lineas.add("b etq" + salto2);
                lineas.add("etq" + salto + ":");
               }

                flag = 1;

            } 
        }

//        if(cuadruplos.intermedio.get(i).lineaCodigo().contains("GOTO")){
//            salto2= todo.substring(todo.indexOf("GOTO")+5, todo.length());
//        }
        if (salto2.contains(Integer.toString(i + 1))) {
            if (todo.contains("GOTO")) {
                lineas.add("b etq" + todo.substring(todo.indexOf("GOTO") + 5, todo.length()));
                flag2 = Integer.parseInt(todo.substring(todo.indexOf("GOTO") + 5, todo.length()));
            }
            //System.out.println("Perra maldita: "+lineas.get(i));
            lineas.add("etq" + salto2 + ":");
            
            
        }
        int r=0;
        for(int j=0;j<lineas2.size();j++){
               if(lineas2.get(j).trim().contains(("etq" + flag2 + ":").trim())){
                   r=1;
               }
           }
        if ((i) == flag2 ) {
           
           if(r==0){
           if(!(lineas.get(lineas.size()-1).equals("etq" + flag2 + ":"))){
            lineas.add("etq" + flag2 + ":");
            }
           }
            
        }
        //FOR
        if (salto != "") {
            if (Integer.parseInt(salto) == i + 1) {
                if (hola != 0) {
                    lineas.add("b etq" + hola);
                    lineas.add("etq" + salto + ":");
                }
            }
        }
        lineas2=lineas;

    }
    
    public String ConvertirOFsset(String vari){
        String offeset = vari;
        int j = 0;
        if (flagFuncion == 1) {
            j =   parametrosF.get(posicionActual)-1;
            
        }
        if (isLocal(vari.trim())) {
            for (int i = 0; i < enAmbitoActual.size(); i++) {
            
            if ((enAmbitoActual.get(i).trim()).equals(vari.trim())) {
                offeset = "-"+ (j+1)*4 + "($fp)";
                return offeset;
            }
            
            j++;
            
        }
            
        }
        
            return vari;
          
        }
    
    
    
     public String ParametrosOFsset(String vari){
        String offeset = vari;
        int j = 0;
      
        
        
        if (isLocal(vari.trim())) {
            for (int i = 0; i < enAmbitoActual.size(); i++) {
            
            if ((enAmbitoActual.get(i).trim()).equals(vari.trim())) {
                offeset = "$s"+ (i);
                return offeset;
            }
            
            j++;
            
        }
            
        }
        
            return vari;
          
        }

  
    public void Aritmeticos(int i) {
        String numeros;
        String numeros1;
        String numeros2;

        if (cuadruplos.getIntermedio().get(i).getValor().contains("+")) {

            numeros = cuadruplos.getIntermedio().get(i).getValor();
            numeros1 = numeros.substring(0, numeros.indexOf("+")).trim();
            numeros2 = numeros.substring(numeros.indexOf("+"), numeros.length()).trim();
            numeros2 = numeros2.replaceAll("\\+", "");

            
            
            
            if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("li $t1," + numeros2);

            }else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("move $t0,$t3");
                lineas.add("move $t1,$t4");

            } else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                System.out.println("jasj");
                lineas.add("li $t0," + numeros1);
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+") && (numeros2.trim()).matches("[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros2.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("li $t1," + numeros2);

            }  else {
                //System.out.println("jasj4");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));
            }

            lineas.add("add $t2,$t1,$t0");

            //j=3;
            // lineas.add("sw $t2," + nombre);
        } else if (cuadruplos.getIntermedio().get(i).getValor().contains("-")) {

            numeros = cuadruplos.getIntermedio().get(i).getValor();
            numeros1 = numeros.substring(0, numeros.indexOf("-")).trim();
            numeros2 = numeros.substring(numeros.indexOf("-"), numeros.length()).trim();
            numeros2 = numeros2.replaceAll("\\-", "");

           
              if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("li $t1," + numeros2);

            }else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("move $t0,$t3");
                lineas.add("move $t1,$t4");

            } else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                System.out.println("jasj");
                lineas.add("li $t0," + numeros1);
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+") && (numeros2.trim()).matches("[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros2.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else {
                //System.out.println("jasj4");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));
            }


            lineas.add("sub $t2,$t0,$t1");

            //j = 3;
            //lineas.add("sw $t2," + nombre);
        } else if (cuadruplos.getIntermedio().get(i).getValor().contains("*")) {

            numeros = cuadruplos.getIntermedio().get(i).getValor();
            numeros1 = numeros.substring(0, numeros.indexOf("*")).trim();
            numeros2 = numeros.substring(numeros.indexOf("*"), numeros.length()).trim();
            numeros2 = numeros2.replaceAll("\\*", "");

           
              if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("li $t1," + numeros2);

            }else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("move $t0,$t3");
                lineas.add("move $t1,$t4");

            } else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                System.out.println("jasj");
                lineas.add("li $t0," + numeros1);
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+") && (numeros2.trim()).matches("[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros2.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else {
                //System.out.println("jasj4");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));
            }

            // lineas.add("lw $t0," + numeros1);
            lineas.add("mul $t2,$t1,$t0");

            //lineas.add("sw $t2," + nombre);
        } else if (cuadruplos.getIntermedio().get(i).getValor().contains("/")) {

            numeros = cuadruplos.getIntermedio().get(i).getValor();
            numeros1 = numeros.substring(0, numeros.indexOf("/")).trim();
            numeros2 = numeros.substring(numeros.indexOf("/"), numeros.length()).trim();
            numeros2 = numeros2.replaceAll("\\/", "");

              if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("li $t1," + numeros2);

            }else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("move $t0,$t3");
                lineas.add("move $t1,$t4");

            } else if ((numeros2.trim()).matches("t[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                System.out.println("jasj");
                lineas.add("li $t0," + numeros1);
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+") && (numeros2.trim()).matches("[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("t[0-9]+")) {
                System.out.println("jasj");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("move $t1,$t3");

            } else if ((numeros1.trim()).matches("t[0-9]+")) {
                System.out.println("jasj2");
                lineas.add("move $t0,$t3");
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros1.trim()).matches("[0-9]+")) {
                lineas.add("li $t0," + numeros1);
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));

            } else if ((numeros2.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("li $t1," + numeros2);

            } else if ((numeros2.trim()).matches("[0-9]+") && (numeros1.trim()).matches("[0-9]+")) {
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset( numeros2));

            } else {
                //System.out.println("jasj4");
                lineas.add("lw $t0," + ConvertirOFsset(numeros1));
                lineas.add("lw $t1," + ConvertirOFsset(numeros2));
            }

            lineas.add("div $t2,$t0,$t1");

        } else if (!(cuadruplos.getIntermedio().get(i).getNombre().equals(""))) {
            if (i > 0) {
                if (!((cuadruplos.getIntermedio().get(i - 1).getValor().contains("*")) || (cuadruplos.getIntermedio().get(i - 1).getValor().contains("+") || (cuadruplos.getIntermedio().get(i - 1).getValor().contains("-"))) || (cuadruplos.getIntermedio().get(i - 1).getValor().contains("/")))) {
                    lineas.add("lw $t0," + cuadruplos.getIntermedio().get(i).getValor());
                    // lineas.add("sw $t0," + nombre);
                }

            } else {
                lineas.add("lw $t0," + cuadruplos.getIntermedio().get(i).getValor());
                // lineas.add("sw $t0," + nombre);
            }

        }

    }

    public void BuscarTemporales() {
    }

    public ArrayList<String> Generar() {
        generarHeader();
        generarBody();

        return lineas;

    }
    
    
    public ArrayList<String> GenerarAmbito(String Ambito, String ultimo) {
        ArrayList<String> todos = new ArrayList();

         
        for (int i = 0; i < tabla.getListaSimbolo().size(); i++) {
            if (tabla.getListaSimbolo().get(i).getAmbito().equals(Ambito+ultimo)) {
                todos.add(tabla.getListaSimbolo().get(i).getNombreVar());
            }
            
        }
        
    

        return todos;
        
}
    
      
    public String ConvertirOFsset2(String vari){
        String offeset = vari;
        if (isLocal(vari.trim())) {
            for (int i = 0; i < enAmbitoActual.size(); i++) {
            
            if ((enAmbitoActual.get(i).trim()).equals(vari.trim())) {
                offeset = "-"+ (i+1)*4 + "($fp)";
                return offeset;
            }
            
        }
            
        }
        
            return vari;
          
        }
    
    
    
    

    public void GenerarOffset() {
        for (int i = 0; i < tabla.getListaSimbolo().size(); i++) {
            if (tabla.getListaSimbolo().get(i).getAmbito().equals("P.M")) {
                offM = offM + 4;
                tabla.getListaSimbolo().get(i).setOffset(offM);
                //tabla.getListaSimbolo().get(i).setNombreVar("-"+offM+"($sp)");
            } else if (tabla.getListaSimbolo().get(i).getAmbito().equals("P.SU")) {
                offSu = offSu + 4;
                tabla.getListaSimbolo().get(i).setOffset(offSu);
                //tabla.getListaSimbolo().get(i).setNombreVar("-"+offSu+"($sp)");
            } else if (tabla.getListaSimbolo().get(i).getAmbito().equals("P.F")) {
                offF = offF + 4;
                tabla.getListaSimbolo().get(i).setOffset(offF);
                //tabla.getListaSimbolo().get(i).setNombreVar("-"+offF+"($sp)");
            }
        }
    }

}
