package co.com.ceiba;

import co.com.ceiba.dto.RegistroVehiculoDTO;
import co.com.ceiba.dto.VehiculoDTO;
import co.com.ceiba.manejador.ManejadorConsultaVehiculos;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import co.com.ceiba.manejador.ManejadorSalidaVehiculo;
import co.com.ceiba.mapeo.MapeoVehiculoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vehiculos")
@CrossOrigin
public class ControladorVehiculos {

    private final MapeoVehiculoDto mapeoVehiculoDto;
    private final ManejadorRegistroVehiculo manejadorRegistroVehiculo;
    private final ManejadorConsultaVehiculos manejadorConsultaVehiculos;
    private final ManejadorSalidaVehiculo manejadorSalidaVehiculo;

    @Autowired
    public ControladorVehiculos(MapeoVehiculoDto mapeoVehiculoDto,
                                ManejadorRegistroVehiculo manejadorRegistroVehiculo,
                                ManejadorConsultaVehiculos manejadorConsultaVehiculos,
                                ManejadorSalidaVehiculo manejadorSalidaVehiculo) {
        this.mapeoVehiculoDto = mapeoVehiculoDto;
        this.manejadorRegistroVehiculo = manejadorRegistroVehiculo;
        this.manejadorConsultaVehiculos = manejadorConsultaVehiculos;
        this.manejadorSalidaVehiculo = manejadorSalidaVehiculo;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehiculoDTO> consultarTodosLosVehiculos() {


        return manejadorConsultaVehiculos.ejecutar().stream()
                .map(mapeoVehiculoDto::aDto)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/registrar", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public VehiculoDTO registrarVehiculo(@RequestBody RegistroVehiculoDTO registroVehiculoDTO) {

        return mapeoVehiculoDto.aDto(
                manejadorRegistroVehiculo.ejecutar(
                        registroVehiculoDTO.getPlaca(),
                        registroVehiculoDTO.getTipoVehiculo(),
                        registroVehiculoDTO.getCilindraje()));
    }

    @PostMapping(value = "/{placa}/salir", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehiculoDTO salidaVehiculo(@PathVariable(name = "placa") String placa) {

        return mapeoVehiculoDto.aDto(manejadorSalidaVehiculo.ejecutar(placa));
    }
}
