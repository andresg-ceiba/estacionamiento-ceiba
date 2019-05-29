package co.com.ceiba.configuracion;


import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicio;
import co.com.ceiba.dominio.servicios.RegistrarVehiculoServicioImpl;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import co.com.ceiba.mapeo.MapeoVehiculoData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

    @Bean
    public FabricaVehiculo crearFabricaVehiculo() {
        return new FabricaVehiculo();
    }


    @Bean
    public ProveedorTiempo crearProveedorTiempo() {
        return new ProveedorTiempo();
    }

    @Bean
    public MapeoVehiculoData crearMapeoVehiculoData() {
        return new MapeoVehiculoData();
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
}
