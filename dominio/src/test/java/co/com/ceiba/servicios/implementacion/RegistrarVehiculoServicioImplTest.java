package co.com.ceiba.servicios.implementacion;


import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrarVehiculoServicioImplTest {

    private static final Integer CANTIDAD_CARRO_PERMITIDA = 10;
    private static final Integer CANTIDAD_MOTO_PERMITIDA = 5;
    private static final String PLACA_NO_PERMITIDA = "ABC123";

    @Mock
    private VehiculoRepositorio repositorioVehiculos;


    private RegistrarVehiculoServicioImpl servicio;

    @BeforeEach
    public void setUp() {

        servicio = new RegistrarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Test
    public void registrarVehiculoCorrectoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(CANTIDAD_CARRO_PERMITIDA);

        when(repositorioVehiculos.registrar(vehiculo)).thenReturn(vehiculo);

        Assertions.assertEquals(vehiculo.getPlaca(),
                servicio.registrarVehiculo(vehiculo));

    }

    @Test
    public void registrarVehiculoMotoCorrectoTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(CANTIDAD_MOTO_PERMITIDA);


        when(repositorioVehiculos.registrar(moto)).thenReturn(moto);

        Assertions.assertEquals(moto.getPlaca(), servicio.registrarVehiculo(moto));

    }


    @Test
    public void capacidadCarroExcedidaDebeFallarTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_CARRO);

        Assertions.assertThrows(ExcepcionOperacionNoPermitida.class, () -> {
            servicio.registrarVehiculo(vehiculo);
        }, "No pueden ingresar mas de 20 carros");
    }

    @Test
    public void capacidadMotoExcedidaDebeFallarTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_MOTO);

        Assertions.assertThrows(ExcepcionOperacionNoPermitida.class, () -> {
            servicio.registrarVehiculo(moto);
        }, "No pueden ingresar mas de 10 motos");
    }

    @Test
    public void placaNoPermitidaEntradaTest() {

        Vehiculo moto = VehiculoTestBuilder.unVehiculo().withPlaca(PLACA_NO_PERMITIDA);

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(CANTIDAD_CARRO_PERMITIDA);

        Assertions.assertThrows(ExcepcionOperacionNoPermitida.class, () -> {
            servicio.registrarVehiculo(moto);
        }, "Las placas iniciadas en 'A' solo pueden ingresar los días lunes y domingos");
    }

}