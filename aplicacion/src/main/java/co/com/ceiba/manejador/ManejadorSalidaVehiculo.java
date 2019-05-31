package co.com.ceiba.manejador;

import co.com.ceiba.dominio.servicios.SalidaVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.Vehiculo;

public class ManejadorSalidaVehiculo {

    private final SalidaVehiculoServicio salidaVehiculoServicio;

    public ManejadorSalidaVehiculo(SalidaVehiculoServicio salidaVehiculoServicio) {
        this.salidaVehiculoServicio = salidaVehiculoServicio;
    }

    public Vehiculo ejecutar(String placa) {
        return salidaVehiculoServicio.salidaVehiculo(placa);
    }
}
