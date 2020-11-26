/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacion;

import Surtidores.ObservadorEstacionDeServicio;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author braya
 */
public class ObservarSurtidor extends Observable implements Runnable {

    private int puerto;
    private DataInputStream dis;
    private DataOutputStream out;
    //private VistaPrincipalController estacionDeServicio;

    public ObservarSurtidor(int puerto) {
        this.puerto = puerto;
      //  this.estacionDeServicio = vista;
    }

    @Override
    public void run() {
        try (ServerSocket listener = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado y escuchando en el puerto " + puerto);
            while (true) {
                Socket sc = listener.accept();
                DataInputStream inSocket = new DataInputStream(sc.getInputStream());
                String TipoConbustible = inSocket.readUTF();
                String NdeLitros = inSocket.readUTF();
                String totalPagar = inSocket.readUTF();

                System.out.println("DESDE EL Estacion de servicio Boleta....");

                System.out.println("Tipo de combustible: " + TipoConbustible);
                System.out.println("Litros: " + NdeLitros);
                System.out.println("Total a pagar: " + totalPagar);
                // notificar a la estacion.
                this.setChanged();
                this.notifyObservers();
                this.clearChanged();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObservadorEstacionDeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

}
