package co.com.ceiba.dominio.vehiculo;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VehiculoTest {

    @Test
    public void vehiculoSinPlacaDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        try {
            new Vehiculo(
                    null,
                    vehiculo.getTipo(),
                    vehiculo.getHoraIngreso(),
                    vehiculo.getCilindraje());
        } catch (ExcepcionValorObligatorio ex) {
            assertEquals("La placa del vehículo es obligatoria", ex.getMessage());
        }

    }

    @Test
    public void vehiculoSinTipoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();


        try {
            new Vehiculo(
                    vehiculo.getPlaca(),
                    null,
                    vehiculo.getHoraIngreso(),
                    vehiculo.getCilindraje());
        } catch (ExcepcionValorObligatorio ex) {
            assertEquals("El tipo del vehículo es obligatorio", ex.getMessage());
        }

    }

    @Test
    public void vehiculoSinHoraDeIngresoDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        try {
            new Vehiculo(
                    vehiculo.getPlaca(),
                    vehiculo.getTipo(),
                    null,
                    vehiculo.getCilindraje());
        } catch (ExcepcionValorObligatorio ex) {
            assertEquals("La hora de ingreso del vehículo es obligatoria", ex.getMessage());
        }

    }

    @Test
    public void motoSinCilindrajeDebeFallarTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        try {
            new Vehiculo(
                    vehiculo.getPlaca(),
                    vehiculo.getTipo(),
                    vehiculo.getHoraIngreso(),
                    null);
        } catch (ExcepcionValorObligatorio ex) {
            assertEquals("El cilindraje es obligatorio en un vehiculo de tipo moto", ex.getMessage());
        }

    }
}