package co.com.ceiba.servicios;


import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.comun.ValidadorArgumentos;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrarVehiculoServicioTest {

    private static final String TIPO_NO_PERMITIDO = "TRICICLO";
    private static final Integer CANTIDAD_CARRO_PERMITIDA = 10;
    private static final Integer CANTIDAD_MOTO_PERMITIDA = 5;

    private ValidadorArgumentos validadorArgumentos = new ValidadorArgumentos();

    @Mock
    private ProveedorTiempo proveedorTiempo;

    @Mock
    private VehiculoRepositorio repositorioVehiculos;


    private RegistrarVehiculoServicio servicio;

    @BeforeEach
    public void setUp() {

        servicio = new RegistrarVehiculoServicio(validadorArgumentos, proveedorTiempo, repositorioVehiculos);
    }

    @Test
    public void registrarVehiculoCorrectoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(CANTIDAD_CARRO_PERMITIDA);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(vehiculo.getHoraIngreso());

        when(repositorioVehiculos.registrar(any(Vehiculo.class))).thenReturn(vehiculo);

        Assertions.assertEquals(vehiculo.getPlaca(),
                servicio.registrarVehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo().name(),
                        vehiculo.getCilindraje()));

    }

    @Test
    public void registrarVehiculoMotoCorrectoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(CANTIDAD_MOTO_PERMITIDA);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(vehiculo.getHoraIngreso());

        when(repositorioVehiculos.registrar(any(Vehiculo.class))).thenReturn(vehiculo);

        Assertions.assertEquals(vehiculo.getPlaca(),
                servicio.registrarVehiculo(
                        vehiculo.getPlaca(),
                        vehiculo.getTipo().name(),
                        vehiculo.getCilindraje()));

    }


    @Test
    public void placaObligatoriaTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            servicio.registrarVehiculo(null, vehiculo.getTipo().name(), vehiculo.getCilindraje());
        }, "La placa del vehículo es obligatoria");

    }

    @Test
    public void tipoVehiculoObligatorioTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            servicio.registrarVehiculo(vehiculo.getPlaca(), null, vehiculo.getCilindraje());
        }, "El tipo de vehiculo es obligatorio");
    }

    @Test
    public void debeSerTipoVehiculoValidoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorInvalido.class, () -> {
            servicio.registrarVehiculo(vehiculo.getPlaca(), TIPO_NO_PERMITIDO, vehiculo.getCilindraje());
        }, "El tipo de vehiculo no está permitido");
    }

    @Test
    public void capacidadCarroExcedidaDebeFallarTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        when(repositorioVehiculos.consultarCantidadCarros()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_CARRO);

        Assertions.assertThrows(ExcepcionOperacionNoPermitida.class, () -> {
            servicio.registrarVehiculo(vehiculo.getPlaca(), vehiculo.getTipo().name(), vehiculo.getCilindraje());
        }, "No pueden ingresar mas de 20 carros");
    }

    @Test
    public void capacidadMotoExcedidaDebeFallarTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        when(repositorioVehiculos.consultarCantidadMotos()).thenReturn(Vehiculo.CAPACIDAD_MAXIMA_MOTO);

        Assertions.assertThrows(ExcepcionOperacionNoPermitida.class, () -> {
            servicio.registrarVehiculo(vehiculo.getPlaca(), vehiculo.getTipo().name(), vehiculo.getCilindraje());
        }, "No pueden ingresar mas de 10 motos");
    }

}