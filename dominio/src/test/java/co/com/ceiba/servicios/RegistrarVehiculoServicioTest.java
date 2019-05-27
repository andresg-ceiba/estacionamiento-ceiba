package co.com.ceiba.servicios;


import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.comun.ValidadorArgumentos;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;
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

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(vehiculo.getHoraIngreso());

        when(repositorioVehiculos.registrar(any(Vehiculo.class))).thenReturn(vehiculo);

        Assertions.assertEquals(vehiculo.getPlaca(), servicio.ejecutar(vehiculo.getPlaca(), vehiculo.getTipo().name()));

    }

    @Test
    public void placaObligatoriaTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            servicio.ejecutar(null, vehiculo.getTipo().name());
        }, "La placa del vehículo es obligatoria");

    }

    @Test
    public void tipoVehiculoObligatorioTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorObligatorio.class, () -> {
            servicio.ejecutar(vehiculo.getPlaca(), null);
        }, "El tipo de vehiculo es obligatorio");
    }

    @Test
    public void debeSerTipoVehiculoValidoTest() {

        Vehiculo vehiculo = VehiculoTestBuilder.unVehiculo();

        Assertions.assertThrows(ExcepcionValorInvalido.class, () -> {
            servicio.ejecutar(vehiculo.getPlaca(), TIPO_NO_PERMITIDO);
        }, "El tipo de vehiculo no está permitido");
    }

}