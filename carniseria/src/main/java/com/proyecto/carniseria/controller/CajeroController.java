
package com.proyecto.carniseria.controller;

import com.proyecto.carniseria.model.Cajero;
import com.proyecto.carniseria.service.ICajeroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cajero")
public class CajeroController {
    
    @Autowired
    private ICajeroService iCajSer;
    
    @GetMapping("/traerlos")
    public List<Cajero> getCajeros(){
        return iCajSer.getCajeros();
    }
    
    @PostMapping("/crear")
    public String saveCajero(@RequestBody Cajero cajero) {
        iCajSer.saveCajero(cajero);
        return "se creo correctamente";
    }
    
    @DeleteMapping("/eliminar/{id_cajero}")
    public String deleteCajero(@PathVariable Long id_cajero) {
        iCajSer.deleteCajero(id_cajero);
        return "se elimino correctamente";
    }
    
    @GetMapping("/buscar/{id_cajero}")
    public Cajero findCajero(@PathVariable Long id_cajero) {
        return iCajSer.findCajero(id_cajero);
    }
    
    @PutMapping("/editar")
    public Cajero editCajero(@RequestBody Cajero cajero) {
        return iCajSer.editCajero(cajero);
    }
    
    @GetMapping("/depositar/jefe/{id_cajero}")
    public String darDineJefe(@PathVariable Long id_cajero) {
        
        return iCajSer.darDineJefe(id_cajero);
        
    }
    
}
