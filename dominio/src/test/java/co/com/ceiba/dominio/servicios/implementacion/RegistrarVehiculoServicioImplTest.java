package co.com.ceiba.dominio.servicios.implementacion;


import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicioImpl;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrarVehiculoServicioImplTest {

    private static final Integer CANTIDAD_CARRO_PERMITIDA = 10;
    private static final Integer CANTIDAD_MOTO_PERMITIDA = 5;
    private static final String PLACA_NO_PERMITIDA = "ABC123";

    @Mock
    private VehiculoRepositorio repositorioVehiculos;


    private RegistrarVehiculoServicioImpl servicio;

    @Before
    public void setUp() {

        servicio = new RegistrarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Test
    public void registrarVehiculoCorrectoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(CANTIDAD_CARRO_PERMITIDA);

        when(repositorioVehiculos.registrar(vehiculo)).thenReturn(vehiculo);

        assertEquals(vehiculo.getPlaca(),
                servicio.registrarVehiculo(vehiculo));

    }

    @Test
    public void registrarVehiculoMotoCorrectoTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(CANTIDAD_MOTO_PERMITIDA);


        when(repositorioVehiculos.registrar(moto)).thenReturn(moto);

        assertEquals(moto.getPlaca(), servicio.registrarVehiculo(moto));

    }


    @Test
    public void capacidadCarroExcedidaDebeFallarTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_CARRO);

        try {
            servicio.registrarVehiculo(vehiculo);
        } catch (ExcepcionOperacionNoPermitida ex) {
            assertEquals("No pueden ingresar mas de 20 carros", ex.getMessage());
        }
    }

    @Test
    public void capacidadMotoExcedidaDebeFallarTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_MOTO);

        try {
            servicio.registrarVehiculo(moto);
        } catch (ExcepcionOperacionNoPermitida ex) {
            assertEquals("No pueden ingresar mas de 10 motos", ex.getMessage());
        }


    }

    @Test
    public void placaNoPermitidaEntradaTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withPlaca(PLACA_NO_PERMITIDA);

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(CANTIDAD_CARRO_PERMITIDA);


        try {
            servicio.registrarVehiculo(moto);
        } catch (ExcepcionOperacionNoPermitida ex) {
            assertEquals("Las placas iniciadas en 'A' solo pueden ingresar los días lunes y domingos", ex.getMessage());
        }

    }

}