package com.example.demo.aerolinea;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/aerolinea")
public class AerolineaC {

    private IAerolineaRepository iAerolineaRepository;
    private AerolineaServiceImpl aerolineaServiceImpl;

    public AerolineaC(IAerolineaRepository iAerolineaRepository, AerolineaServiceImpl aerolineaServiceImpl){
        this.iAerolineaRepository = iAerolineaRepository;
        this.aerolineaServiceImpl = aerolineaServiceImpl;
    }

    @PostMapping("/guardar")
    public AerolineaEntity guardar(@RequestBody AerolineaEntity aerolinea){
        return this.aerolineaServiceImpl.guardar(aerolinea);
    }

    @GetMapping("/buscarPorId/{id}")
    public AerolineaEntity buscarPorId(@PathVariable Long id){
        Optional<AerolineaEntity> aerolineaEOptional = this.iAerolineaRepository.findById(id);
        if(aerolineaEOptional.isPresent()){
            return aerolineaEOptional.get();
        }
        else
            return null;
    }
}
