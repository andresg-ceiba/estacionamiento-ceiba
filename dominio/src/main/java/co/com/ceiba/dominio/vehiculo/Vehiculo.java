package co.com.ceiba.dominio.vehiculo;


import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@Value
@Wither
public class Vehiculo {

    public static final Integer CAPACIDAD_MAXIMA_MOTO = 10;
    public static final Integer CAPACIDAD_MAXIMA_CARRO = 20;

    private final String placa;

    private final TipoVehiculo tipo;

    private final LocalDateTime horaIngreso;

    private final Integer cilindraje;
}
