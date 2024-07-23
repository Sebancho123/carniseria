
package com.proyecto.carniseria.controller;

import com.proyecto.carniseria.model.Cliente;
import com.proyecto.carniseria.service.IClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private IClienteService iCliSer;
    
    @GetMapping("/traerlos")
    public List<Cliente> getCliente() {
        return iCliSer.getCliente();
    }
    
    @PostMapping("/crear")
    public String saveCliente(@RequestBody Cliente cli) {
        
        return iCliSer.saveCliente(cli);
    }
    
    @DeleteMapping("/eliminar/{id_cli}")
    public String deleteCliente(@PathVariable Long id_cli) {
        iCliSer.deleteCliente(id_cli);
        return "se elimino corretamente";
    }
    
    @GetMapping("/buscar/{id_cli}")
    public Cliente findCliente(@PathVariable Long id_cli) {
        return iCliSer.findCliente(id_cli);
    }
    
    @PutMapping("/editar")
    public Cliente editCliente(@RequestBody Cliente cli) {
     return iCliSer.editCliente(cli);
    }
    
    @GetMapping("/cancelar/pedido/{id_cli}")
    public String cancelarPedido(@PathVariable Long id_cli) {
        
        return iCliSer.cancelarPedido(id_cli);
        
    }
    
}
