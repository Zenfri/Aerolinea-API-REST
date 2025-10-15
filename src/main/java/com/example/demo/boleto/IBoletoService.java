package com.example.demo.boleto;

import com.example.demo.respuesta.CuerpoResponse;

public interface IBoletoService {
    CuerpoResponse<BoletoResponse> guardarBoleto(BoletoRequest boletoRequest);
}
