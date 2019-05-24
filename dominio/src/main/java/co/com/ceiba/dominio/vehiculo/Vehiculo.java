package co.com.ceiba.dominio.vehiculo;


import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Vehiculo {


    private final TipoVehiculo tipo;

    private final String placa;

    private final LocalDateTime horaIngreso;
}
