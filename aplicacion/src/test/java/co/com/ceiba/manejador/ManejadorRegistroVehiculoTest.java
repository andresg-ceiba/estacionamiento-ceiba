package co.com.ceiba.manejador;

import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ManejadorRegistroVehiculoTest {

    @Mock
    private RegistrarVehiculoServicio registrarVehiculoServicio;

    @Mock
    private FabricaVehiculo fabricaVehiculo;

    @Mock
    private ProveedorTiempo proveedorTiempo;

    @InjectMocks
    private ManejadorRegistroVehiculo manejadorRegistroVehiculo;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void ejecutar() {
        LocalDateTime horaTest = LocalDateTime.of(2019, 5, 26, 0, 0);

        Vehiculo vehiculoEntrante = VehiculoTestBuilder.unVehiculo().withHoraIngreso(horaTest);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaTest);

        when(fabricaVehiculo.crearVehiculo(
                vehiculoEntrante.getPlaca(),
                vehiculoEntrante.getTipo().name(),
                vehiculoEntrante.getHoraIngreso(),
                vehiculoEntrante.getCilindraje()))
                .thenReturn(vehiculoEntrante);

        when(registrarVehiculoServicio.registrarVehiculo(vehiculoEntrante)).thenReturn(vehiculoEntrante.getPlaca());


        assertEquals(vehiculoEntrante.getPlaca(),
                manejadorRegistroVehiculo.ejecutar(
                        vehiculoEntrante.getPlaca(),
                        vehiculoEntrante.getTipo().name(),
                        vehiculoEntrante.getCilindraje()));
    }
}