package models;

public class Inversionista {
    private Integer idInversionista;
    private String nombres;
    private String dni;
    private String pertenencia;

    public Inversionista() {
    }

    public Inversionista(Integer idInversionista, String nombres, String dni, String pertenencia) {
        this.idInversionista = idInversionista;
        this.nombres = nombres;
        this.dni = dni;
        this.pertenencia = pertenencia;
    }

    public Integer getIdInversionista() {
        return idInversionista;
    }

    public void setIdInversionista(Integer idInversionista) {
        this.idInversionista = idInversionista;
    }

    public String getNombres() {
        return nombres;
    }   

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPertenencia() {
        return pertenencia;
    }

    public void setPertenencia(String pertenencia) {
        this.pertenencia = pertenencia;
    }


}
