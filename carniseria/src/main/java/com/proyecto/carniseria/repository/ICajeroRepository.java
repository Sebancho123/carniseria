
package com.proyecto.carniseria.repository;

import com.proyecto.carniseria.model.Cajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICajeroRepository extends JpaRepository<Cajero, Long>{
    
}
