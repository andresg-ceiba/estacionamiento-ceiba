package co.com.ceiba.dominio.vehiculo;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FabricaVehiculoTest {

    private static final String TIPO_NO_VALIDO = "TRICICLO";

    private FabricaVehiculo fabricaVehiculo = new FabricaVehiculo();

    @Test
    public void crearVehiculoCorrectamenteTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        assertEquals(vehiculo,
                fabricaVehiculo.crearVehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo().name(),
                        vehiculo.getHoraIngreso(),
                        vehiculo.getCilindraje()));
    }

    @Test
    public void enumNoValidoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        try {
            fabricaVehiculo.crearVehiculo(
                    vehiculo.getPlaca(),
                    TIPO_NO_VALIDO,
                    vehiculo.getHoraIngreso(),
                    vehiculo.getCilindraje());
        } catch (ExcepcionValorInvalido ex) {
            assertEquals("El tipo de vehiculo no es válido", ex.getMessage());
        }

    }
}