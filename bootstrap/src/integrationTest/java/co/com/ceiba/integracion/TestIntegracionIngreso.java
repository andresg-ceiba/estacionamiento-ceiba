package co.com.ceiba.integracion;


import co.com.ceiba.configuracion.Configuracion;
import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dto.RegistroVehiculoDTO;
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
public class TestIntegracionIngreso {

    private static final String BASE_PLACA_TEST = "TST4";
    private static final String PLACA_CORRECTA = "OTC456";
    private static final String PLACA_NO_PERMITIDA = "ATC456";
    private static final Integer CILINDRAJE_TEST = 150;
    private static final LocalDateTime HORA_INGRESO_TEST = LocalDateTime.of(2019, 5, 28, 0, 0);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoOperations operations;

    @MockBean
    private ProveedorTiempo proveedorTiempo;

    private ProveedorTiempo proveedorTiempoReal;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mapper.registerModule(new JavaTimeModule());

        Configuracion configuracion = new Configuracion();

        proveedorTiempoReal = configuracion.crearProveedorTiempo();
    }

    @After
    public void tearDown() throws Exception {

        operations.dropCollection(VehiculoData.class);
    }


    @Test
    public void RegistrarVehiculoTest() throws Exception {

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_CORRECTA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(PLACA_CORRECTA,
                TipoVehiculo.MOTO,
                HORA_INGRESO_TEST,
                CILINDRAJE_TEST,
                null,
                null);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(HORA_INGRESO_TEST);

        MvcResult result = mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

    }

    @Test
    public void registrarVehiculoYaIngresadoTest() throws Exception {

        VehiculoData carro = VehiculoData.builder()
                .placa(BASE_PLACA_TEST + "01")
                .tipo(TipoVehiculo.CARRO.name())
                .horaIngreso(HORA_INGRESO_TEST)
                .cilindraje(CILINDRAJE_TEST)
                .build();

        operations.save(carro);


        when(proveedorTiempo.obtenerHoraActual()).thenReturn(proveedorTiempoReal.obtenerHoraActual());

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(BASE_PLACA_TEST + "01");
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);


        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isConflict())
                .andExpect(content().string("El vehiculo ya ha sido ingresado anteriormente"));

    }

    @Test
    public void excederCapacidadCarroTest() throws Exception {


        for (int i = 0; i < 21; i++) {
            VehiculoData carro = VehiculoData.builder()
                    .placa(BASE_PLACA_TEST + String.format("%02d", i))
                    .tipo(TipoVehiculo.CARRO.name())
                    .horaIngreso(HORA_INGRESO_TEST)
                    .cilindraje(CILINDRAJE_TEST)
                    .build();

            operations.save(carro);
        }

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_CORRECTA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.CARRO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(proveedorTiempoReal.obtenerHoraActual());

        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No pueden ingresar mas de 20 carros"));

    }

    @Test
    public void excederCapacidadMotoTest() throws Exception {

        for (int i = 0; i < 11; i++) {
            VehiculoData carro = VehiculoData.builder()
                    .placa(BASE_PLACA_TEST + String.format("%02d", i))
                    .tipo(TipoVehiculo.MOTO.name())
                    .horaIngreso(HORA_INGRESO_TEST)
                    .cilindraje(CILINDRAJE_TEST)
                    .build();

            operations.save(carro);
        }

        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_CORRECTA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(proveedorTiempoReal.obtenerHoraActual());

        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No pueden ingresar mas de 10 motos"));

    }


    @Test
    public void entradaPlacaIniciadaEnANoPermitidaEnSemanaTest() throws Exception {

        LocalDateTime horaDeingreso = LocalDateTime.of(2019, 5, 28, 0, 0);


        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_NO_PERMITIDA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaDeingreso);

        mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Las placas iniciadas en 'A' solo pueden ingresar los dias lunes y domingos"));

    }

    @Test
    public void entradaPlacaIniciadaEnAPermitidaDomingoTest() throws Exception {

        LocalDateTime horaDeingreso = LocalDateTime.of(2019, 5, 26, 0, 0);


        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_NO_PERMITIDA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);

        VehiculoDTO vehiculoEsperado = new VehiculoDTO(PLACA_NO_PERMITIDA,
                TipoVehiculo.MOTO,
                horaDeingreso,
                CILINDRAJE_TEST,
                null,
                null);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaDeingreso);

        MvcResult result = mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

    }

    @Test
    public void entradaPlacaIniciadaEnAPermitidaLunesTest() throws Exception {

        LocalDateTime horaDeingreso = LocalDateTime.of(2019, 5, 27, 0, 0);


        RegistroVehiculoDTO registroVehiculoDTO = new RegistroVehiculoDTO();
        registroVehiculoDTO.setPlaca(PLACA_NO_PERMITIDA);
        registroVehiculoDTO.setTipoVehiculo(TipoVehiculo.MOTO.name());
        registroVehiculoDTO.setCilindraje(CILINDRAJE_TEST);


        VehiculoDTO vehiculoEsperado = new VehiculoDTO(PLACA_NO_PERMITIDA,
                TipoVehiculo.MOTO,
                horaDeingreso,
                CILINDRAJE_TEST,
                null,
                null);

        when(proveedorTiempo.obtenerHoraActual()).thenReturn(horaDeingreso);

        MvcResult result = mockMvc.perform(post("/vehiculos/registrar")
                .content(mapper.writeValueAsString(registroVehiculoDTO))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        VehiculoDTO vehiculoObtenido = mapper.readValue(result.getResponse().getContentAsString(), VehiculoDTO.class);

        assertEquals(vehiculoEsperado, vehiculoObtenido);

    }
}
