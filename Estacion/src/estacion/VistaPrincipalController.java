/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacion;


import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author braya
 */
public class VistaPrincipalController implements Initializable,Observer {

    @FXML
    private Button holaBtn;
    int identificadorSucursal;

    public VistaPrincipalController() {
        this.identificadorSucursal=1;
        Servidor s = new Servidor(5000,this);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();
    }
    
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void saludar(ActionEvent event) {
        System.out.println("Hola");
    }

    @Override
    public void update(Observable o, Object arg){
        //System.out.println("TEXTO: " + (String)  arg);
        if(arg instanceof Precio){
            Precio precio = (Precio) arg;
            System.out.println("precio de 93: "+ precio.getB93());
        }else{
            System.out.println("Error");
        }
    }


    
    
    
}
