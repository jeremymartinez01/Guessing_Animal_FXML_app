/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author ERWIN AURIA
 */
public class PreguntasData {
    
    
    public static ArrayList<String>leerPreguntas(String ruta) throws IOException {
        ArrayList<String> preguntas = new ArrayList<>();
        //Usamos la clase BufferedReader para leer archivos de texto
        try (InputStream input = new URL("file:" + ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))){
            String linea;
            //leemos linea a linea hasta llegar la final del archivo
            while( (linea=bf.readLine())!=null ){
                if(esPregunta(linea)){
                    preguntas.add(linea);
                }       
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } 
        return preguntas;
    }
    
    
    public static boolean esPregunta(String s){        
        return s.startsWith("¿") && s.endsWith("?");
    }
    
}
