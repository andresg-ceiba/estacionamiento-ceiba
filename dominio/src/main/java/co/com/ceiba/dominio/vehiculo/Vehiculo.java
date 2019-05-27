package co.com.ceiba.dominio.vehiculo;


import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@Value
@Wither
public class Vehiculo {

    private final String placa;

    private final TipoVehiculo tipo;

    private final LocalDateTime horaIngreso;
}
