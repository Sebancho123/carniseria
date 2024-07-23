
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Comida;
import java.util.List;


public interface IComidaService {
 
    //traerlos
    public List<Comida> getComidas();
    
    //crear
    public void saveComida(Comida comida);
    
    //eliminar
    public void deleteComida(Long cod_comida);
    
    //buscar
    public Comida findComida (Long cod_comida);
    
    //editar
    public Comida editComida(Comida comida);
    
}
