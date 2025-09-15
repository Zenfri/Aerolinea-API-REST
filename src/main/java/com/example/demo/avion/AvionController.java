package com.example.demo.avion;

import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/avion")
public class AvionController {
    private AvionServiceImpl avionServiceImpl;

    public AvionController(AvionServiceImpl avionServiceImpl){
        this.avionServiceImpl = avionServiceImpl;
    }

    @PostMapping("/guardar")
    public CuerpoRespuesta<AvionEntity> guardar(@RequestBody AvionRequest avionRequest){
        return this.avionServiceImpl.guardarAvion(avionRequest);
    }


}
