package com.example.demo.piloto;

import com.example.demo.respuesta.CuerpoResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/pilotos")
public class PilotoController {
    private PilotoServiceImpl pilotoServiceImpl;

    public PilotoController(PilotoServiceImpl pilotoServiceImpl){
        this.pilotoServiceImpl = pilotoServiceImpl;

    }
    @PostMapping("/guardar")
    public CuerpoResponse<PilotoResponse> guardar(@RequestBody PilotoRequest pilotoRequest) {
        return this.pilotoServiceImpl.guardarPiloto(pilotoRequest);
    }

    @GetMapping("/listar")
    public List<PilotoEntity> listar(){
        return this.pilotoServiceImpl.listar();
    }

    @GetMapping("/buscarPorId/{id}")
    public CuerpoResponse<PilotoResponse> buscarPiloto(@PathVariable Long id) {
        return this.pilotoServiceImpl.buscarPorId(id);
    }

    /*@GetMapping({"/buscarPorNombre","/buscarPorNombre/","/buscarPorNombre/{nombre}"})
    public CuerpoRespuesta<List<Piloto>> buscarPorNombre(@PathVariable(required = false) String nombre){
        return this.pilotoServiceImpl.buscarPorNombre(nombre);
    }*/

    @GetMapping("/buscarPorNombre")
    public CuerpoResponse<List<PilotoResponse>> buscarPorNombre(@PathVariable String nombre){
        return this.pilotoServiceImpl.buscarPorNombre(nombre);
    }
}
