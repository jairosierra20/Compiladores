/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;


public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    
      static String errores;
      CupArbol tabC = new CupArbol();
      Cuadruplo cuadruplos = new Cuadruplo();
      Tabla tabla_simbolos = new Tabla();
    
    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    CupArbol sintactico;
    
    private void analizarLexico() throws IOException{
        
        String expr = (String) txtArchivo.getText();
        Lexer lexer = new Lexer(new StringReader(expr));
        String resultado = "";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtLexico.setText(resultado);
                return;
            }
            switch (token) {
                case Comilla:
                    resultado += "<" + token +">\n";
                    break;
                case Cadena:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Integer:
                    resultado += "<" + token +">\n";
                    break;
                    
                case Boolean:
                    resultado += "<" + token +">\n";
                    break;
                case Double:
                    resultado += "<" + token +">\n";
                    break;
                case IF:
                    resultado += "<" + token +">\n";
                    break;
                case Else:
                   resultado += "<" + token +">\n";
                    break;
                case Do:
                    resultado += "<" + token +">\n";
                    break;
                case While:
                    resultado += "<" + token +">\n";
                    break;
                case For:
                    resultado += "<" + token +">\n";
                    break;
                case Igual:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case OPRestSuma:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Enter:
                    resultado += "  <Enter>" + "\n";
                    break;
                case OPMultDiv:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Dim:
                    resultado += "<" + token +">\n";
                    break;
                case OR:
                   resultado += "<" + token +">\n";
                    break;
                case And:
                    resultado += "<" + token +">\n";
                    break;
                case OperadorRelacional:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case AS:
                    resultado += "<" + token +">\n";
                    break;
                case Op_Booleano:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case parentesisE:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case parentesisC:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case llaveE:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case llaveC:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case corcheteE:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case corcheteC:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Main:
                    resultado += "<" + "Sub" +">\n";
                    resultado += "<" + "Main" +">\n";
                    break;
                case coma:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Identificador:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case Numero:
                    resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                case ERROR:
                    resultado +=  "El Simbolo: [ " + lexer.lexeme + " ] no esta definido, Error: linea: " + lexer.linea  + " columna: "+ lexer.columna +  "\n";
                    
                    break;
                    
                case Comentario:
                          resultado += "<" + token + ", " +" ' "+ lexer.lexeme + " ' " +">\n";
                    break;
                default:
                    resultado += "<" + token +">\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnArchivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArchivo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLexico = new javax.swing.JTextArea();
        btnAnalizarLexico = new javax.swing.JButton();
        btnLimpiarLexico = new javax.swing.JButton();
        btnLimpiarArchivo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSintactico = new javax.swing.JTextArea();
        btnAnalizarSintactico = new javax.swing.JButton();
        btnLimpiarSintactico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnArchivo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnArchivo.setText("Cargar archivo");
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        txtArchivo.setColumns(20);
        txtArchivo.setRows(5);
        jScrollPane1.setViewportView(txtArchivo);

        txtLexico.setEditable(false);
        txtLexico.setColumns(20);
        txtLexico.setRows(5);
        jScrollPane2.setViewportView(txtLexico);

        btnAnalizarLexico.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAnalizarLexico.setText("Analizar");
        btnAnalizarLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarLexicoActionPerformed(evt);
            }
        });

        btnLimpiarLexico.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLimpiarLexico.setText("Limpiar");
        btnLimpiarLexico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarLexicoActionPerformed(evt);
            }
        });

        btnLimpiarArchivo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLimpiarArchivo.setText("Limpiar");
        btnLimpiarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarArchivoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Analizador Lexico");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("Compiladores 2");

        jButton1.setText("Graficar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Codigo Final");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarArchivo)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAnalizarLexico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarLexico))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArchivo)
                    .addComponent(btnLimpiarArchivo)
                    .addComponent(btnAnalizarLexico)
                    .addComponent(btnLimpiarLexico))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton2)
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))))
        );

        txtSintactico.setEditable(false);
        txtSintactico.setColumns(20);
        txtSintactico.setRows(5);
        jScrollPane3.setViewportView(txtSintactico);

        btnAnalizarSintactico.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAnalizarSintactico.setText("Analizar");
        btnAnalizarSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarSintacticoActionPerformed(evt);
            }
        });

        btnLimpiarSintactico.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLimpiarSintactico.setText("Limpiar");
        btnLimpiarSintactico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSintacticoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Analizador Sintactico y Semantico");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(270, 270, 270))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnalizarSintactico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiarSintactico))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalizarSintactico)
                    .addComponent(btnLimpiarSintactico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        // TODO add your handling code here:
        try {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String CadenaBasic = new String(Files.readAllBytes(archivo.toPath()));
            txtArchivo.setText(CadenaBasic);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnLimpiarLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarLexicoActionPerformed
        // TODO add your handling code here:
        txtLexico.setText(null);
    }//GEN-LAST:event_btnLimpiarLexicoActionPerformed

    private void btnLimpiarSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSintacticoActionPerformed
        // TODO add your handling code here:
        txtSintactico.setText(null);
        errores = "";
    }//GEN-LAST:event_btnLimpiarSintacticoActionPerformed

    private void btnAnalizarLexicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarLexicoActionPerformed
        try {
            analizarLexico();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalizarLexicoActionPerformed

    private void btnAnalizarSintacticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarSintacticoActionPerformed
        // TODO add your handling code here:
        errores = "";
         //notificar_er("----------Analisis Comenzando----------");
        String CadenaBasic = txtArchivo.getText();
       
        ArbolSemantico s = new ArbolSemantico(new codigo.Lexico(new BufferedReader( new StringReader(CadenaBasic))));   
        
        PrimeraPasada s2 = new PrimeraPasada(new codigo.Lexico(new BufferedReader( new StringReader(CadenaBasic))));
        try {
           s2.parse();
           
          // s.tabla.setListaSimbolo(s2.tabla.getListaSimbolo());
            System.out.println(s2.tabla.listaSimbolo.get(0).getNombreVar());
            s.tabla=s2.tabla;
            tabC.tabla =  s2.tabla;
            s.parse();
            //txtSintactico.setText("Analisis Sintactico realizado correctamente");
           
     
           
             notificar_er("Analisis Sintactico realizado correctamente");
             
             if (errores.length() < 50) {
                
                  txtSintactico.setForeground(new Color(25, 111, 61));
                  txtSintactico.setText(errores);
                
            }else{
               
                 errores = errores.replace("Analisis Sintactico realizado correctamente", "");
                 txtSintactico.setForeground(Color.red);
                 txtSintactico.setText(errores);
             }
             
            
           
            
      
        } catch (Exception ex) {
            System.out.println(ex);
//             Symbol sym = s.getS();
//             txtSintactico.setText("Error . Linea: " + (sym.right ) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
//            txtSintactico.setForeground(Color.red);
            
        }
   
      
       
    }//GEN-LAST:event_btnAnalizarSintacticoActionPerformed

    private void btnLimpiarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarArchivoActionPerformed
        // TODO add your handling code here:
        txtArchivo.setText(null);
    }//GEN-LAST:event_btnLimpiarArchivoActionPerformed

    
     
     
     
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String CadenaBasic = txtArchivo.getText();
        CupArbol s = new CupArbol(new codigo.Lexico(new StringReader(CadenaBasic)));
        
      
        try {
            
            
            s.tabla = tabC.tabla;
            s.parse();
            
           Nodo raiz = s.padre;
          
           
           graficar(recorrido(raiz), "ProyectodeCompi");
           
           
                  
          
            
            System.out.println("bien hecho");
             cuadruplos = s.generaracion;
           tabla_simbolos=s.tabla;
            //s.generaracion.getIntermedio().add(e)
        } catch (Exception ex){
            

            
            
            System.out.println("Revise bien");
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        CodigoFinal codigo;
        codigo = new CodigoFinal(cuadruplos,tabla_simbolos);
        System.out.println("<----------------------------------------------------->");
        System.out.println("Cuadruplos Finales: ");
        for (int i = 0; i < codigo.getCuadruplos().intermedio.size(); i++) {
            System.out.println( i + ":" + codigo.getCuadruplos().intermedio.get(i).lineaCodigo());
        }
        
        System.out.println("<----------------------------------------------------->");
        System.out.println("Tabla de Simbolos: ");
        for (int i = 0; i < codigo.getTabla().getListaSimbolo().size(); i++) {
            System.out.println("Fila "+i+" Nombre: "+codigo.getTabla().getListaSimbolo().get(i).getNombreVar() + " Tipo: "+codigo.getTabla().getListaSimbolo().get(i).getType() + " Ambito: "+codigo.getTabla().getListaSimbolo().get(i).getAmbito());
        }
        
        
         System.out.println("<----------------------------------------------------->");
        
        
        codigo.Generar();
        
        for (int i = 0; i < codigo.lineas.size(); i++) {
            System.out.println("" + codigo.lineas.get(i));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public String recorrido(Nodo raiz){
        String Cuerpo = "";
        
        int i = 0;
        for (Nodo hijos : raiz.getHijos() ) {
            if (!(hijos.getNombre().equals("VACIO"))) {
                Cuerpo += "\"" + raiz.getNumNodo() + "." + raiz.getNombre() +  "=" + raiz.getValor() +"\"->\"" + hijos.getNumNodo() + "." + hijos.getNombre() +  "=" + hijos.getValor() + "\"\n";
                
                
                //System.out.println("recorrido" +  i +": " + Cuerpo);
                Cuerpo += recorrido(hijos);
                
                
            }else{
                System.out.println(raiz.getNombre()); 
            }
            
            i++;
            
        }
        
        
        return Cuerpo;
       
    }
    
    
    
    public void graficar(String cadena, String cad){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombre = cad;
        String archivo = nombre + ".dot";
        
        try{
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            
            pw.println(cadena);
            pw.println("\n}");
            
            fichero.close();
        }catch (Exception e) {
            //System.out.println(e );
        }
        
        try {
            //System.out.println("dot.exe -Tpng "+ nombre +".dot -o "+ "FrijolDeMierda.png");
            String cmd = "dot.exe -Tpng "+ nombre +".dot -o "+ "ArbolSintactico.png";
            
            Runtime.getRuntime().exec(cmd);
            System.out.println("ahi vamos");
        } catch (IOException ioe) {
            System.out.println(ioe );
        }
        
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
    
    
  
    public static void notificar_er(String cad){
        errores +=  cad+"\n";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarLexico;
    private javax.swing.JButton btnAnalizarSintactico;
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnLimpiarArchivo;
    private javax.swing.JButton btnLimpiarLexico;
    private javax.swing.JButton btnLimpiarSintactico;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtArchivo;
    private javax.swing.JTextArea txtLexico;
    private javax.swing.JTextArea txtSintactico;
    // End of variables declaration//GEN-END:variables
}
