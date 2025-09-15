package com.example.demo.pasajero;

import com.example.demo.respuesta.CuerpoRespuesta;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pasajero")
@AllArgsConstructor
public class PasajeroController {
    private PasajeroServiceImpl pasajeroService;
    @PostMapping("/guardar")
    public CuerpoRespuesta<PasajeroEntity> guardar(@RequestBody PasajeroEntity pasajeroEntity){
        return this.pasajeroService.guardarPasajero(pasajeroEntity);
    }

    @GetMapping("/buscarPorId/{id}")
    public CuerpoRespuesta<PasajeroEntity> buscar(@PathVariable Long id){
        return this.pasajeroService.buscarPasajeroPorId(id);
    }

}
