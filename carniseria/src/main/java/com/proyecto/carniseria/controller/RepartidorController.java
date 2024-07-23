
package com.proyecto.carniseria.controller;

import com.proyecto.carniseria.model.Repartidor;
import com.proyecto.carniseria.service.IRepartidorService;
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
@RequestMapping("/repartidor")
public class RepartidorController {
    
    @Autowired
    private IRepartidorService iRepSer;
    
    @GetMapping("/traerlos")
    public List<Repartidor> getRepartidor () {
        
        return iRepSer.getRepartidor();
        
    }
    
    @PostMapping("/crear")
    public String saveRepartidor(@RequestBody Repartidor repart) {
        
        iRepSer.saveRepartidor(repart);
        return "se creo correctamente";
        
    }
    
    @DeleteMapping("/eliminar/{id_repart}")
    public String deleteRepartidor (@PathVariable Long id_repart) {
        iRepSer.deleteRepartidor(id_repart);
        return "se elimino correctamente";
    }
    
    @GetMapping("/buscar/{id_repart}")
    public Repartidor findRepartidor(@PathVariable Long id_repart) {
        
        return iRepSer.findRepartidor(id_repart);
    }
    
    @PutMapping("/editar")
    public Repartidor editRepartidor (@RequestBody Repartidor repart) {
        
        return iRepSer.editRepartidor(repart);
        
    }
    
    @GetMapping("/disponible/{id_repart}")
    public String ImDisponible(@PathVariable Long id_repart) {
        
        return iRepSer.ImDiponible(id_repart);
        
    }
    
}
