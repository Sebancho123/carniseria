
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Duenio;
import java.util.List;


public interface IDuenioService {
    //traerlos
    public List<Duenio> getDuenios();
    
    //crear
    public void saveDuenio(Duenio duenio);
    
    //eliminar
    public void deleteDuenio(Long id_duenio);
    
    //encontrar
    public Duenio findDuenio(Long id_duenio);
    
    //editar
    public Duenio editDuenio(Duenio duenio);
    
    public String pagarCajero();
    
}
