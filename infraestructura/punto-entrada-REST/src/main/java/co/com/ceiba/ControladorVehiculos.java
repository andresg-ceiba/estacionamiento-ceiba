package co.com.ceiba;

import co.com.ceiba.DTO.RegistroVehiculoDTO;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.manejador.ManejadorConsultaVehiculos;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehiculos")
public class ControladorVehiculos {

    private final ManejadorRegistroVehiculo manejadorRegistroVehiculo;
    private final ManejadorConsultaVehiculos manejadorConsultaVehiculos;

    @Autowired
    public ControladorVehiculos(ManejadorRegistroVehiculo manejadorRegistroVehiculo,
                                ManejadorConsultaVehiculos manejadorConsultaVehiculos) {
        this.manejadorRegistroVehiculo = manejadorRegistroVehiculo;
        this.manejadorConsultaVehiculos = manejadorConsultaVehiculos;
    }

    @GetMapping
    public List<Vehiculo> consultarTodosLosVehiculos() {
        return manejadorConsultaVehiculos.ejecutar();
    }

    @PostMapping(value = "/registrar")
    public String registrarVehiculo(@RequestBody RegistroVehiculoDTO registroVehiculoDTO) {

        return manejadorRegistroVehiculo.ejecutar(
                registroVehiculoDTO.getPlaca(),
                registroVehiculoDTO.getTipoVehiculo(),
                registroVehiculoDTO.getCilindraje());
    }
}
