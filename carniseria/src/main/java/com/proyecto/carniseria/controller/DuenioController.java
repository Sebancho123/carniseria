
package com.proyecto.carniseria.controller;

import com.proyecto.carniseria.model.Duenio;
import com.proyecto.carniseria.service.IDuenioService;
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
@RequestMapping("/duenio")
public class DuenioController {
    
    @Autowired
    private IDuenioService iDueSer;
    
    @GetMapping("/traerlos")
    public List<Duenio> getDuenios() {
        return iDueSer.getDuenios();
    }
    
    @PostMapping("/crear")
    public String saveDuenio(@RequestBody Duenio duenio) {
        
        iDueSer.saveDuenio(duenio);
        return "se creo correctamente";
        
    }
    
    @DeleteMapping("/eliminar/{id_duenio}")
    public String deleteDuenio (@PathVariable Long id_duenio) {
        
        iDueSer.deleteDuenio(id_duenio);
        return "se elimino correctamente";
        
    }
    
    @GetMapping("/buscar/{id_duenio}")
    public Duenio findDuenio(@PathVariable Long id_duenio) {
        return iDueSer.findDuenio(id_duenio);
        
    }
    
    @PutMapping("/editar") 
    public Duenio editDuenio(@RequestBody Duenio duenio) {
        return iDueSer.editDuenio(duenio);
    }
    
    @GetMapping("/pagar")
    public String pagarCajero () {
        
        return iDueSer.pagarCajero();
        
    }
    
    
}
