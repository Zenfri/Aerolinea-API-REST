package com.example.demo.vuelo;

import com.example.demo.respuesta.CuerpoResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private VueloServiceImpl vueloServiceImpl;

    public VueloController(VueloServiceImpl vueloServiceImpl){
        this.vueloServiceImpl = vueloServiceImpl;
    }
    @PostMapping("/guardar")
    public CuerpoResponse<VueloResponse> guardar(@RequestBody VueloRequest vueloRequest){
        return this.vueloServiceImpl.guardarVuelo(vueloRequest);
    }

    @GetMapping("/buscarPorId/{id}")
    public CuerpoResponse<VueloResponse> buscarPorId(@PathVariable Long id){
        return this.vueloServiceImpl.buscarVueloPorId(id);
    }

    @GetMapping("/buscarPorFecha/{fechaSalida}")
    public CuerpoResponse<List<VueloResponse>> buscarPorFechaSalida(
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaSalida){
        return this.vueloServiceImpl.buscarVueloPorFechaSalida(fechaSalida);
    }
}
