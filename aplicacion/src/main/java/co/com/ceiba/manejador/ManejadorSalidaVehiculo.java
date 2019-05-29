package co.com.ceiba.manejador;

import co.com.ceiba.dominio.servicios.SalidaVehiculoServicio;

public class ManejadorSalidaVehiculo {

    private final SalidaVehiculoServicio salidaVehiculoServicio;

    public ManejadorSalidaVehiculo(SalidaVehiculoServicio salidaVehiculoServicio) {
        this.salidaVehiculoServicio = salidaVehiculoServicio;
    }

    public Double ejecutar(String placa) {
        return salidaVehiculoServicio.salidaVehiculo(placa);
    }
}
