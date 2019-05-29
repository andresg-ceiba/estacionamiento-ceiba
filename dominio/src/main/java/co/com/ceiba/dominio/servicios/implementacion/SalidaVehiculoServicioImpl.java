package co.com.ceiba.dominio.servicios.implementacion;

import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionOperacionNoPermitida;
import co.com.ceiba.dominio.servicios.SalidaVehiculoServicio;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SalidaVehiculoServicioImpl implements SalidaVehiculoServicio {

    private static final String ELIMINAR_VEHICULO_EXISTENTE_IMPOSIBLE = "No es posible registrar salida para un vehiculo no ingresado";

    private final VehiculoRepositorio vehiculoRepositorio;

    private final ProveedorTiempo proveedorTiempo;

    public SalidaVehiculoServicioImpl(VehiculoRepositorio vehiculoRepositorio, ProveedorTiempo proveedorTiempo) {
        this.vehiculoRepositorio = vehiculoRepositorio;
        this.proveedorTiempo = proveedorTiempo;
    }

    @Override
    public Double salidaVehiculo(String placa) {

        validarVehiculoRegistrado(placa);

        Vehiculo vehiculoSaliente = vehiculoRepositorio.consultarPorPlaca(placa);


        vehiculoRepositorio.eliminar(vehiculoSaliente);

        return calcularCosto(vehiculoSaliente);
    }

    private void validarVehiculoRegistrado(String placa) {
        if (!vehiculoRepositorio.existe(placa)) {
            throw new ExcepcionOperacionNoPermitida(ELIMINAR_VEHICULO_EXISTENTE_IMPOSIBLE);
        }
    }

    private Double calcularCosto(Vehiculo vehiculoSaliente) {

        LocalDateTime horaDeSalida = proveedorTiempo.obtenerHoraActual();

        Long minutosFaltantes = vehiculoSaliente.getHoraIngreso().until(horaDeSalida, ChronoUnit.MINUTES);

        Double minutosFaltantesEnHoras = minutosFaltantes.doubleValue() / 60d;

        Double horasFaltantes = Math.ceil(minutosFaltantesEnHoras);

        Double costoBase = descomponerCosto(vehiculoSaliente, horasFaltantes);

        if (vehiculoSaliente.getTipo() == TipoVehiculo.MOTO &&
                vehiculoSaliente.getCilindraje() > vehiculoSaliente.getTipo().getUmbralAltoCilindraje()) {

            return costoBase + vehiculoSaliente.getTipo().getSobreCostoUmbral();
        }
        return costoBase;
    }

    private Double descomponerCosto(Vehiculo vehiculoSaliente, Double horasFaltantes) {
        if (horasFaltantes <= 0) {
            return 0d;
        }
        if (horasFaltantes > Vehiculo.HORAS_LIMITE_DIA) {
            return vehiculoSaliente.getTipo().getCostoDia()
                    + descomponerCosto(vehiculoSaliente, horasFaltantes - Vehiculo.HORAS_POR_DIA);
        } else {
            return (vehiculoSaliente.getTipo().getCostoHora() * horasFaltantes);
        }


    }
}
