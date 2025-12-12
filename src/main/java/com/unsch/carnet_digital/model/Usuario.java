package com.unsch.carnet_digital.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 15)
    private String dni;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;   // correo institucional (Google)

    @Column(name = "codigo_estudiante", length = 20)
    private String codigoEstudiante;

    @Column(length = 150)
    private String escuela;

    @Column(name = "foto_carnet_url", columnDefinition = "TEXT")
    private String fotoCarnetUrl; // foto profesional para el carnet

    @Column(name = "foto_google_url", columnDefinition = "TEXT")
    private String fotoGoogleUrl; // opcional: foto obtenida desde Google

    @Column(nullable = false, length = 30)
    private String rol = "";

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @Column(name = "uuid_verificacion", unique = true, length = 80)
    private String uuidVerificacion;

}