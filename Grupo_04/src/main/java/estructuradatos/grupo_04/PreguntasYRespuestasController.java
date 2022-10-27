/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package estructuradatos.grupo_04;

import Data.ArbolData;
import TDAs.BinaryTree;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeremy
 */
public class PreguntasYRespuestasController implements Initializable {

    @FXML
    private TextArea preguntas;
    private BinaryTree<String> arbol = PrimaryController.arbolFinal;
    private int PreguntasHechas = 0;
    private int PreguntasUsuario = VentanaRandomController.nMaximo;
    @FXML
    private Button RespuestaSi;
    @FXML
    private Button RespuestaNo;
    @FXML
    private Button btn_nose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (PreguntasHechas != PreguntasUsuario) {
            preguntar();
        } else {
            darRespuesta(this.arbol);
            disableButtons();
        }

    }

    @FXML
    private void RespuestaSi(ActionEvent event) {
        respuestaSi();
        if (PreguntasHechas != PreguntasUsuario) {
            preguntar();
        } else {
            darRespuesta(this.arbol);
            disableButtons();
        }
    }

    @FXML
    private void RespuestaNo(ActionEvent event) {
        respuestaNo();
        if (PreguntasHechas != PreguntasUsuario) {
            preguntar();
        } else {
            darRespuesta(this.arbol);
            disableButtons();
        }
    }

    @FXML
    private void RegresarPestania(ActionEvent event) {
        this.PreguntasHechas = 0;
        this.arbol = null;
        try {
            App.setRoot("VentanaRandom");
        } catch (IOException ex) {
            Logger.getLogger(PreguntasYRespuestasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void respuestaSi() {
        PreguntasHechas++;
        this.arbol = this.arbol.getLeft();
    }

    private void respuestaNo() {
        PreguntasHechas++;
        this.arbol = this.arbol.getRight();
    }

    private void preguntar() {
        if (ArbolData.esPregunta(this.arbol.getRootContent())) {
            preguntas.setText(this.arbol.getRootContent());
        }
    }

    private void darRespuesta(BinaryTree<String> guia) {
        if (guia == null) {
            preguntas.setText("Lo sentimos, pero no tenemos un animal que cumpla esa descripción");
        } else if (!ArbolData.esPregunta(guia.getRootContent())) {
            preguntas.setText("Estas pensando en un " + arbol.getRootContent());
        } else if (ArbolData.esPregunta(guia.getRootContent())) {
            String respuesta = "";
            if(ArbolData.getRespuestas(arbol).isEmpty()){
                preguntas.setText("Lo sentimos, pero no tenemos un animal que cumpla esa descripción");
            }else if(ArbolData.getRespuestas(arbol).size()==1){
                preguntas.setText("Estas pensando en un " + ArbolData.getRespuestas(arbol).getFirst());
            }else{
                for (String respuestas : ArbolData.getRespuestas(arbol)) {
                    respuesta += respuestas + ", ";
                }
                preguntas.setText("Podrias estar pensando en estos animales: " + respuesta.substring(0, respuesta.length()-2));
            }
        }
    }

    @FXML
    private void RespuestaNoSabe(ActionEvent event) {
        darRespuesta(this.arbol);
        disableButtons();
    }
    
    private void disableButtons(){
        btn_nose.setVisible(false);
        btn_nose.setDisable(true);
        RespuestaNo.setVisible(false);
        RespuestaNo.setDisable(true);
        RespuestaSi.setVisible(false);
        RespuestaSi.setDisable(true);
    }

}


