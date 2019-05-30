package co.com.ceiba.testDataBuilders;

import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;

import java.time.LocalDateTime;

public class VehiculoTestBuilder {

    private static final String PLACA_VEHICULO_TEST = "SBC123";
    private static final Integer CILINDRAJE_BAJO = 150;

    private static final LocalDateTime HORA_ENTRADA_TEST = LocalDateTime.of(2019, 5, 15, 0, 0);


    public static Vehiculo unVehiculo() {
        return new Vehiculo(PLACA_VEHICULO_TEST, TipoVehiculo.CARRO, HORA_ENTRADA_TEST, CILINDRAJE_BAJO,null,null);
    }


}
