package models;

public class Proyecto {
    private Integer idProyecto;
    private String nombreProyecto;
    private Float inversion;
    private Integer tiempoVida;
    private String fechaInicio;
    private String fechaFin;
    private Float electricidadGenerada;
    private Float costoTotal;
    private String codigodelproyecto;
    public Proyecto(){

    }
    
    public Proyecto(Integer idProyecto, String nombreProyecto, Float inversion, Integer tiempoVida, String fechaInicio, String fechaFin, Float electricidadGenerada, Float costoTotal, String codigodelproyecto) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.inversion = inversion;
        this.tiempoVida = tiempoVida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.electricidadGenerada = electricidadGenerada;
        this.costoTotal = costoTotal;
        this.codigodelproyecto = codigodelproyecto;
    }


    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
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

    public Integer getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(Integer tiempoVida) {
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
        return electricidadGenerada;
    }

    public void setElectricidadGenerada(Float electricidadGenerada) {
        this.electricidadGenerada = electricidadGenerada;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCodigodelproyecto() {
        return codigodelproyecto;
    }

    public void setCodigodelproyecto(String codigodelproyecto) {
        this.codigodelproyecto = codigodelproyecto;
    }

}

