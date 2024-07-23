
package com.proyecto.carniseria.repository;

import com.proyecto.carniseria.model.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IComidaRepository extends JpaRepository<Comida, Long>{
    
}
