
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cajero;
import java.util.List;


public interface ICajeroService {
    
    //traerlos
    public List<Cajero> getCajeros();
    
    //crear
    public void saveCajero(Cajero cajero);
    
    //eliminar
    public void deleteCajero(Long id_cajero);
    
    //buscar
    public Cajero findCajero(Long id_cajero);
    
    //editar
    public Cajero editCajero(Cajero cajero);
    
    public String darDineJefe(Long id_cajero);
    
}
