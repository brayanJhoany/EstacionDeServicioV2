/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Surtidores;

import estacion.Precio;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author braya
 */
public class VistaSurtidorController implements Initializable, Observer {

    @FXML
    private TextField cantidadDeCargaField;
    @FXML
    private Text totalAPagarTXT;
    @FXML
    private Button cancelarCompraBtn;
    @FXML
    private Text identificadorSurtidor;

    String idSurtidor;
    int puerto;
    @FXML
    private ComboBox<String> listaPrecioBox;
    @FXML
    private Text precioCombustible;
    Precio precioAux;

    boolean bloquearActualizacionDePrecios;

    @FXML
    private Button confirmarCompraBtn;
    @FXML
    private Button generarBoletaBtn;

    void VistaSurtidorController() {
        this.cancelarCompraBtn = new Button();
        this.totalAPagarTXT = new Text();
        this.cantidadDeCargaField = new TextField();
        this.identificadorSurtidor = new Text();
        this.listaPrecioBox = new ComboBox<>();
        this.precioCombustible = new Text();
        this.confirmarCompraBtn = new Button();
        this.bloquearActualizacionDePrecios = false;
        this.confirmarCompraBtn = new Button();
        this.generarBoletaBtn = new Button();

    }

    public void initData(String idSurtidor, int puerto) {
        this.idSurtidor = idSurtidor;
        this.puerto = puerto;
        this.identificadorSurtidor.setText(idSurtidor);
        setPuerto(puerto);
        ObservadorEstacionDeServicio servidor = new ObservadorEstacionDeServicio(this.puerto);
        servidor.addObserver(this);
        Thread t = new Thread(servidor);
        t.start();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.listaPrecioBox.getItems().addAll(
                "93", "95", "97", "Diesel", "Kerosene"
        );
    }

    @FXML
    private void cancelarCompra(ActionEvent event) {
        //volvemos habilitar la actualizacion de los precios.
        this.bloquearActualizacionDePrecios=false;
        System.out.println("Los precios Estan bloqueados? "+ this.bloquearActualizacionDePrecios);
        Stage stage = (Stage) this.cancelarCompraBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Precio) {
            //this.infoPreciosTextArea.clear();
            //se pueden actualizar los precios
            System.out.println("Los precios Estan bloqueados? "+ this.bloquearActualizacionDePrecios);
            if (this.bloquearActualizacionDePrecios == false) {
                Precio precio = (Precio) arg;
                this.precioAux = precio;
                System.out.println("DESDE VISTA SUERTIDOR.....");
                System.out.println("93:  " + precio.getB93());
                String precio93 = "1. precio vencina 93: " + precio.getB93() + "\n";
                String precio95 = "2. precio vencina 95: " + precio.getB95() + "\n";
                String precio97 = "3. precio vencina : 97" + precio.getB97() + "\n";
                String disel = "4. precio vencina diesel: " + precio.getDisel() + "\n";
                String kerosen = "5. precio vencina kerosen: " + precio.getKerosene() + "\n";
                String texto = precio93 + precio95 + precio97 + disel + kerosen;
                // this.infoPreciosTextArea.appendText(texto);
                //enviarListaDePreciosASurtidores(precio);

            }

        } else {
            System.out.println("Error");

        }

    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @FXML
    private void calcularPrecioCombustible(ActionEvent event) {
        
        this.bloquearActualizacionDePrecios=true;
        System.out.println("Los precios Estan bloqueados? "+ this.bloquearActualizacionDePrecios);
        String tipoVencina = this.listaPrecioBox.getSelectionModel().getSelectedItem();
        //"93", "95", "97", "Diesel", "Kerosene"
        System.out.println("Tipo de vencina: " + tipoVencina);
        if (this.precioAux != null) {
            String p93 = "" + this.precioAux.getB93();
            String p95 = "" + this.precioAux.getB95();
            String p97 = "" + this.precioAux.getB97();
            String diesel = "" + this.precioAux.getDisel();
            String kerosene = "" + this.precioAux.getKerosene();
            if (tipoVencina.equalsIgnoreCase("93") == true) {
                this.precioCombustible.setText(p93);
            } else if (tipoVencina.equalsIgnoreCase("95") == true) {
                this.precioCombustible.setText(p95);
            } else if (tipoVencina.equalsIgnoreCase("97") == true) {
                this.precioCombustible.setText(p97);
            } else if (tipoVencina.equalsIgnoreCase("Diesel") == true) {
                this.precioCombustible.setText(diesel);
            } else if (tipoVencina.equalsIgnoreCase("Kerosene") == true) {
                this.precioCombustible.setText(kerosene);
            }
        }

    }

    @FXML
    private void ConfirmarCompra(ActionEvent event) {
        String tipoVencina = this.listaPrecioBox.getSelectionModel().getSelectedItem();

        if (this.cantidadDeCargaField.getText() != null) {
            int litros = Integer.parseInt(this.cantidadDeCargaField.getText());
            String p93 = "" + this.precioAux.getB93();
            String p95 = "" + this.precioAux.getB95();
            String p97 = "" + this.precioAux.getB97();
            String diesel = "" + this.precioAux.getDisel();
            String kerosene = "" + this.precioAux.getKerosene();
            int total = 0;
            if (tipoVencina.equalsIgnoreCase("93") == true) {
                total = (int) (precioAux.getB93() * litros);
                String t = "" + total;
                this.totalAPagarTXT.setText(t);
            } else if (tipoVencina.equalsIgnoreCase("95") == true) {
                total = (int) (precioAux.getB95() * litros);
                String t = "" + total;
                this.totalAPagarTXT.setText(t);
            } else if (tipoVencina.equalsIgnoreCase("97") == true) {
                total = (int) (precioAux.getB97() * litros);
                String t = "" + total;
                this.totalAPagarTXT.setText(t);
            } else if (tipoVencina.equalsIgnoreCase("Diesel") == true) {
                total = (int) (precioAux.getDisel() * litros);
                String t = "" + total;
                this.totalAPagarTXT.setText(t);
            } else if (tipoVencina.equalsIgnoreCase("Kerosene") == true) {
                total = (int) (precioAux.getKerosene() * litros);
                String t = "" + total;
                this.totalAPagarTXT.setText(t);
            }
        }
    }

    @FXML
    private void GenerarBoleta(ActionEvent event) {
        //volvemos a hablilitar la actualizacion de precios.
        this.bloquearActualizacionDePrecios=false;
        System.out.println("Los precios Estan bloqueados? "+ this.bloquearActualizacionDePrecios);
        
        String tipoVencina = this.listaPrecioBox.getSelectionModel().getSelectedItem();
        String litros= this.cantidadDeCargaField.getText();
        String total =this.precioCombustible.getText();
        System.out.println("****** Boleta ********");
        System.out.println("Tipo de combustible: "+ tipoVencina);
        System.out.println("Litros: "+litros);
        System.out.println("Total a pagar: "+ total);
        System.out.println("*********************");
        
        enviarBoletaEstacionDeServicio(tipoVencina,litros,total);
    }

    private void enviarBoletaEstacionDeServicio(String tipoVencina, String cantidadDeCargaField, String precioCombustible) {
        //enviar los precios.
    }

}
