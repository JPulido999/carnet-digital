package com.unsch.carnet_digital.controller;

import com.unsch.carnet_digital.model.Usuario;
import com.unsch.carnet_digital.service.BarcodeService;
import com.unsch.carnet_digital.service.QRService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/carnet")
@CrossOrigin(origins = "http://localhost:5173")
public class CarnetController {

    private final BarcodeService barcodeService;
    private final QRService qrService;

    public CarnetController(BarcodeService barcodeService, QRService qrService) {
        this.barcodeService = barcodeService;
        this.qrService = qrService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> obtenerCarnet(@AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(401).body(Map.of("error", "No autenticado"));
        }

        try {
            // Barcode con DNI
            String barcodeBase64 = barcodeService.generarCode128Base64(usuario.getDni(), 600, 150);

            // 👉 URL de verificación (esta página será escaneada por vigilantes)
            String urlVerificacion = "https://miapp.com/verificacion/" + usuario.getDni();

            // Generar QR basado en esa URL
            String qrBase64 = qrService.generarQRCodeBase64(urlVerificacion, 260, 260);

            // Devolver datos del carnet
            var data = Map.of(
                    "id", usuario.getId(),
                    "nombres", usuario.getNombres(),
                    "apellidos", usuario.getApellidos(),
                    "dni", usuario.getDni(),
                    "correo", usuario.getCorreo(),
                    "codigoEstudiante", usuario.getCodigoEstudiante(),
                    "escuela", usuario.getEscuela(),
                    "fotoCarnetUrl", usuario.getFotoCarnetUrl(),
                    "barcodeBase64", barcodeBase64,
                    "qrBase64", qrBase64  // 👈 NUEVO
            );

            return ResponseEntity.ok(data);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error generando el carnet"));
        }
    }
}
