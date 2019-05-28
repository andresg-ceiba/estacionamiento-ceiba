package co.com.ceiba.manejador;

import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.servicios.RegistrarVehiculoServicio;

public class ManejadorRegistroVehiculo {

    private final RegistrarVehiculoServicio registrarVehiculoServicio;
    private final FabricaVehiculo fabricaVehiculo;
    private final ProveedorTiempo proveedorTiempo;

    public ManejadorRegistroVehiculo(RegistrarVehiculoServicio registrarVehiculoServicio, FabricaVehiculo fabricaVehiculo, ProveedorTiempo proveedorTiempo) {
        this.registrarVehiculoServicio = registrarVehiculoServicio;
        this.fabricaVehiculo = fabricaVehiculo;
        this.proveedorTiempo = proveedorTiempo;
    }

    public String ejecutar(String placa, String tipoVehiculo, Integer cilindraje) {
        Vehiculo vehiculoEntrante =
                fabricaVehiculo.crearVehiculo(
                        placa,
                        tipoVehiculo,
                        proveedorTiempo.obtenerHoraActual(),
                        cilindraje);

        return registrarVehiculoServicio.registrarVehiculo(vehiculoEntrante);
    }
}
