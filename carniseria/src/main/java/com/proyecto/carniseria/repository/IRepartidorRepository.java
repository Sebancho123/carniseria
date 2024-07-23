
package com.proyecto.carniseria.repository;

import com.proyecto.carniseria.model.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepartidorRepository extends JpaRepository<Repartidor, Long>{
    
}
