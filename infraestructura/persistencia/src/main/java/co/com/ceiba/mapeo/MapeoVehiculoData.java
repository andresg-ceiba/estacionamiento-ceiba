package co.com.ceiba.mapeo;

import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;

public class MapeoVehiculoData {

    FabricaVehiculo fabricaVehiculo;

    public Vehiculo aEntidad(VehiculoData vehiculoData) {
        return fabricaVehiculo.crearVehiculo(
                vehiculoData.getPlaca(),
                vehiculoData.getTipo(),
                vehiculoData.getHoraIngreso(),
                vehiculoData.getCilindraje());
    }

    public VehiculoData aData(Vehiculo vehiculo) {

        return VehiculoData.builder()
                .placa(vehiculo.getPlaca())
                .tipo(vehiculo.getTipo().name())
                .horaIngreso(vehiculo.getHoraIngreso())
                .cilindraje(vehiculo.getCilindraje()).build();
    }

}
