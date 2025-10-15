package com.example.demo.boleto;

import com.example.demo.respuesta.CuerpoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/boleto")
public class BoletoController {

    private BoletoServiceImpl boletoServiceImpl;
    @PostMapping("/guardar")
    public CuerpoResponse<BoletoResponse> guardar(@RequestBody BoletoRequest boletoRequest){
        return this.boletoServiceImpl.guardarBoleto(boletoRequest);
    }
}
