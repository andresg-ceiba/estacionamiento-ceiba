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

    public static final Long HORAS_LIMITE_DIA = 9L;
    public static final Long HORAS_POR_DIA = 24L;

    private final String placa;

    private final TipoVehiculo tipo;

    private final LocalDateTime horaIngreso;

    private final Integer cilindraje;

    private final LocalDateTime horaSalida;

    private final Double costoEstacionamiento;

    public Vehiculo(String placa, TipoVehiculo tipo, LocalDateTime horaIngreso, Integer cilindraje, LocalDateTime horaSalida, Double costoEstacionamiento) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaIngreso = horaIngreso;
        this.cilindraje = cilindraje;
        this.horaSalida = horaSalida;
        this.costoEstacionamiento = costoEstacionamiento;
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
