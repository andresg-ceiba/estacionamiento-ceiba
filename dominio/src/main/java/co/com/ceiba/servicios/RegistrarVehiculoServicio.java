package co.com.ceiba.servicios;

import co.com.ceiba.dominio.comun.ValidadorArgumentos;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegistrarVehiculoServicio {

    private static final String PLACA_OBLIGATORIA = "La placa del vehículo es obligatoria";
    private static final String TIPO_OBLIGATORIO = "La placa es obligatoria";
    private static final String TIPO_INVALIDO = "El tipo de vehiculo no está permitido";

    private final ValidadorArgumentos validador;

    private final VehiculoRepositorio repositorioVehiculos;


    public Long ejecutar(String placa, String tipoVehiculo) {

        validador.validarObligatorio(placa, PLACA_OBLIGATORIA);
        validador.validarObligatorio(tipoVehiculo, TIPO_OBLIGATORIO);
        validador.validarValorEnumValido(tipoVehiculo, TipoVehiculo.class, TIPO_INVALIDO);

        repositorioVehiculos.registrar();

        return 1L;
    }

}
