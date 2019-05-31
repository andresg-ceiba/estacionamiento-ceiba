package co.com.ceiba.dominio.servicios.implementacion;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionDuplicidad;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


public class RegistrarVehiculoServicioImpl implements RegistrarVehiculoServicio {

    private static final String ENTRADA_NO_PERMITIDA = "Las placas iniciadas en 'A' solo pueden ingresar los dias lunes y domingos";
    private static final String VEHICULO_YA_INGRESADO = "El vehiculo ya ha sido ingresado anteriormente";
    private static final String CAPACIDAD_CARRO_EXCEDIDA = "No pueden ingresar mas de 20 carros";
    private static final String CAPACIDAD_MOTO_EXCEDIDA = "No pueden ingresar mas de 10 motos";

    private final VehiculoRepositorio repositorioVehiculos;

    public RegistrarVehiculoServicioImpl(VehiculoRepositorio repositorioVehiculos) {
        this.repositorioVehiculos = repositorioVehiculos;
    }

    public Vehiculo registrarVehiculo(Vehiculo vehiculoEntrante) {

        validarVehiculoYaIngresado(vehiculoEntrante);

        validarCapacidadEntrada(vehiculoEntrante.getTipo());

        validarEntradaPermitida(vehiculoEntrante.getPlaca(), vehiculoEntrante.getHoraIngreso());

        return repositorioVehiculos.registrar(vehiculoEntrante);
    }


    private void validarVehiculoYaIngresado(Vehiculo vehiculoEntrante) {
        if (repositorioVehiculos.existe(vehiculoEntrante.getPlaca())) {
            throw new ExcepcionDuplicidad(VEHICULO_YA_INGRESADO);

        }
    }

    private void validarCapacidadEntrada(TipoVehiculo tipoVehiculo) {

        switch (tipoVehiculo) {
            case CARRO:
                if (repositorioVehiculos.consultarCantidadCarros() >= tipoVehiculo.getCapacidadMaxima()) {
                    throw new ExcepcionOperacionNoPermitida(CAPACIDAD_CARRO_EXCEDIDA);
                }
                break;
            case MOTO:
                if (repositorioVehiculos.consultarCantidadMotos() >= tipoVehiculo.getCapacidadMaxima()) {
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
