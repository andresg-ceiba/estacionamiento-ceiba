package co.com.ceiba;

import co.com.ceiba.DTO.RegistroVehiculoDTO;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vehiculos")
public class ControladorVehiculos {

    private final ManejadorRegistroVehiculo manejadorRegistroVehiculo;

    public ControladorVehiculos(ManejadorRegistroVehiculo manejadorRegistroVehiculo) {
        this.manejadorRegistroVehiculo = manejadorRegistroVehiculo;
    }

    @PostMapping(value = "/registrar")
    public String registrarVehiculo(@RequestBody RegistroVehiculoDTO registroVehiculoDTO) {

        return manejadorRegistroVehiculo.ejecutar(
                registroVehiculoDTO.getPlaca(),
                registroVehiculoDTO.getTipoVehiculo(),
                registroVehiculoDTO.getCilindraje());
    }
}
