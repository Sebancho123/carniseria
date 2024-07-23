
package com.proyecto.carniseria.repository;

import com.proyecto.carniseria.model.Duenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDuenioRepository extends JpaRepository<Duenio, Long>{
    
}
