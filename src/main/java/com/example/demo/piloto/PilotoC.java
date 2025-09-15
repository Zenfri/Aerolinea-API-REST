package com.example.demo.piloto;

import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/pilotos")
public class PilotoC {
    private PilotoServiceImpl pilotoServiceImpl;

    public PilotoC(PilotoServiceImpl pilotoServiceImpl){
        this.pilotoServiceImpl = pilotoServiceImpl;

    }
    @PostMapping("/guardar")
    public CuerpoRespuesta<Piloto> guardar(@RequestBody PilotoRequest pilotoRequest) {
        return this.pilotoServiceImpl.guardarPiloto(pilotoRequest);
    }

    @GetMapping("/listar")
    public List<Piloto> listar(){
        return this.pilotoServiceImpl.listar();
    }

    @GetMapping("/buscarPorId/{id}")
    public CuerpoRespuesta buscarPiloto(@PathVariable Long id) {
        return this.pilotoServiceImpl.buscarPorId(id);
    }
    
}
