package co.com.ceiba.dominio.vehiculo;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FabricaVehiculoTest {

    private static final String TIPO_NO_VALIDO = "TRICICLO";

    private FabricaVehiculo fabricaVehiculo = new FabricaVehiculo();

    @Test
    void crearVehiculoCorrectamenteTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertEquals(vehiculo,
                fabricaVehiculo.crearVehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo().name(),
                        vehiculo.getHoraIngreso(),
                        vehiculo.getCilindraje()));
    }

    @Test
    void enumNoValidoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertThrows(ExcepcionValorInvalido.class, () ->
                fabricaVehiculo.crearVehiculo(
                        vehiculo.getPlaca(),
                        TIPO_NO_VALIDO,
                        vehiculo.getHoraIngreso(),
                        vehiculo.getCilindraje()), "El tipo de vehiculo no es válido");
    }
}