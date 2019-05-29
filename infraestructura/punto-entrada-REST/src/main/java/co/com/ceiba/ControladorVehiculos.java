package co.com.ceiba;

import co.com.ceiba.DTO.RegistroVehiculoDTO;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.manejador.ManejadorConsultaVehiculos;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import co.com.ceiba.manejador.ManejadorSalidaVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping(value = "/vehiculos")
public class ControladorVehiculos {

    private final ManejadorRegistroVehiculo manejadorRegistroVehiculo;
    private final ManejadorConsultaVehiculos manejadorConsultaVehiculos;
    private final ManejadorSalidaVehiculo manejadorSalidaVehiculo;

    @Autowired
    public ControladorVehiculos(ManejadorRegistroVehiculo manejadorRegistroVehiculo,
                                ManejadorConsultaVehiculos manejadorConsultaVehiculos,
                                ManejadorSalidaVehiculo manejadorSalidaVehiculo) {
        this.manejadorRegistroVehiculo = manejadorRegistroVehiculo;
        this.manejadorConsultaVehiculos = manejadorConsultaVehiculos;
        this.manejadorSalidaVehiculo = manejadorSalidaVehiculo;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehiculo> consultarTodosLosVehiculos() {
        return manejadorConsultaVehiculos.ejecutar();
    }

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces =MediaType.APPLICATION_JSON_UTF8_VALUE )
    public String registrarVehiculo(@RequestBody RegistroVehiculoDTO registroVehiculoDTO) {

        return manejadorRegistroVehiculo.ejecutar(
                registroVehiculoDTO.getPlaca(),
                registroVehiculoDTO.getTipoVehiculo(),
                registroVehiculoDTO.getCilindraje());
    }

    @PostMapping(value = "/{placa}/salir",produces = MediaType.TEXT_PLAIN_VALUE)
    public Double salidaVehiculo(@PathVariable(name = "placa") String placa) {

        return manejadorSalidaVehiculo.ejecutar(placa);
    }
}
