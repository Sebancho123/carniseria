
package com.proyecto.carniseria.controller;

import com.proyecto.carniseria.model.Comida;
import com.proyecto.carniseria.service.IComidaService;
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
@RequestMapping("/comida")
public class ComidaController {
 
    @Autowired
    private IComidaService iComiSer;
    
    @GetMapping("/traerlos")
    public List<Comida> getComidas() {
        return iComiSer.getComidas();
    }
    
    @PostMapping("/crear")
    public String saveComida(@RequestBody Comida comida) {
        iComiSer.saveComida(comida);
        return "Se creo correctamente";
    }
    
    @DeleteMapping("/eliminar/{cod_comida}")
    public String deleteComida(@PathVariable Long cod_comida) {
        
        iComiSer.deleteComida(cod_comida);
        return "Se elimino correctamente";
    }
    
    @GetMapping("/buscar/{cod_comida}")
    public Comida findComida (@PathVariable Long cod_comida) {
        
        return iComiSer.findComida(cod_comida);
        
    }
    
    @PutMapping("/editar")
    public Comida editComida (@RequestBody Comida comida) {
        return iComiSer.editComida(comida);
    }
    
}
