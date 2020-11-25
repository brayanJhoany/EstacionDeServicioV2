/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacion;

import java.util.Date;

/**
 *
 * @author braya
 */
public class Compra {
    
    private int id;
    private int idsurtidor;
    private String tipoConbustible;
    private double litrosCargados;
    private int precioTotal;
    private Date fecha;

    public Compra(int id, int idsurtidor, String tipoConbustible, double litrosCargados, int precioTotal, Date fecha) {
        this.id = id;
        this.idsurtidor = idsurtidor;
        this.tipoConbustible = tipoConbustible;
        this.litrosCargados = litrosCargados;
        this.precioTotal = precioTotal;
        this.fecha= fecha;
    }
    
    public Compra(int idsurtidor, String tipoConbustible, double litrosCargados, int precioTotal) {
        this.idsurtidor = idsurtidor;
        this.tipoConbustible = tipoConbustible;
        this.litrosCargados = litrosCargados;
        this.precioTotal = precioTotal;
        this.fecha= new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdsurtidor() {
        return idsurtidor;
    }

    public void setIdsurtidor(int idsurtidor) {
        this.idsurtidor = idsurtidor;
    }

    public String getTipoConbustible() {
        return tipoConbustible;
    }

    public void setTipoConbustible(String tipoConbustible) {
        this.tipoConbustible = tipoConbustible;
    }

    public double getLitrosCargados() {
        return litrosCargados;
    }

    public void setLitrosCargados(double litrosCargados) {
        this.litrosCargados = litrosCargados;
    }

    public int getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    public void imprimirCompra(){   
        System.out.println("Compra");
        System.out.println(this.idsurtidor); 
        System.out.println(this.tipoConbustible); 
        System.out.println(this.litrosCargados); 
        System.out.println(this.precioTotal); 
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
