package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cliente;
import com.proyecto.carniseria.model.Repartidor;
import com.proyecto.carniseria.repository.IRepartidorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepartidorService implements IRepartidorService {

    @Autowired
    private IRepartidorRepository iRepRepo;

    @Autowired
    private IClienteService iCliSer;

    @Override
    public List<Repartidor> getRepartidor() {

        List<Repartidor> listaRepartidores = iRepRepo.findAll();
        return listaRepartidores;

    }

    @Override
    public void saveRepartidor(Repartidor repart) {

        iRepRepo.save(repart);

    }

    @Override
    public void deleteRepartidor(Long id_repart) {
        iRepRepo.deleteById(id_repart);
    }

    @Override
    public Repartidor findRepartidor(Long id_repart) {
        return iRepRepo.findById(id_repart).orElse(null);
    }

    @Override
    public Repartidor editRepartidor(Repartidor repart) {

        this.saveRepartidor(repart);
        return repart;

    }

    @Override
    public String ImDiponible(Long id_repart) {

        String mensaje = "";

        Repartidor repar = this.findRepartidor(id_repart);

        if (repar.getEstado().equalsIgnoreCase("en curso")) {
            if (!repar.getLocalizacion_del_cliente().equalsIgnoreCase("no se sabe aun")) {
                repar.setEstado("disponible");
                repar.setLocalizacion_del_cliente("no se sabe aun");
                this.saveRepartidor(repar);
            }
        } else {
            mensaje = mensaje + "no podemos desocupar por q el repartidor esta disponible";
        }

        List<Cliente> listaClientes = iCliSer.getCliente();

        for (Cliente cli : listaClientes) {
            if (cli.getAtendido().equalsIgnoreCase("sin_atender")) {

                Long idCliReparti = cli.getRepartidor().getId();
                
                if (cli.getRepartidor() != null) {

                    if (id_repart.equals(idCliReparti)) {

                        cli.setRepartidor(null);
                        cli.setAtendido("atendido");
                        iCliSer.saveCliGlobal(cli);

                        mensaje = mensaje + " " + "Se desocupo correctamente";
                    }
                } else {
                    mensaje = mensaje + "el repartidor ya esta disponible";
                }   

            }else {
                
            }
        }

        return mensaje;
    }

}
