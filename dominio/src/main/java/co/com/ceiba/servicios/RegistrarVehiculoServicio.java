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
    private static final String TIPO_OBLIGATORIO = "El tipo de vehiculo es obligatorio";
    private static final String TIPO_INVALIDO = "El tipo de vehiculo no está permitido";
    private static final String ENTRADA_NO_PERMITIDA = "Las placas iniciadas en 'A' solo pueden ingresar los días lunes y domingos";
    private static final String CAPACIDAD_CARRO_EXCEDIDA = "No pueden ingresar mas de 20 carros";
    private static final String CAPACIDAD_MOTO_EXCEDIDA = "No pueden ingresar mas de 10 motos";

    private final ValidadorArgumentos validador;

    private final ProveedorTiempo proveedorTiempo;

    private final VehiculoRepositorio repositorioVehiculos;


    public String registrarVehiculo(String placa, String tipoVehiculo, Integer cilindraje) {

        validarArgumentosEntrada(placa, tipoVehiculo);

        validarCapacidadEntrada(tipoVehiculo);

        LocalDateTime horaEntrada = proveedorTiempo.obtenerHoraActual();

        validarEntradaPermitida(placa, horaEntrada);

        Vehiculo vehiculoEntrante = construirVehiculoAIngresar(placa, tipoVehiculo, horaEntrada, cilindraje);

        return repositorioVehiculos.registrar(vehiculoEntrante).getPlaca();
    }


    private void validarArgumentosEntrada(String placa, String tipoVehiculo) {
        validador.validarObligatorio(placa, PLACA_OBLIGATORIA);
        validador.validarObligatorio(tipoVehiculo, TIPO_OBLIGATORIO);
        validador.validarValorEnumValido(tipoVehiculo, TipoVehiculo.class, TIPO_INVALIDO);
    }

    private void validarCapacidadEntrada(String tipoVehiculo) {

        switch (TipoVehiculo.valueOf(tipoVehiculo)) {
            case CARRO:
                if (repositorioVehiculos.consultarCantidadCarros() >= Vehiculo.CAPACIDAD_MAXIMA_CARRO) {
                    throw new ExcepcionOperacionNoPermitida(CAPACIDAD_CARRO_EXCEDIDA);
                }
                break;
            case MOTO:
                if (repositorioVehiculos.consultarCantidadMotos() >= Vehiculo.CAPACIDAD_MAXIMA_MOTO) {
                    throw new ExcepcionOperacionNoPermitida(CAPACIDAD_MOTO_EXCEDIDA);
                }
        }

    }

    private Vehiculo construirVehiculoAIngresar(String placa,
                                                String tipoVehiculo,
                                                LocalDateTime horaEntrada,
                                                Integer cilingraje) {

        return new Vehiculo(placa, TipoVehiculo.valueOf(tipoVehiculo), horaEntrada, cilingraje);

    }

    private void validarEntradaPermitida(String placa, LocalDateTime horaEntrada) {
        if (placa.charAt(0) == 'A'
                && !(horaEntrada.getDayOfWeek().equals(DayOfWeek.SUNDAY) || horaEntrada.getDayOfWeek()
                .equals(DayOfWeek.MONDAY))) {

            throw new ExcepcionOperacionNoPermitida(ENTRADA_NO_PERMITIDA);
        }
    }
}
