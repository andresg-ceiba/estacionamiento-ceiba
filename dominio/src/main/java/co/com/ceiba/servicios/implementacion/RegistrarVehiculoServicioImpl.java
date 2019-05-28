package co.com.ceiba.servicios.implementacion;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.servicios.RegistrarVehiculoServicio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class RegistrarVehiculoServicioImpl implements RegistrarVehiculoServicio {

    private static final String ENTRADA_NO_PERMITIDA = "Las placas iniciadas en 'A' solo pueden ingresar los días lunes y domingos";
    private static final String CAPACIDAD_CARRO_EXCEDIDA = "No pueden ingresar mas de 20 carros";
    private static final String CAPACIDAD_MOTO_EXCEDIDA = "No pueden ingresar mas de 10 motos";

    private final VehiculoRepositorio repositorioVehiculos;

    public RegistrarVehiculoServicioImpl(VehiculoRepositorio repositorioVehiculos) {
        this.repositorioVehiculos = repositorioVehiculos;
    }

    public String registrarVehiculo(Vehiculo vehiculoEntrante) {

        validarCapacidadEntrada(vehiculoEntrante.getTipo());

        validarEntradaPermitida(vehiculoEntrante.getPlaca(), vehiculoEntrante.getHoraIngreso());

        return repositorioVehiculos.registrar(vehiculoEntrante).getPlaca();
    }


    private void validarCapacidadEntrada(TipoVehiculo tipoVehiculo) {

        switch (tipoVehiculo) {
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


    private void validarEntradaPermitida(String placa, LocalDateTime horaEntrada) {
        if (placa.charAt(0) == 'A'
                && !(horaEntrada.getDayOfWeek().equals(DayOfWeek.SUNDAY) || horaEntrada.getDayOfWeek()
                .equals(DayOfWeek.MONDAY))) {

            throw new ExcepcionOperacionNoPermitida(ENTRADA_NO_PERMITIDA);
        }
    }
}
