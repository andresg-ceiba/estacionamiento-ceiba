package co.com.ceiba.servicios;

import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.comun.ValidadorArgumentos;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class RegistrarVehiculoServicio {

    private static final String PLACA_OBLIGATORIA = "La placa del vehículo es obligatoria";
    private static final String TIPO_OBLIGATORIO = "La placa es obligatoria";
    private static final String TIPO_INVALIDO = "El tipo de vehiculo no está permitido";
    private static final String ENTRADA_NO_PERMITIDA = "Las placas iniciadas en 'A' solo pueden ingresar los días lunes y domingos";

    private final ValidadorArgumentos validador;

    private final ProveedorTiempo proveedorTiempo;

    private final VehiculoRepositorio repositorioVehiculos;


    public Long ejecutar(String placa, String tipoVehiculo) {

        validador.validarObligatorio(placa, PLACA_OBLIGATORIA);
        validador.validarObligatorio(tipoVehiculo, TIPO_OBLIGATORIO);
        validador.validarValorEnumValido(tipoVehiculo, TipoVehiculo.class, TIPO_INVALIDO);

        LocalDateTime horaEntrada = proveedorTiempo.obtenerHoraActual();

        validarEntradaPermitida(placa, horaEntrada);

        Vehiculo vehiculoEntrante = new Vehiculo(TipoVehiculo.valueOf(tipoVehiculo), placa, horaEntrada);

        return repositorioVehiculos.registrar(vehiculoEntrante);
    }

    private void validarEntradaPermitida(String placa, LocalDateTime horaEntrada) {
        if (placa.charAt(0) == 'A'
                && !(horaEntrada.getDayOfWeek().equals(DayOfWeek.SUNDAY) || horaEntrada.getDayOfWeek().equals(DayOfWeek.MONDAY))) {

            throw new ExcepcionOperacionNoPermitida(ENTRADA_NO_PERMITIDA);
        }
    }
}
