package models;

public class Proyecto {
    private int idProyecto;
    private String nombreProyecto;
    private Float inversion;
    private int tiempoVida;
    private String fechaInicio;
    private String fechaFin;
    private Float ElectricidadGenerada;
    private Float costoTotal;

    public Proyecto(){

    }
    
    public Proyecto(int idProyecto, String nombreProyecto, Float inversion, int tiempoVida, String fechaInicio, String fechaFin, Float ElectricidadGenerada, Float costoTotal) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.inversion = inversion;
        this.tiempoVida = tiempoVida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ElectricidadGenerada = ElectricidadGenerada;
        this.costoTotal = costoTotal;
    }


    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Float getInversion() {
        return inversion;
    }

    public void setInversion(Float inversion) {
        this.inversion = inversion;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Float getElectricidadGenerada() {
        return ElectricidadGenerada;
    }

    public void setElectricidadGenerada(Float ElectricidadGenerada) {
        this.ElectricidadGenerada = ElectricidadGenerada;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

}

