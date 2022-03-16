/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;



public class Principal {
    public static void main(String[] args) throws Exception {
        String ruta1 = "src/codigo/Lexer.flex";
        String ruta2 = "src/codigo/LexerCup.flex";
        String[] rutaS = {"-parser", "ArbolSemantico", separatorsToSystem("src/codigo/ArbolSemantico.cup")};
        String[] rutaS2 = {"-parser", "CupArbol", separatorsToSystem("src/codigo/CupArbol.cup")};
        String[] rutaS3 = {"-parser", "PrimeraPasada", separatorsToSystem("src/codigo/PrimeraPasada.cup")};

        generar(ruta1, ruta2, rutaS , rutaS2, rutaS3);
    }
    public static void generar( String ruta1, String ruta2, String[] rutaS , String[] rutaS2,String[] rutaS3) throws IOException, Exception{
        File archivo;

        
        archivo = new File(separatorsToSystem(ruta1));
        jflex.Main.generate(archivo);
        archivo = new File(separatorsToSystem(ruta2));
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS2);
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS3);
        
        Path rutaSym = Paths.get("src/codigo/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("sym.java"), 
                Paths.get("src/codigo/sym.java")
        );
        Path rutaSin = Paths.get("src/codigo/ArbolSemantico.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get("ArbolSemantico.java"), 
                Paths.get("src/codigo/ArbolSemantico.java")
        );
        
        Path rutaSin2 = Paths.get("src/codigo/CupArbol.java");
        if (Files.exists(rutaSin2)) {
            Files.delete(rutaSin2);
        }
        Files.move(
                Paths.get("CupArbol.java"), 
                Paths.get("src/codigo/CupArbol.java")
        );
        Path rutaSin3 = Paths.get("src/codigo/PrimeraPasada.java");
        if (Files.exists(rutaSin3)) {
            Files.delete(rutaSin3);
        }
        Files.move(
                Paths.get("PrimeraPasada.java"), 
                Paths.get("src/codigo/PrimeraPasada.java")
        );
    }

    public static String separatorsToSystem(String path)
    { 
        if (path == null) {
            return null;
        }
        if (System.getProperty("os.name").startsWith("Win")) {
            return FilenameUtils.separatorsToWindows(path);
        } else {
            return FilenameUtils.separatorsToUnix(path);
        }
    }
}



