package com.proyecto.carniseria.service;

import com.proyecto.carniseria.model.Cajero;
import com.proyecto.carniseria.model.Cliente;
import com.proyecto.carniseria.model.Comida;
import com.proyecto.carniseria.model.Repartidor;
import com.proyecto.carniseria.repository.IClienteRepository;
import com.proyecto.carniseria.repository.IComidaRepository;
import com.proyecto.carniseria.repository.IRepartidorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iCliRepo;

    @Autowired
    private IRepartidorRepository iRepRepo;
    
    @Autowired
    private IRepartidorService iRepSer;

    @Autowired
    private IComidaRepository iComiRepo;

    @Autowired
    private ICajeroService iCajSer;

    @Override
    public List<Cliente> getCliente() {

        List<Cliente> listaClientes = iCliRepo.findAll();
        return listaClientes;

    }

    @Override
    public String saveCliente(Cliente cli) {

        List<Cliente> listaClientes = this.getCliente();
        //double pagoComi = 0;
        double regresoDeDinero;
        double pagoComida = 0.0;

        boolean exisOno = false;

        for (Cliente cli2 : listaClientes) {
            Cliente client = this.findCliente(cli2.getId());

            if (client.getNombre().equals(cli.getNombre())) {
                if (client.getDirreccion().equals(cli.getDirreccion())) {
                    exisOno = true;
                }
            }
        }

        String mensaje = "";

        //hacemos esto para sber si tiene el presupuesto para pedir comidas
        List<Integer> listaNumComidas = cli.getListaComida();
        List<Comida> listaComida = iComiRepo.findAll();

        for (Integer integer : listaNumComidas) {
            int cod_comi = integer;

            for (Comida comi : listaComida) {
                if (cod_comi == comi.getCodigo_comida()) {
                    if (comi.isDisponible() == true) {
                        pagoComida = pagoComida + comi.getPrecio();
                    }
                }
            }
        }

        for (Cliente cli2 : listaClientes) {
            Cliente client = this.findCliente(cli2.getId());

            if (exisOno == false) {

                if (!client.getNombre().equals(cli.getNombre())) {
                    if (!client.getDirreccion().equals(cli.getDirreccion())) {

                        regresoDeDinero = cli.getSueldo();

                        if (regresoDeDinero > pagoComida) {

                            System.out.println("lego awjow");
                            pedirComida(cli, mensaje, pagoComida);
                            regresoDeDinero = regresoDeDinero - pagoComida;
                            cli.setSueldo(regresoDeDinero);
                            cli.setAtendido("sin_atender");
                            iCliRepo.save(cli);

                            break;
                        } else {
                            mensaje = mensaje + "no tiene dinero suficiente";
                        }

                    } else {
                        regresoDeDinero = cli.getSueldo();

                        //para saber si tiene sueldo sufuciente para comprar
                        if (regresoDeDinero > pagoComida) {
                            System.out.println("tambien aca");
                            pedirComida(cli, mensaje, pagoComida);
                            regresoDeDinero = regresoDeDinero - pagoComida;
                            cli.setSueldo(regresoDeDinero);
                            cli.setAtendido("sin_atender");

                            iCliRepo.save(cli);
                            break;
                        } else {
                            mensaje = mensaje + " no tiene dinero suficiente ";
                        }
                    }
                }
            } else {

                System.out.println("llege aca");
                if (client.getNombre().equals(cli.getNombre())) {
                    if (client.getDirreccion().equals(cli.getDirreccion())) {

                        regresoDeDinero = client.getSueldo();

                        //para saber si tiene sueldo sufuciente para comprar
                        if (regresoDeDinero > pagoComida) {
                            pedirComida(client, mensaje, pagoComida);
                            regresoDeDinero = regresoDeDinero - pagoComida;
                            client.setSueldo(regresoDeDinero);
                            client.setAtendido("sin_atender");

                            iCliRepo.save(client);
                            break;
                        } else {
                            mensaje = mensaje + " no tiene dinero suficiente ";
                        }

                    } else {

                        regresoDeDinero = client.getSueldo();

                        if (regresoDeDinero > pagoComida) {
                            pedirComida(client, mensaje, pagoComida);
                            regresoDeDinero = regresoDeDinero - pagoComida;
                            client.setSueldo(regresoDeDinero);
                            cli.setAtendido("sin_atender");

                            iCliRepo.save(client);
                            break;
                        } else {
                            mensaje = mensaje + " no tiene dinero suficiente ";
                        }

                    }
                }
            }
        }

        return mensaje;
    }

    @Override
    public void deleteCliente(Long id_cli) {
        iCliRepo.deleteById(id_cli);

    }

    @Override
    public Cliente findCliente(Long id_cli) {

        return iCliRepo.findById(id_cli).orElse(null);

    }

    @Override
    public Cliente editCliente(Cliente cli) {

        this.saveCliente(cli);
        return cli;

    }

    @Override
    public void saveCliGlobal(Cliente cli) {

        iCliRepo.save(cli);
    }

    //para la logica de reservar el repartidor y cobrar la comida q pidio para no hacer tanto codigo en el save
    public void pedirComida(Cliente cli, String mensaje, double pagoComida) {

        List<Repartidor> listaRepartidores = iRepRepo.findAll();

        for (Repartidor repart : listaRepartidores) {
            if (repart.getEstado().equalsIgnoreCase("disponible")) {
                repart.setEstado("en curso");
                repart.setLocalizacion_del_cliente("Nombre : " + cli.getNombre() + " Direccion: " + cli.getDirreccion());
                cli.setRepartidor(repart);
                break;
            }
        }

        List<Integer> listaNumComidas = cli.getListaComida();
        List<Comida> listaComida = iComiRepo.findAll();
        double pagoComi;

        for (Integer integer : listaNumComidas) {
            int cod_comi = integer;

            for (Comida comi : listaComida) {
                if (cod_comi == comi.getCodigo_comida()) {
                    if (comi.isDisponible() == true) {
                        pagoComida = pagoComida + comi.getPrecio();

                        int cant = comi.getCantidad();

                        cant = cant - 1;
                        comi.setCantidad(cant);

                        if (comi.getCantidad() == 0) {
                            comi.setDisponible(false);
                        }
                    } else {
                        mensaje = "un tipo de carne q pidio no esta disponible, no hay!";
                    }
                }
            }

        }

        Cajero cajero = iCajSer.findCajero(1L);
        pagoComi = cajero.getTotal_recaudado();
        pagoComi = pagoComi + pagoComida;

        cajero.setTotal_recaudado(pagoComi);

    }

    @Override
    public String cancelarPedido(Long id_cli) {

        Cliente cli = this.findCliente(id_cli);
        double pagoComida = 0;
        
        Long id_repar = cli.getRepartidor().getId();
        
        Repartidor repar = iRepSer.findRepartidor(id_repar);
        
        repar.setEstado("disponible");
        repar.setLocalizacion_del_cliente("no se sabe aun");
        iRepSer.saveRepartidor(repar);

        cli.setAtendido("cancelado");
        cli.setRepartidor(null);

        this.saveCliGlobal(cli);

        List<Integer> listaNumComidas = cli.getListaComida();
        List<Comida> listaComida = iComiRepo.findAll();

        for (Integer integer : listaNumComidas) {
            int cod_comi = integer;

            for (Comida comi : listaComida) {
                if (cod_comi == comi.getCodigo_comida()) {
                    if (comi.isDisponible() == true) {
                        pagoComida = pagoComida + comi.getPrecio();
                    }
                }
            }
        }
        
        Cajero caje = iCajSer.findCajero(1L);

        double dineCaje = caje.getTotal_recaudado();
        
        dineCaje = dineCaje - pagoComida;
        
        caje.setTotal_recaudado(dineCaje);
        iCajSer.saveCajero(caje);
        
        double dineClient = cli.getSueldo();
        dineClient = dineClient + pagoComida;
        cli.setSueldo(dineClient);
        this.saveCliGlobal(cli);
        
        String mensaje = "Se cancelo correctamente";
        return mensaje;
    }

}
