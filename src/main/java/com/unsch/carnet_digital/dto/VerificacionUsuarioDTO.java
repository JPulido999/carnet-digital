package com.unsch.carnet_digital.dto;

public class VerificacionUsuarioDTO {

    private String nombres;
    private String apellidos;
    private String dni;
    private String codigoEstudiante;   
    private String rol;
    private String escuela;
    private String fotoUrl;

    public VerificacionUsuarioDTO(
            String nombres,
            String apellidos,
            String dni,
            String codigoEstudiante,
            String rol,
            String escuela,
            String fotoUrl
    ) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.codigoEstudiante = codigoEstudiante;
        this.rol = rol;
        this.escuela = escuela;
        this.fotoUrl = fotoUrl;
    }

    // getters solamente (NO setters)
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public String getRol() { return rol; }
    public String getEscuela() { return escuela; }
    public String getFotoUrl() { return fotoUrl; }
}
