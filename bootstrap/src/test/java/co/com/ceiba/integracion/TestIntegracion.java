package co.com.ceiba.integracion;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    private ObjectM

    public void testRegistrarVehiculo(){
        mockMvc.perform(post("/vehiculos/registrar").content(mapper));
    }
}
