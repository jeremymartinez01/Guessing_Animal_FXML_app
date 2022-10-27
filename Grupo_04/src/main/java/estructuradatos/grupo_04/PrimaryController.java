package estructuradatos.grupo_04;

import TDAs.BinaryTree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController implements Initializable {

    @FXML
    private Label lbPreguntastxt;
    @FXML
    private Label lbRespuestastxt;
    @FXML
    private Button btnCargarPreguntas;
    @FXML
    private Button primaryButton;
    
    public static BinaryTree<String> arbolFinal;
    public static boolean filePredeterminado=true;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Entre initialize");
        if(filePredeterminado){
            //System.out.println("bandera true");
            lbPreguntastxt.setText("");
            lbRespuestastxt.setText("");
            primaryButton.setVisible(false);
        }else{
            //System.out.println("bandera false");
            lbPreguntastxt.setText(FileChooserController.nomPreguntas);
            lbRespuestastxt.setText(FileChooserController.nomRespuestas);
            primaryButton.setVisible(true);
        }
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("VentanaPregunta");
    }

    @FXML
    private void fileChooserPreguntas(ActionEvent event) throws IOException {
        App.setRoot("FileChooser");
    }

}
