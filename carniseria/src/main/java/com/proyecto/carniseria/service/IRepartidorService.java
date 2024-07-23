
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Repartidor;
import java.util.List;


public interface IRepartidorService {
    
    //traerlos
    public List<Repartidor> getRepartidor();
    
    //crear
    public void saveRepartidor(Repartidor repart);
    
    //eliminar
    public void deleteRepartidor(Long id_repart);
    
    //buscar
    public Repartidor findRepartidor(Long id_repart);
    
    //editar
    public Repartidor editRepartidor(Repartidor repart);
    
    //para cuando el repartidor ya este disponible
    public String ImDiponible (Long id_repart);
    
}
