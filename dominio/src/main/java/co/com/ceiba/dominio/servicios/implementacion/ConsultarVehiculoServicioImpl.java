package co.com.ceiba.dominio.servicios.implementacion;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionSinDatos;
import co.com.ceiba.dominio.servicios.ConsultarVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;

import java.util.List;


public class ConsultarVehiculoServicioImpl implements ConsultarVehiculoServicio {

    private static final String ESTACIONAMIENTO_VACIO = "No hay vehiculos en el estacionamiento";

    private final VehiculoRepositorio repositorioVehiculos;

    public ConsultarVehiculoServicioImpl(VehiculoRepositorio repositorioVehiculos) {
        this.repositorioVehiculos = repositorioVehiculos;
    }

    public List<Vehiculo> consultarTodos() {

        List<Vehiculo> vehiculosIngresados = repositorioVehiculos.consultarTodos();

        if (vehiculosIngresados.isEmpty()) {
            throw new ExcepcionSinDatos(ESTACIONAMIENTO_VACIO);
        }

        return vehiculosIngresados;
    }
}
