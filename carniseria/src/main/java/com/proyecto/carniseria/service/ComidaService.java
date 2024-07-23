
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Comida;
import com.proyecto.carniseria.repository.IComidaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComidaService implements IComidaService{

    @Autowired
    private IComidaRepository iComirepo;
    
    
    @Override
    public List<Comida> getComidas() {
        
        List<Comida> listaComidas = iComirepo.findAll();
        
        return listaComidas;
        
    }

    @Override
    public void saveComida(Comida comida) {
        
        iComirepo.save(comida);
        
    }

    @Override
    public void deleteComida(Long cod_comida) {
        
        iComirepo.deleteById(cod_comida);
        
    }

    @Override
    public Comida findComida(Long cod_comida) {
        
        return iComirepo.findById(cod_comida).orElse(null);
        
    }

    @Override
    public Comida editComida(Comida comida) {
        
        this.saveComida(comida);
        return comida;
        
    }
    
}
