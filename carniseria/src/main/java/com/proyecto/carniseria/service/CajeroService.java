package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cajero;
import com.proyecto.carniseria.model.Duenio;
import com.proyecto.carniseria.repository.ICajeroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CajeroService implements ICajeroService {

    @Autowired
    private ICajeroRepository iCajRepo;

    @Autowired
    private IDuenioService iDueSer;

    @Override
    public List<Cajero> getCajeros() {

        List<Cajero> listaCajeros = iCajRepo.findAll();
        return listaCajeros;
    }

    @Override
    public void saveCajero(Cajero cajero) {

        iCajRepo.save(cajero);

    }

    @Override
    public void deleteCajero(Long id_cajero) {

        iCajRepo.deleteById(id_cajero);

    }

    @Override
    public Cajero findCajero(Long id_cajero) {
        return iCajRepo.findById(id_cajero).orElse(null);
    }

    @Override
    public Cajero editCajero(Cajero cajero) {

        this.saveCajero(cajero);
        return cajero;

    }

    @Override
    public String darDineJefe(Long id_cajero) {

        String mensaje;

        Cajero caje = this.findCajero(id_cajero);
        if (caje.getTotal_recaudado() != 0) {
            double totalAEntregar = caje.getTotal_recaudado();

            Duenio due = iDueSer.findDuenio(1L);

            due.setDinero(totalAEntregar);
            iDueSer.saveDuenio(due);

            caje.setTotal_recaudado(0);
            this.saveCajero(caje);
            mensaje = "se deposito correctamente";

        }else {
            mensaje = "El cojero no tiene nada q depositar";
        }
        
        return mensaje;
    }

}
