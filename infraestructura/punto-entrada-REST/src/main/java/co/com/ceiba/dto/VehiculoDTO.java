package co.com.ceiba.dto;

import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {

    private String placa;

    private TipoVehiculo tipo;

    private LocalDateTime horaIngreso;

    private Integer cilindraje;

    private LocalDateTime horaSalida;

    private Double costoEstacionamiento;
}
