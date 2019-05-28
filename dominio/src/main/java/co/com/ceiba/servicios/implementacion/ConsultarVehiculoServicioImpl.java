package co.com.ceiba.servicios.implementacion;

import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.servicios.ConsultarVehiculoServicio;
import lombok.RequiredArgsConstructor;

import java.util.List;


public class ConsultarVehiculoServicioImpl implements ConsultarVehiculoServicio {

    private final VehiculoRepositorio repositorioVehiculos;

    public ConsultarVehiculoServicioImpl(VehiculoRepositorio repositorioVehiculos) {
        this.repositorioVehiculos = repositorioVehiculos;
    }

    public List<Vehiculo> consultarTodos(){

        return repositorioVehiculos.consultarTodos();
    }
}
