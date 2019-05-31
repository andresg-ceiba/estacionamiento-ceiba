package co.com.ceiba.integracion;

import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dto.VehiculoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegracionSalida {

    private static final String BASE_PLACA_TEST = "TST4";
    private static final Integer CILINDRAJE_TEST = 150;
    private static final LocalDateTime HORA_INGRESO_TEST = LocalDateTime.of(2019, 5, 28, 0, 0);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoOperations operations;

    @MockBean
    private ProveedorTiempo proveedorTiempo;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mapper.registerModule(new JavaTimeModule());
    }

    @After
    public void tearDown() throws Exception {
        operations.dropCollection(VehiculoData.class);
    }

    @Test
    public void salidaDeCarroDespues9Horas() throws Exception {
        VehiculoData vehiculoData = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.CARRO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(CILINDRAJE_TEST)
                .build();

        operations.save(vehiculoData);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 5, 29, 3, 0);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(BASE_PLACA_TEST + "01",
                TipoVehiculo.CARRO,
                HORA_INGRESO_TEST,
                CILINDRAJE_TEST,
                horaSalida,
                11000d);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        MvcResult result = mockMvc.perform(post("/vehiculos/TST401/salir"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

        mockMvc.perform(post("/vehiculos/TST401/salida"))
                .andExpect(status().isNotFound());

    }


    @Test
    public void salidaDeCarroAntes9Horas() throws Exception {
        VehiculoData vehiculoData = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.CARRO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(CILINDRAJE_TEST)
                .build();

        operations.save(vehiculoData);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 5, 28, 3, 0);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(BASE_PLACA_TEST + "01",
                TipoVehiculo.CARRO,
                HORA_INGRESO_TEST,
                CILINDRAJE_TEST,
                horaSalida,
                3000d);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        MvcResult result = mockMvc.perform(post("/vehiculos/TST401/salir"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

        mockMvc.perform(post("/vehiculos/TST401/salida"))
                .andExpect(status().isNotFound());

    }


    @Test
    public void salidaDeMotoDespues9Horas() throws Exception {
        VehiculoData vehiculoData = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.MOTO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(CILINDRAJE_TEST)
                .build();

        operations.save(vehiculoData);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 5, 28, 10, 0);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(BASE_PLACA_TEST + "01",
                TipoVehiculo.MOTO,
                HORA_INGRESO_TEST,
                CILINDRAJE_TEST,
                horaSalida,
                4000d);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        MvcResult result = mockMvc.perform(post("/vehiculos/TST401/salir"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

        mockMvc.perform(post("/vehiculos/TST401/salida"))
                .andExpect(status().isNotFound());

    }


    @Test
    public void salidaDeMotoAntes9Horas() throws Exception {
        VehiculoData vehiculoData = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.MOTO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(CILINDRAJE_TEST)
                .build();

        operations.save(vehiculoData);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 5, 28, 3, 0);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(BASE_PLACA_TEST + "01",
                TipoVehiculo.MOTO,
                HORA_INGRESO_TEST,
                CILINDRAJE_TEST,
                horaSalida,
                1500d);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        MvcResult result = mockMvc.perform(post("/vehiculos/TST401/salir"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

        mockMvc.perform(post("/vehiculos/TST401/salida"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void salidaDeMotoAltoCilindraje() throws Exception {
        VehiculoData vehiculoData = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.MOTO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(TipoVehiculo.MOTO.getUmbralAltoCilindraje() + 1)
                .build();

        operations.save(vehiculoData);

        LocalDateTime horaSalida = LocalDateTime.of(2019, 5, 28, 3, 0);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(BASE_PLACA_TEST + "01",
                TipoVehiculo.MOTO,
                HORA_INGRESO_TEST,
                TipoVehiculo.MOTO.getUmbralAltoCilindraje() + 1,
                horaSalida,
                3500d);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaSalida);

        MvcResult result = mockMvc.perform(post("/vehiculos/TST401/salir"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

        mockMvc.perform(post("/vehiculos/TST401/salida"))
                .andExpect(status().isNotFound());

    }
}
