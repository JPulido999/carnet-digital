package com.unsch.carnet_digital.controller;

import com.unsch.carnet_digital.model.Usuario;
import com.unsch.carnet_digital.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{dni}")
    public Usuario buscarPorDni(@PathVariable String dni) {
        return service.buscarPorDni(dni);
    }

}
