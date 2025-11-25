package com.unsch.carnet_digital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unsch.carnet_digital.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findByCorreo(String correo);
}
