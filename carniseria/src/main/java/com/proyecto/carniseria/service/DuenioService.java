package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cajero;
import com.proyecto.carniseria.model.Duenio;
import com.proyecto.carniseria.repository.IDuenioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuenioService implements IDuenioService {

    @Autowired
    private IDuenioRepository iDueRepo;

    @Autowired
    private ICajeroService iCajSer;

    @Override
    public List<Duenio> getDuenios() {

        List<Duenio> listaDuenios = iDueRepo.findAll();
        return listaDuenios;

    }

    @Override
    public void saveDuenio(Duenio duenio) {

        iDueRepo.save(duenio);

    }

    @Override
    public void deleteDuenio(Long id_duenio) {
        iDueRepo.deleteById(id_duenio);
    }

    @Override
    public Duenio findDuenio(Long id_duenio) {

        return iDueRepo.findById(id_duenio).orElse(null);

    }

    @Override
    public Duenio editDuenio(Duenio duenio) {

        this.saveDuenio(duenio);
        return duenio;
    }

    @Override
    public String pagarCajero() {

        List<Cajero> listaCajeros = iCajSer.getCajeros();
        Duenio due = this.findDuenio(1L);

        for (Cajero caje : listaCajeros) {

            double sueldoCaje = caje.getSueldo_deCajero();
            sueldoCaje = sueldoCaje + 100;
            caje.setSueldo_deCajero(sueldoCaje);
            iCajSer.saveCajero(caje);
            
            double sueldoDuenio = due.getDinero();

            sueldoDuenio = sueldoDuenio - 100;
            due.setDinero(sueldoDuenio);
            this.saveDuenio(due);

        }

        String mensaje = "Se pago a todos con exito";

        return mensaje;

    }

}
