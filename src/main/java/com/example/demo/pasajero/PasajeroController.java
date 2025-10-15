package com.example.demo.pasajero;

import com.example.demo.respuesta.CuerpoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pasajero")
@AllArgsConstructor
public class PasajeroController {
    private PasajeroServiceImpl pasajeroService;
    @PostMapping("/guardar")
    public CuerpoResponse<PasajeroResponse> guardar(@RequestBody PasajeroRequest pasajeroRequest){
        return this.pasajeroService.guardarPasajero(pasajeroRequest);
    }

    @GetMapping("/buscarPorId/{id}")
    public CuerpoResponse<PasajeroResponse> buscar(@PathVariable Long id){
        return this.pasajeroService.buscarPasajeroPorId(id);
    }

}
