package co.com.ceiba.configuracion;


import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.servicios.ConsultarVehiculoServicio;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicio;
import co.com.ceiba.dominio.servicios.implementacion.ConsultarVehiculoServicioImpl;
import co.com.ceiba.dominio.servicios.implementacion.RegistrarVehiculoServicioImpl;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.manejador.ManejadorConsultaVehiculos;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import co.com.ceiba.mapeo.MapeoVehiculoData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class Configuracion {

    @Bean
    public FabricaVehiculo crearFabricaVehiculo() {
        return new FabricaVehiculo();
    }


    @Bean
    public ProveedorTiempo crearProveedorTiempo() {
        return new ProveedorTiempo(Clock.systemDefaultZone());
    }

    @Bean
    public MapeoVehiculoData crearMapeoVehiculoData(FabricaVehiculo fabricaVehiculo) {
        return new MapeoVehiculoData(fabricaVehiculo);
    }

    @Bean
    public RegistrarVehiculoServicio crearRegistrarVehiculoServicio(VehiculoRepositorio repositorioVehiculos) {

        return new RegistrarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Bean
    public ManejadorRegistroVehiculo crearManejadorRegistroVehiculo(RegistrarVehiculoServicio registrarVehiculoServicio,
                                                                    FabricaVehiculo fabricaVehiculo,
                                                                    ProveedorTiempo proveedorTiempo) {
        return new ManejadorRegistroVehiculo(registrarVehiculoServicio, fabricaVehiculo, proveedorTiempo);
    }

    @Bean
    public ConsultarVehiculoServicio crearConsultarVehiculoServicio(VehiculoRepositorio repositorioVehiculos) {

        return new ConsultarVehiculoServicioImpl(repositorioVehiculos);
    }

    @Bean
    public ManejadorConsultaVehiculos crearManejadorConsultaVehiculos(ConsultarVehiculoServicio consultarVehiculoServicio) {
        return new ManejadorConsultaVehiculos(consultarVehiculoServicio);
    }


}
