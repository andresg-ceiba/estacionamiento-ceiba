package co.com.ceiba.dominio.vehiculo;


import lombok.AllArgsConstructor;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@AllArgsConstructor
@Wither
public class Vehiculo {


    private final TipoVehiculo tipo;

    private final String placa;

    private final LocalDateTime horaIngreso;
}
