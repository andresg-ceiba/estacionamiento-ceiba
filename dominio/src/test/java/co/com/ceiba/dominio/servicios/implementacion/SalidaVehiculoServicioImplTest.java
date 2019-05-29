package co.com.ceiba.dominio.servicios.implementacion;

import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.servicios.SalidaVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalidaVehiculoServicioImplTest {

    @Mock
    private VehiculoRepositorio vehiculoRepositorio;

    @Mock
    private ProveedorTiempo proveedorTiempo;

    @InjectMocks
    private SalidaVehiculoServicioImpl salidaVehiculoServicio;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void salidaCarroAntesNueveHorasTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 15, 4, 0);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(Double.valueOf(4000d), salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaCarroDespuesNueveHorasTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(Double.valueOf(11000d), salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoAntesNueveHorasTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 15, 4, 0);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(Double.valueOf(2000d), salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoDespuesNueveHorasTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo().withTipo(TipoVehiculo.MOTO);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(Double.valueOf(5500d), salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoAltoCilindrajeTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withTipo(TipoVehiculo.MOTO)
                .withCilindraje(TipoVehiculo.MOTO.getUmbralAltoCilindraje() + 1);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(Double.valueOf(7500d), salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }
}