package com.unsch.carnet_digital.service;

import com.unsch.carnet_digital.dto.VerificacionUsuarioDTO;
import com.unsch.carnet_digital.model.Usuario;
import com.unsch.carnet_digital.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VerificacionService {

    private final UsuarioRepository usuarioRepository;

    public VerificacionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public VerificacionUsuarioDTO verificarPorUuid(String uuid) {
        Usuario usuario = usuarioRepository.findByUuidVerificacion(uuid)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToDTO(usuario);
    }

    public VerificacionUsuarioDTO verificarManual(String dni, String codigo) {

        Usuario usuario;

        if (dni != null) {
            usuario = usuarioRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        } else if (codigo != null) {
            usuario = usuarioRepository.findByCodigoEstudiante(codigo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        } else {
            throw new RuntimeException("Debe proporcionar DNI o código");
        }

        return mapToDTO(usuario);
    }

    private VerificacionUsuarioDTO mapToDTO(Usuario usuario) {
    return new VerificacionUsuarioDTO(
            usuario.getNombres(),
            usuario.getApellidos(),
            usuario.getDni(),
            usuario.getCodigoEstudiante(),
            usuario.getRol(),              // si es String
            usuario.getEscuela(),
            usuario.getFotoCarnetUrl()
    );
}
}

