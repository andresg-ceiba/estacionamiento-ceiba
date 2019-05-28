package co.com.ceiba.dominio.vehiculo;


import co.com.ceiba.dominio.comun.ValidadorArgumentos;
import lombok.Value;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@Value
@Wither
public class Vehiculo {

    private static final String PLACA_OBLIGATORIA = "La placa del vehículo es obligatoria";
    private static final String TIPO_VEHICULO_OBLIGATORIO = "El tipo del vehículo es obligatorio";
    private static final String HORA_INGRESO_OBLIGATORIA = "La hora de ingreso del vehículo es obligatoria";
    private static final String CILINDRAJE_OBLIGATORIO = "El cilindraje es obligatorio en un vehiculo de tipo moto";


    public static final Integer CAPACIDAD_MAXIMA_MOTO = 10;
    public static final Integer CAPACIDAD_MAXIMA_CARRO = 20;

    private final String placa;

    private final TipoVehiculo tipo;

    private final LocalDateTime horaIngreso;

    private final Integer cilindraje;

    public Vehiculo(String placa, TipoVehiculo tipo, LocalDateTime horaIngreso, Integer cilindraje) {

        validarArgumentosObligatorios(placa, tipo, horaIngreso, cilindraje);

        this.placa = placa;
        this.tipo = tipo;
        this.horaIngreso = horaIngreso;
        this.cilindraje = cilindraje;
    }

    private void validarArgumentosObligatorios(String placa, TipoVehiculo tipoVehiculo, LocalDateTime horaIngreso, Integer cilindraje) {
        ValidadorArgumentos.validarObligatorio(placa, PLACA_OBLIGATORIA);
        ValidadorArgumentos.validarObligatorio(tipoVehiculo, TIPO_VEHICULO_OBLIGATORIO);
        ValidadorArgumentos.validarObligatorio(horaIngreso, HORA_INGRESO_OBLIGATORIA);

        if (tipoVehiculo == TipoVehiculo.MOTO) {
            ValidadorArgumentos.validarObligatorio(cilindraje, CILINDRAJE_OBLIGATORIO);
        }

    }
}
