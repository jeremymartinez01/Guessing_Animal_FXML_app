/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.grupo_04;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class VentanaRandomController implements Initializable {

    @FXML
    private TextField lblMaximo;
    @FXML
    private Button lblPartida;

    public static int nMaximo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void iniciarPartida(MouseEvent event) throws IOException {

        ArrayList<String> preguntas = FileChooserController.preguntas;
        //nMaximo = Integer.parseInt(lblMaximo.getText());
        try {
            nMaximo = Integer.parseInt(lblMaximo.getText());
            if (nMaximo > preguntas.size()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El numero que ingreso esta fuera del limite.\nPuede ingresar un numero hasta " + preguntas.size());
                alert.show();
            } else if (nMaximo < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "El numero que ingreso no puede ser negativo.\nPuede ingresar un numero hasta " + preguntas.size());
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Por favor, piense en un animal");
                alert.show();
                App.setRoot("PreguntasYRespuestas");
            }

        } catch (NumberFormatException e) {
              Alert alert = new Alert(Alert.AlertType.ERROR, "Ingrese un número.\nPuede ingresar un numero hasta " + preguntas.size());
              alert.show();
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            Logger.getLogger(VentanaRandomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
