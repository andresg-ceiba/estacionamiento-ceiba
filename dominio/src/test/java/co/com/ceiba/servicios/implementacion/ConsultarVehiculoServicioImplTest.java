package co.com.ceiba.servicios.implementacion;

import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.testDataBuilders.VehiculoTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultarVehiculoServicioImplTest {

    @Mock
    private VehiculoRepositorio repositorioVehiculos;


    private ConsultarVehiculoServicioImpl servicio;

    @BeforeEach
    public void setUp() {

        servicio = new ConsultarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Test
    void consultarTodos() {
        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        List<Vehiculo> listaVehiculos = new ArrayList<>(Arrays.asList(vehiculo, vehiculo, vehiculo));

        when(repositorioVehiculos.consultarTodos()).thenReturn(listaVehiculos);

        assertEquals(listaVehiculos, servicio.consultarTodos());

    }
}