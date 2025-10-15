package com.example.demo.avion;

import com.example.demo.respuesta.CuerpoResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avion")
public class AvionController {
    private AvionServiceImpl avionServiceImpl;

    public AvionController(AvionServiceImpl avionServiceImpl){
        this.avionServiceImpl = avionServiceImpl;
    }

    @PostMapping("/guardar")
    public CuerpoResponse<AvionResponse> guardar(@RequestBody AvionRequest avionRequest){
        return this.avionServiceImpl.guardarAvion(avionRequest);
    }


    @GetMapping("/buscarPorId/{id}")
    public CuerpoResponse<AvionResponse> buscarPorId(@PathVariable Long id){
        return this.avionServiceImpl.buscarAvionPorId(id);
    }


}
