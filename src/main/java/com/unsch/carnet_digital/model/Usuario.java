package com.unsch.carnet_digital.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private String rol;
    private String fotoUrl;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
