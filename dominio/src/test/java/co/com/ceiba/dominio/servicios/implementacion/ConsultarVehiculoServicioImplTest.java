package co.com.ceiba.dominio.servicios.implementacion;

import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsultarVehiculoServicioImplTest {

    @Mock
    private VehiculoRepositorio repositorioVehiculos;

    private ConsultarVehiculoServicioImpl servicio;

    @Before
    public void setUp() {

        servicio = new ConsultarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Test
    public void consultarTodosTest() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        List<Vehiculo> listaVehiculos = new ArrayList<>(Arrays.asList(vehiculo, vehiculo, vehiculo));

        when(repositorioVehiculos.consultarTodos()).thenReturn(listaVehiculos);

        assertEquals(listaVehiculos, servicio.consultarTodos());

    }
}