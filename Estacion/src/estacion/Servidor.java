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
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Observable implements Runnable{

    private DataInputStream entrada;
    private final int puerto;
    private Socket sc;
    private ServerSocket servidor;
    private VistaPrincipalController estacionDeServicio;

    public Servidor(int puerto, VistaPrincipalController estacion) {
        this.puerto = puerto;
        this.estacionDeServicio = estacion;
    }

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(this.puerto);
            System.out.println("Estamos escuchando desde el puerto :" + this.puerto);
            while (true) {
                sc = servidor.accept();
                entrada = new DataInputStream(sc.getInputStream());
                //outSocket = new DataOutputStream(sc.getOutputStream());
                switch (entrada.readInt()) {
                    case 1:
                        // va a sabera que sucursal ir por la ip
                        //cambiarPrecios();
                        double b93 = entrada.readDouble();
                        double b95 = entrada.readDouble();
                        double b97 = entrada.readDouble();
                        double disel = entrada.readDouble();
                        double kerosene = entrada.readDouble();
                        Precio precio = new Precio(b93, b95, b97, disel, kerosene);

                        this.setChanged();
                        this.notifyObservers(precio);
                        this.clearChanged();
                        break;

                    case 2:
                        //enviarTransacciones();
                        break;
                    default:
                        // code block
                        System.out.println("Fallo");
                }
                entrada.close();
                //outSocket.close();
                sc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
