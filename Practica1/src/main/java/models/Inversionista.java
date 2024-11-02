package models;

public class Inversionista {
    private int idInversionista;
    private String nombres;
    private String dni;
    private Float inversion;

    public Inversionista() {
    }

    public Inversionista(int idInversionista, String nombres, String dni, Float inversion) {
        this.idInversionista = idInversionista;
        this.nombres = nombres;
        this.dni = dni;
        this.inversion = inversion;
    }

    public int getIdInversionista() {
        return idInversionista;
    }

    public void setIdInversionista(int idInversionista) {
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

    public Float getInversion() {
        return inversion;
    }

    public void setInversion(Float inversion) {
        this.inversion = inversion;
    }


}
