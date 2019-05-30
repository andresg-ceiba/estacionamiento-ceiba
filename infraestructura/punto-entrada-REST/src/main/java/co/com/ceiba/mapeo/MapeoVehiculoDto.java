package co.com.ceiba.mapeo;

import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dto.VehiculoDTO;

public class MapeoVehiculoDto {

    public VehiculoDTO aDto(Vehiculo vehiculo) {
        return new VehiculoDTO(vehiculo.getPlaca(),
                vehiculo.getTipo(),
                vehiculo.getHoraIngreso(),
                vehiculo.getCilindraje(),
                vehiculo.getHoraSalida(),
                vehiculo.getCostoEstacionamiento());
    }
}
