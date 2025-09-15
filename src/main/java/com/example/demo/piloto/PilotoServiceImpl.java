package com.example.demo.piloto;

import com.example.demo.respuesta.CuerpoRespuesta;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PilotoServiceImpl implements IPilotoService{
    private IPilotoRepository iPilotoRepository;

    public PilotoServiceImpl(IPilotoRepository iPilotoRepository){
        this.iPilotoRepository = iPilotoRepository;
    }
    @Override
    public CuerpoRespuesta<Piloto> guardarPiloto(PilotoRequest pilotoRequest) {
        CuerpoRespuesta<Piloto> cuerpoRespuesta = new CuerpoRespuesta<>();

        //Validar que la longitud del nombre no supere los 20 caracteres
        if(pilotoRequest.getNombres().length() > 20){
            return new CuerpoRespuesta<>(
                    400,
                    "Error al enviar el nombre, excede los 20 caracteres!",
                    Optional.empty()
            );
        }

        Piloto piloto = new Piloto();
        piloto.setNombre(pilotoRequest.getNombres());
        piloto.setApellido(pilotoRequest.getApellidos());
        piloto.setEstado(true);

        this.iPilotoRepository.save(piloto);

        return new CuerpoRespuesta<>(
                201,
                "El Piloto se guardó exitósamente",
                Optional.of(piloto)
        );
    }

    @Override
    public CuerpoRespuesta<Piloto> buscarPorId(Long id) {
        Optional<Piloto> pilotoOptional = this.iPilotoRepository.findById(id);
        //Si no se encontró
        if(pilotoOptional.isEmpty()) {
            //El estandar dice que solo <> es la mejor forma para devolver esta respuesta "sin especificar el tipo, que sería redundante"
            return new CuerpoRespuesta<>(
                    404,
                    "No se encontró el Piloto con el id: "+id,
                    Optional.empty()
            );
        }
        //Si se encontró
        return new CuerpoRespuesta<>(
                200,
                "Se encontró el Piloto con el id: "+id,
                pilotoOptional
        );
    }

    @Override
    public List<Piloto> listar() {
        return this.iPilotoRepository.findAll();
    }
}
