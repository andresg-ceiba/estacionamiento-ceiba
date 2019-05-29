package co.com.ceiba.integracion;


import co.com.ceiba.DTO.RegistroVehiculoDTO;
import co.com.ceiba.adaptador.VehiculoRepositorioAdaptador;
import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestIntegracion {

    private static final String PLACA_TEST = "TST4";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoOperations operations;

    private final List<VehiculoData> vehiculosDataTes = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        LocalDateTime horaDeingreso = LocalDateTime.of(2019, 5, 28, 0, 0);

        for (int i = 0; i < 9; i++) {
            VehiculoData carro = VehiculoData.builder()
                    .placa(PLACA_TEST + String.format("")i)
                    .tipo(TipoVehiculo.CARRO.name())
                    .horaIngreso(horaDeingreso)
                    .cilindraje(0)
                    .build();

            operations.save(carro);
        }

    }

    @After
    public void tearDown() throws Exception {

        operations.dropCollection(VehiculoData.class);
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void RegistrarVehiculoTest() throws Exception {

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca("OQB20C");
        registroVehiculoDTO.setTipoVehiculo("MOTO");
        registroVehiculoDTO.setCilindraje(150);


        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(registroVehiculoDTO.getPlaca()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void registrarVehiculoYaIngresadoTest() throws Exception {

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_TEST + 1);
        registroVehiculoDTO.setTipoVehiculo("MOTO");
        registroVehiculoDTO.setCilindraje(150);


        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isConflict())
                .andExpect(content().string("El vehiculo ya ha sido ingresado anteriormente"));

    }

    @Test
    public void excederCapacidadCarroTest() throws Exception {

        LocalDateTime horaDeingreso = LocalDateTime.of(2019, 5, 28, 0, 0);

        VehiculoData carro = VehiculoData.builder()
                .placa(PLACA_TEST + 9)
                .tipo(TipoVehiculo.CARRO.name())
                .horaIngreso(horaDeingreso)
                .cilindraje(0)
                .build();

        operations.save(carro);

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca("TST461");
        registroVehiculoDTO.setTipoVehiculo("CARRO");
        registroVehiculoDTO.setCilindraje(0);


        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No pueden ingresar mas de 20 carros"));

    }
}
