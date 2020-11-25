/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacion;

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
public class EscucharSurtidores extends Observable implements Runnable{

    private int puerto;
    private DataInputStream dis;
    private DataOutputStream out;
    
    public EscucharSurtidores(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try (ServerSocket listener = new ServerSocket(puerto)){
            System.out.println("Servidor iniciado y escuchando en el puerto " + puerto);
            while(true){
                Socket sc = listener.accept();
                System.out.println("Cliente " + sc.getRemoteSocketAddress() + " se ha conectado");
                /**
                DataInputStream in = new DataInputStream(sc.getInputStream());
                int contador = in.readInt();
                int idSurtidor = in.readInt();
                Compra compra = new Compra(idSurtidor,in.readUTF(),in.readDouble(),in.readInt());
                if(contador!=1){
                    Error error = new Error(idSurtidor, contador);
                    this.setChanged();
                    this.notifyObservers(error);
                    this.clearChanged();
                }
                this.setChanged();
                this.notifyObservers(compra);
                this.clearChanged();
                 **/
            }
        } catch (IOException ex) {
            Logger.getLogger(EscucharSurtidores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
