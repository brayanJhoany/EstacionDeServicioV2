/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Surtidores;

import estacion.Precio;
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
public class ObservadorEstacionDeServicio extends Observable implements Runnable{
     private int puerto;
    private DataInputStream dis;
    private DataOutputStream out;
    
    public ObservadorEstacionDeServicio(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try (ServerSocket listener = new ServerSocket(puerto)){
            System.out.println("Servidor iniciado y escuchando en el puerto " + puerto);
            while(true){
                Socket sc = listener.accept();
                DataInputStream inSocket = new DataInputStream(sc.getInputStream());
                double b93 = inSocket.readDouble();
                double b95 = inSocket.readDouble();
                double b97 = inSocket.readDouble();
                double disel = inSocket.readDouble();
                double kerosene = inSocket.readDouble();
                System.out.println("DESDE EL SURTIDOR....");
                System.out.println("El precio de la 93: " + b93);
                
                
                Precio precios = new Precio(b93,b95,b97,disel,kerosene);
                this.setChanged();
                this.notifyObservers(precios);
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
