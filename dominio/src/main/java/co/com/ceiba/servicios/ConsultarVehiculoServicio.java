package co.com.ceiba.servicios;

import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ConsultarVehiculoServicio {

    private final VehiculoRepositorio repositorioVehiculos;

    public List<Vehiculo> consultarTodos(){

        return repositorioVehiculos.consultarTodos();
    }
}
