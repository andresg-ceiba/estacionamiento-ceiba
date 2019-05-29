package co.com.ceiba.manejador;

import co.com.ceiba.dominio.servicios.ConsultarVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.Vehiculo;

import java.util.List;

public class ManejadorConsultaVehiculos {

    private final ConsultarVehiculoServicio consultarVehiculoServicio;

    public ManejadorConsultaVehiculos(ConsultarVehiculoServicio consultarVehiculoServicio) {
        this.consultarVehiculoServicio = consultarVehiculoServicio;
    }

    public List<Vehiculo> ejecutar(){
        return consultarVehiculoServicio.consultarTodos();
    }
}
