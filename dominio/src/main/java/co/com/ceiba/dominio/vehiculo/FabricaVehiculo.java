package co.com.ceiba.dominio.vehiculo;

import co.com.ceiba.dominio.comun.ValidadorArgumentos;

import java.time.LocalDateTime;

public class FabricaVehiculo {

    private static final String TIPO_INVALIDO = "El tipo de vehiculo no es válido";

    public Vehiculo crearVehiculo(String placa, String tipoVehiculo, LocalDateTime horaIngreso, Integer cilindraje) {

        ValidadorArgumentos.validarValorEnumValido(tipoVehiculo, TipoVehiculo.class, TIPO_INVALIDO);

        return new Vehiculo(placa, TipoVehiculo.valueOf(tipoVehiculo), horaIngreso, cilindraje);
    }
}
