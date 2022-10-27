/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.grupo_04;

import static Data.ArbolData.*;
import static Data.PreguntasData.leerPreguntas;
import static Data.RespuestasData.leerRespuestas;
import TDAs.BinaryTree;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FileChooserController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Label lbMensajeError;
    private ImageView ivImagen;
    
    ///
    private File fileSeleccionado;
    private String rutaPreguntas;
    private String rutaRespuestas;
    @FXML
    private Label lbNumPreguntas;
    @FXML
    private Button btnCargarTexto;
    @FXML
    private Label lbDescripcion;
    @FXML
    private Button btnBuscarRespuestas;
    @FXML
    private Label lbNumResp;
    @FXML
    private Button btnVolver;
    
    public static ArrayList<String> preguntas;
    public static ArrayList<String> respuestas;
    public static String nomPreguntas;
    public static String nomRespuestas;
    @FXML
    private Label lbTextoNumResp;
    
    //public static BinaryTree<String> arbolFinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //root.autosize();
        
        lbMensajeError.setText("");
        lbTextoNumResp.setVisible(false);
        lbNumResp.setVisible(false);
        btnBuscarRespuestas.setVisible(false);
        btnCargarTexto.setVisible(false);
        // TODO
        btnBuscar.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Texto preguntas");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                   /* new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")*/
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
            );

            // Obtener la imagen seleccionada
            File txtFile = fileChooser.showOpenDialog(null);
            fileSeleccionado = txtFile;

            // Mostar la imagen
            if (txtFile != null) {
                //Image image = new Image("file:" + imgFile.getAbsolutePath());
                //ivImagen.setImage(image);
                
                rutaPreguntas = txtFile.getAbsolutePath();
                try {
                    preguntas=leerPreguntas(rutaPreguntas);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                lbNumPreguntas.setText(String.valueOf(preguntas.size())+" preguntas");
                BinaryTree<String> arbolPreguntas=enlazarArbolesPreguntas(preguntas);
                lbDescripcion.setText(arbolPorNivelPreguntasString(arbolPreguntas,preguntas));
                
                
                btnBuscarRespuestas.setVisible(true);
                
            }
        });
        
        btnBuscarRespuestas.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Texto respuestas");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                   /* new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")*/
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt")
            );

            // Obtener la imagen seleccionada
            File txtFile = fileChooser.showOpenDialog(null);
            fileSeleccionado = txtFile;

            // Mostar la imagen
            if (txtFile != null) {
                //Image image = new Image("file:" + imgFile.getAbsolutePath());
                //ivImagen.setImage(image);
                
                rutaRespuestas = txtFile.getAbsolutePath();
                try {
                    respuestas=leerRespuestas(rutaRespuestas);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                lbTextoNumResp.setVisible(true);
                lbNumResp.setVisible(true);
                lbNumResp.setText(String.valueOf(respuestas.size())+" respuestas");
                BinaryTree<String> arbolPreguntas=enlazarArbolesPreguntas(preguntas);
                //System.out.println(respuestas);
                lbDescripcion.setText(arbolPorNivelString(arbolPreguntas,preguntas,respuestas));
                btnCargarTexto.setVisible(true);
            }
        });
    }    


    @FXML
    private void volver(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void cargarTexto(ActionEvent event) throws IOException {
        BinaryTree<String> arbolPreguntas=enlazarArbolesPreguntas(preguntas);
        //lbDescripcion.setText(arbolPorNivelString(arbolPreguntas,preguntas,respuestas));
        PrimaryController.arbolFinal=enlazarRespuestas(arbolPreguntas,respuestas);
        PrimaryController.filePredeterminado=false;
        String[] direccionPreg = rutaPreguntas.split(Pattern.quote(File.separator));
        String[] direccionResp = rutaRespuestas.split(Pattern.quote(File.separator));
        nomPreguntas=direccionPreg[direccionPreg.length-1];
        nomRespuestas=direccionResp[direccionResp.length-1];
        //PrimaryController.lbPreguntastxt.setText(nomPreguntas);
        //PrimaryController.lbRespuestastxt.setText(nomRespuestas);
        //regresar a la ventana anterior
        App.setRoot("primary");
    }
    
}
