/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Surtidores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author braya
 */
public class VistaSurtidorController implements Initializable {

    @FXML
    private TextField cantidadDeCargaField;
    @FXML
    private Text totalAPagarTXT;
    @FXML
    private Button cancelarCompraBtn;
    @FXML
    private Text identificadorSurtidor;
    
    int idSurtidor, puerto;
    
    
    public VistaSurtidorController(){
        this.cantidadDeCargaField = new TextField();
        this.totalAPagarTXT = new Text();
        this.cancelarCompraBtn = new Button();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelarCompra(ActionEvent event) {
        Stage stage = (Stage) this.cancelarCompraBtn.getScene().getWindow();
        stage.close();
    }
    
}
