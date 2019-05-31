package co.com.ceiba.integracion;

import co.com.ceiba.data.VehiculoData;
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
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegracionConsulta {

    private static final String BASE_PLACA_TEST = "TST4";
    private static final Integer CILINDRAJE_TEST = 150;
    private static final LocalDateTime HORA_INGRESO_TEST = LocalDateTime.of(2019, 5, 28, 0, 0);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoOperations operations;

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @After
    public void tearDown() throws Exception {
        operations.dropCollection(VehiculoData.class);
    }


    @Test
    public void consultarTodosLosVehiculosTest() throws Exception {

        List<VehiculoDTO> listaVehiculos = new ArrayList<>();

        for (int i = 0; i < 21; i++) {
            VehiculoData vehiculoData = VehiculoData.builder()
                    .placa(BASE_PLACA_TEST + String.format("%02d", i))
                    .tipo(TipoVehiculo.CARRO.name())
                    .horaIngreso(HORA_INGRESO_TEST)
                    .cilindraje(CILINDRAJE_TEST)
                    .build();

            VehiculoDTO vehiculoDTO = new VehiculoDTO(
                    BASE_PLACA_TEST + String.format("%02d", i),
                    TipoVehiculo.CARRO,
                    HORA_INGRESO_TEST,
                    CILINDRAJE_TEST,
                    null,
                    null);

            listaVehiculos.add(vehiculoDTO);

            operations.save(vehiculoData);
        }


        MvcResult result = mockMvc.perform(get("/vehiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        List<VehiculoDTO> resultado = Arrays.asList(mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO[].class));

        assertEquals(listaVehiculos, resultado);
    }


    @Test
    public void consultarSinVehiculosTest() throws Exception {

        mockMvc.perform(get("/vehiculos"))
                .andExpect(status().isNotFound());

    }

}
