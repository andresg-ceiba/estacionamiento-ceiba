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

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 15, 4, 0);

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withHoraSalida(horaSalida)
                .withCostoEstacionamiento(4000d);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(vehiculo, salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaCarroDespuesNueveHorasTest() {

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withHoraSalida(horaSalida)
                .withCostoEstacionamiento(11000d);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(vehiculo, salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoAntesNueveHorasTest() {


        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 15, 4, 0);

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withTipo(TipoVehiculo.MOTO)
                .withHoraSalida(horaSalida)
                .withCostoEstacionamiento(2000d);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(vehiculo, salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoDespuesNueveHorasTest() {

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withTipo(TipoVehiculo.MOTO)
                .withHoraSalida(horaSalida)
                .withCostoEstacionamiento(5500d);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(vehiculo, salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }

    @Test
    public void salidaMotoAltoCilindrajeTest() {

        LocalDateTime horaSalida = LocalDateTime.of(2019, 05, 16, 3, 0);

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo()
                .withTipo(TipoVehiculo.MOTO)
                .withCilindraje(TipoVehiculo.MOTO.getUmbralAltoCilindraje() + 1)
                .withHoraSalida(horaSalida)
                .withCostoEstacionamiento(7500d);

        when(vehiculoRepositorio.existe(vehiculo.getPlaca())).thenReturn(true);

        when(vehiculoRepositorio.consultarPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        assertEquals(vehiculo, salidaVehiculoServicio.salidaVehiculo(vehiculo.getPlaca()));
    }
}