package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simple")
public class MiController {
    @GetMapping("/saludo")
    public String saludo(){
        return "Hola amiguitos";
    }

    @RequestMapping(value = "/despedida", method = RequestMethod.GET)
    //@RequestMapping("/adios") // si quieres que solo devuelva info (get), mejor sería colocar @GetMapping
    public String despedida(){
        return "Adiós amiguitos";
    }
}
