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

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario buscarPorDni(String dni) {
        return repository.findByDni(dni).orElse(null);
    }
}
