/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacion;


import Surtidores.Surtidor;
import Surtidores.VistaSurtidorController;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author braya
 */
public class VistaPrincipalController implements Initializable,Observer {

    int identificadorSucursal;
    @FXML
    private TextArea infoPreciosTextArea;
    @FXML
    private TextField idSurtidorTextField;
    @FXML
    private TextField puertoSurtidorTextField;
    @FXML
    private Button cambiarPreciosBtn;
    @FXML
    private Button crearSurtidorBtn;
    Precio precioAux;
    ArrayList<Surtidores.Surtidor>surtidores;
    public VistaPrincipalController() {
        this.surtidores =new ArrayList<Surtidores.Surtidor>();
        this.idSurtidorTextField= new TextField();
        this.puertoSurtidorTextField = new TextField();
        this.infoPreciosTextArea = new TextArea();
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

 
    @Override
    public void update(Observable o, Object arg){
        //System.out.println("TEXTO: " + (String)  arg);
        if(arg instanceof Precio){
            this.infoPreciosTextArea.clear();
            Precio precio = (Precio) arg;
            String precio93= "1. precio vencina 93: "+ precio.getB93()+"\n";
            String precio95= "2. precio vencina 95: "+ precio.getB95()+"\n";
            String precio97= "3. precio vencina : 97"+ precio.getB97()+"\n";
            String disel= "4. precio vencina diesel: "+ precio.getDisel()+"\n";
            String kerosen = "5. precio vencina kerosen: "+ precio.getKerosene()+"\n";
            String texto = precio93 + precio95+precio97+disel+kerosen;
            this.infoPreciosTextArea.appendText(texto);
            enviarListaDePreciosASurtidores(precio);
            this.precioAux=precio;
            

        }else{
            System.out.println("Error");
        }
    }

    @FXML
    private void cambiarPrecios(ActionEvent event) {
        System.out.println("Se intenta actualizar  los precios.");
        System.out.println("Precio 93: "+this.precioAux.getB93());
         enviarListaDePreciosASurtidores(this.precioAux);
    }

    @FXML
    private void crearNuevoSurtidor(ActionEvent event) throws IOException {
        //necesitamos crear la instancia de surtidor
        if(this.idSurtidorTextField.getText() != null && this.puertoSurtidorTextField.getText() != null){
            String idSurt=this.idSurtidorTextField.getText();
            int puerto = Integer.parseInt(this.puertoSurtidorTextField.getText());
            //System.out.println("id surtidor: " + idSurt);
            //System.out.println("Puerto: " + puerto);
            
            abrirVistaSurtidor(idSurt,puerto);
            Surtidores.Surtidor surtidor = new Surtidor(Integer.parseInt(idSurt), puerto);
            this.surtidores.add(surtidor);
        }
        
       
    }
    
    public void abrirVistaSurtidor(String idSurtidor, int puerto) throws IOException{
       
       // VistaSurtidorController dialogController = new VistaSurtidorController(idSurtidor, puerto);
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "/Surtidores/VistaSurtidor.fxml"));

        //fxmlLoader.setController(dialogController);
        //fxmlLoader.setLocation(VistaSurtidorController.class.getResource("/Surtidores/VistaSurtidor.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Nuevo Surtidor");
        stage.setScene(new Scene(root1));
        
        VistaSurtidorController controller = loader.<VistaSurtidorController>getController();
        controller.initData(idSurtidor,puerto);

        
        
        stage.setResizable(false);
        stage.show();
        
       
      
       


    }
    
    
    


    public void enviarPrecioAlSuertidor(String idSurtidor, int puerto){
        
    }
    
    private void enviarListaDePreciosASurtidores(Precio p) {
        String ip = "localhost";
        for (int i = 0; i < this.surtidores.size(); i++) {
            try (
                Socket ss = new Socket(ip, this.surtidores.get(i).getPuerto())) {
                System.out.println("Se enviaran los datos: "+ this.surtidores.get(i).getPuerto());
                System.out.println("El precio 93: " + p.getB93() );
                DataOutputStream out = new DataOutputStream(ss.getOutputStream());
                out.writeDouble(p.getB93());
                out.writeDouble(p.getB95());
                out.writeDouble(p.getB97());
                out.writeDouble(p.getDisel());
                out.writeDouble(p.getKerosene());
            } catch (IOException ex) {
                Logger.getLogger(Surtidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

