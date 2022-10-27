/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import TDAs.BinaryTree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Javier
 */
public class ArbolData {
    
    
    public static BinaryTree<String> enlazarArbolesPreguntas(ArrayList<String> orden){
        //INTENTO CON QUEUE
        //LLENO LA COLA
        ArrayList<String> reverse = new ArrayList<>(orden);     
        Collections.reverse(reverse);
        
        Queue<BinaryTree<String>> q = new ArrayDeque<>(); 
        
        for(String p:reverse){
            int i=(int)Math.pow(2, orden.indexOf(p));
            while(i>0){
                q.offer(new BinaryTree<>(p));
                i--;
            }
            //PARA EL ULTIMO INDICE
            if(orden.indexOf(p)== 0){
                q.offer(new BinaryTree<>(p));
            }
            //PARAR EN EL LA PRIMERA PREGUNTA (ULTIMA INVERTIDA)
            break;
        }
        int nv = orden.size()-1;
        int cont = q.size()/2;
        while(q.size()>1){
            //System.out.println("Entre while");
            if(cont!=0){
                BinaryTree<String> left = q.poll();
                //System.out.println("hijo izquierdo: "+left.getRootContent());
                BinaryTree<String> right = q.poll();
                //System.out.println("hijo derecho: "+right.getRootContent());
                BinaryTree<String> root= new BinaryTree<>(orden.get(nv-1));
                root.setLeft(left);
                root.setRight(right);
                q.offer(root);
                cont--;
            }else{
                nv--;
                cont = q.size()/2;
            }
        }  
        
        return q.poll();
    }
    
    
    //METODO PARA MOSTRAR ARBOL POR NIVEL
    public static Map<Integer,ArrayList<String>> arbolPorNivel(BinaryTree<String> arbol, ArrayList<String> orden,ArrayList<String> respuestas){
        Map<Integer,ArrayList<String>> tabla = new LinkedHashMap<>();
        Stack<BinaryTree<String>> s = new Stack<>();
        s.push(arbol);
        
        while (!s.isEmpty()) {
            BinaryTree<String> tree = s.pop();
            if (!tree.isEmpty()) {
                //traversal.add(tree.getRootContent());
                if(tabla.containsKey(orden.indexOf(tree.getRootContent()))){
                    tabla.get(orden.indexOf(tree.getRootContent())).add(tree.getRootContent());
                }else{
                    tabla.putIfAbsent(orden.indexOf(tree.getRootContent()), new ArrayList<String>());
                    tabla.get(orden.indexOf(tree.getRootContent())).add(tree.getRootContent());
                }
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                s.push(tree.getRight());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                s.push(tree.getLeft());
            }
        }
        tabla.put(orden.size(), respuestas);
        
        return tabla;
    }
    //metodo mostrar por nivel solo preguntas
    public static String arbolPorNivelPreguntasString(BinaryTree<String> arbolPreguntas, ArrayList<String> orden){
        String resp="";
        Map<Integer,ArrayList<String>> tabla=arbolPorNivel(arbolPreguntas,orden,null);
        
        for(int i=0;i<tabla.keySet().size()-1;i++){
            resp+="Nivel: "+String.valueOf(i)+" "+tabla.get(i)+" \n";
        }
        
        return resp;
    }
    
    public static String arbolPorNivelString(BinaryTree<String> arbolPreguntas, ArrayList<String> orden,ArrayList<String> respuestas){
        String resp="";
        Map<Integer,ArrayList<String>> tabla=arbolPorNivel(arbolPreguntas,orden,respuestas);
        
        for(int i=0;i<tabla.keySet().size()-1;i++){
            resp+="Nivel: "+String.valueOf(i)+" "+tabla.get(i)+" \n";
        }
        resp+="Nivel "+String.valueOf(tabla.keySet().size()-1)+" (respuestas): "+tabla.get(tabla.keySet().size()-1)+" \n";
        //System.out.println("tabla: "+tabla.keySet());
        //System.out.println("size: "+tabla.keySet().size());
        return resp;
    }
    
    public static BinaryTree<String> enlazarRespuestas(BinaryTree<String> preguntas,ArrayList<String> respuestas){
        BinaryTree<String> arbol=preguntas;
        BinaryTree<String> navegar=preguntas;
        for(String linea:respuestas){
            //System.out.println("linea: "+linea);
            navegar=preguntas;
            String[] split=linea.split(" ");
            String animal=split[0];
            for(int i=1;i<split.length-1;i++){
                //System.out.println(i);
                if(split[i].equals("SI")){
                    //System.out.println("navegar antes de si: "+navegar.getRootContent());
                    navegar=navegar.getLeft();
                    //System.out.println("navegar despues de si: "+navegar.getRootContent());
                }else{
                    //System.out.println("navegar antes de no: "+navegar.getRootContent());
                    navegar=navegar.getRight();
                    //System.out.println("navegar despues de no: "+navegar.getRootContent());
                }
            }
            if(split[split.length-1].equals("SI")){
                //System.out.println("Editando si: "+navegar.getRootContent()+" con: "+animal);
                navegar.setLeft(new BinaryTree<String>(animal));
            }else{
                //System.out.println("Editando no: "+navegar.getRootContent()+" con: "+animal);
                navegar.setRight(new BinaryTree<String>(animal));
            }     
        }
        
        return arbol;
    }
    
    public static boolean esPregunta(String s){        
        return s.startsWith("¿") && s.endsWith("?");
    }
    
    public static LinkedList<String> getRespuestas(BinaryTree<String> arbol){
        LinkedList<String> hojas = arbol.getHojas();
        LinkedList<String> respuestas= new LinkedList<>();
        for(String s: hojas){
            if(esPregunta(s)){
                continue;
            }
            respuestas.add(s);
        }
        return respuestas;
    }

}
