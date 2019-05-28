package co.com.ceiba.dominio.vehiculo;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class VehiculoTest {

    @Test
    void vehiculoSinPlacaDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertThrows(ExcepcionValorObligatorio.class,
                () -> new Vehiculo(
                        null,
                        vehiculo.getTipo(),
                        vehiculo.getHoraIngreso(),
                        vehiculo.getCilindraje()),
                "La placa del vehículo es obligatoria");
    }

    @Test
    void vehiculoSinTipoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertThrows(ExcepcionValorObligatorio.class,
                () -> new Vehiculo(
                        vehiculo.getPlaca(),
                        null,
                        vehiculo.getHoraIngreso(),
                        vehiculo.getCilindraje()),
                "El tipo del vehículo es obligatorio");
    }

    @Test
    void vehiculoSinHoraDeIngresoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertThrows(ExcepcionValorObligatorio.class,
                () -> new Vehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo(),
                        null,
                        vehiculo.getCilindraje()),
                "La hora de ingreso del vehículo es obligatoria");
    }

    @Test
    void motoSinCilindrajeDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        assertThrows(ExcepcionValorObligatorio.class,
                () -> new Vehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo(),
                        vehiculo.getHoraIngreso(),
                        null),
                "El cilindraje es obligatorio en un vehiculo de tipo moto");
    }
}