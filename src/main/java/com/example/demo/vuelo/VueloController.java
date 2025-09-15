package com.example.demo.vuelo;

import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vuelo")
public class VueloController {
    private VueloServiceImpl vueloServiceImpl;

    public VueloController(VueloServiceImpl vueloServiceImpl){
        this.vueloServiceImpl = vueloServiceImpl;
    }
    @PostMapping("/guardar")
    public CuerpoRespuesta<VueloResponse> guardar(@RequestBody VueloRequest vueloRequest){
        return this.vueloServiceImpl.guardarVuelo(vueloRequest);
    }
}
