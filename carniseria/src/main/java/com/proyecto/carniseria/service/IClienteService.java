
package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    //crear
    public List<Cliente> getCliente();
    
    //crear
    public String saveCliente(Cliente cli);
    
    //eliminar
    public void deleteCliente(Long id_cli);
    
    //buscar
    public Cliente findCliente(Long id_cli);
    
    ///editar
    public Cliente editCliente(Cliente cli);
    
    //el guardar para la logica de IMDISPONIBLE de repartidor
    public void saveCliGlobal(Cliente cli);
    
    //para cancelar el pepido
    public String cancelarPedido(Long id_cli);
    
}
