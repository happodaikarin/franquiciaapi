package com.ramses.franquiciaapi.services;
import com.ramses.franquiciaapi.model.Franquicia;
import com.ramses.franquiciaapi.repository.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public Franquicia agregarFranquicia(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public Optional<Franquicia> obtenerFranquiciaPorId(Long id) {
        return franquiciaRepository.findById(id);
    }


    //listar todas las franquicias
    public Iterable<Franquicia> listarFranquicias() {
        return franquiciaRepository.findAll();
    }

    public Franquicia actualizarNombre(Long id, String nuevoNombre) throws Exception {
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new Exception("Franquicia no encontrada"));
        franquicia.setNombre(nuevoNombre);
        return franquiciaRepository.save(franquicia);
    }
}

