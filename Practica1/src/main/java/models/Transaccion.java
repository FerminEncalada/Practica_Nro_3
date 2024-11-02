package models;

import java.sql.Date;

public class Transaccion {
    private int idTransaccion;
    private String inversionista;
    private String tipoTransaccion;
    private Date fecha;
    private String descripción;

    public Transaccion(){

    }

    public Transaccion(int idTransaccion, String inversionista, String tipoTransaccion, Date fecha, String descripción) {
        this.idTransaccion = idTransaccion;
        this.inversionista = inversionista;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.descripción = descripción;
    }
    
    public int getIdTransaccion() {
        return idTransaccion;
    }
    
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getInversionista() {
        return inversionista;
    }

    public void setInversionista(String inversionista) {
        this.inversionista = inversionista;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
}
