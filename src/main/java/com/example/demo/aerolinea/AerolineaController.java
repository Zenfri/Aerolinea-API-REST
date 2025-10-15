package com.example.demo.aerolinea;

import com.example.demo.respuesta.ApiResponse;
import com.example.demo.respuesta.CuerpoResponse;
import com.example.demo.util.Constantes.CodigosHttp;
import com.example.demo.util.Constantes.Mensajes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolineas")
@Validated
public class AerolineaController {

    private AerolineaServiceImpl aerolineaServiceImpl;

    public AerolineaController(AerolineaServiceImpl aerolineaServiceImpl){
        this.aerolineaServiceImpl = aerolineaServiceImpl;
    }

    @PostMapping("/guardar")
    public ResponseEntity<ApiResponse<AerolineaResponse>> guardar(@Valid @RequestBody AerolineaRequest aerolineaRequest, HttpServletRequest request){
        AerolineaResponse aerolineaResponse = aerolineaServiceImpl.guardar(aerolineaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                HttpStatus.CREATED.value(), Mensajes.EXITO_CREACION, aerolineaResponse, request.getRequestURI())
        );
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ApiResponse<AerolineaResponse>> buscarPorId(
            @PathVariable @Min(value = 1, message = "El id debe ser mayor que 0") Long id,
            HttpServletRequest request){
        AerolineaResponse aerolineaResponse = aerolineaServiceImpl.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(
            new ApiResponse<>(
                    HttpStatus.FOUND.value(),
                    Mensajes.EXITO_BUSQUEDA,
                    aerolineaResponse,
                    request.getRequestURI()
            )
        );
    }

    @PutMapping("/actualizarPorId/{id}")
    public ResponseEntity<ApiResponse<AerolineaResponse>> actualizarPorId(
            @PathVariable @Min(value = 1, message = "El id debe ser mayor que 0") Long id,
            @Valid @RequestBody AerolineaRequest aerolineaRequest,
            HttpServletRequest request){
        AerolineaResponse aerolineaResponse = aerolineaServiceImpl.actualizarPorId(id, aerolineaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        Mensajes.EXITO_ACTUALIZACION,
                        aerolineaResponse,
                        request.getRequestURI()
                )
        );
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<AerolineaResponse>>> listar(HttpServletRequest request){
        List<AerolineaResponse> lResponseEntities = aerolineaServiceImpl.listar();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        HttpStatus.OK.value(),
                        Mensajes.EXITO_LISTAR,
                        lResponseEntities,
                        request.getRequestURI()
                )
        );
    }
}
