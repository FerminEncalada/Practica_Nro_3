package models;

public class Hospital {
    private Integer idHospital;
    private String nombre;
    private String direccion;
    private Integer capacidad; 
    private double longitud;
    private double latitud;
    public Hospital(){

    }
    
    public Hospital(Integer idHospital, String nombre, String direccion, Integer capacidad, double longitud, double latitud) {
        this.idHospital = idHospital;
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    
	public Integer getIdHospital() {
		return this.idHospital;
	}

	public void setIdHospital(Integer idHospital) {
		this.idHospital = idHospital;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}



}

