package com.unsch.carnet_digital.service;

import com.unsch.carnet_digital.model.Usuario;
import com.unsch.carnet_digital.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario crear(Usuario usuario) {

        if (repository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        if (repository.existsByDni(usuario.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        // por defecto se crea activo
        usuario.setActivo(true);

        return repository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario datos) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Si el correo cambió, validar
        if (!usuario.getCorreo().equals(datos.getCorreo())
                && repository.existsByCorreo(datos.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Si el DNI cambió, validar
        if (!usuario.getDni().equals(datos.getDni())
                && repository.existsByDni(datos.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        usuario.setNombres(datos.getNombres());
        usuario.setApellidos(datos.getApellidos());
        usuario.setDni(datos.getDni());
        usuario.setCorreo(datos.getCorreo());
        usuario.setCodigoEstudiante(datos.getCodigoEstudiante());
        usuario.setEscuela(datos.getEscuela());
        usuario.setFotoCarnetUrl(datos.getFotoCarnetUrl());
        usuario.setFotoGoogleUrl(datos.getFotoGoogleUrl());
        usuario.setRol(datos.getRol());
        usuario.setActivo(datos.isActivo());

        return repository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // --- MÉTODO NORMAL (si lo necesitas)
    public Usuario buscarPorCorreo(String correo) {
        return repository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("No existe usuario con ese correo"));
    }

    // --- MÉTODO PARA LOGIN Google Sign-In ---
    // SOLO permite usuarios ACTIVOS
    public Usuario buscarActivoPorCorreo(String correo) {

        Usuario usuario = repository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Correo no registrado en el sistema."));

        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario inactivo. Acceso denegado.");
        }

        return usuario;
    }

    public Usuario buscarPorDni(String dni) {
        return repository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No existe usuario con ese DNI"));
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        repository.deleteById(id);
    }
}
