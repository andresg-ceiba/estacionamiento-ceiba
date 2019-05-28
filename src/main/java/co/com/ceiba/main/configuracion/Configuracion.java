package co.com.ceiba.main.configuracion;


import co.com.ceiba.adaptador.VehiculoRepositorioAdaptador;
import co.com.ceiba.dominio.comun.ProveedorTiempo;
import co.com.ceiba.dominio.vehiculo.FabricaVehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.manejador.ManejadorRegistroVehiculo;
import co.com.ceiba.mapeo.MapeoVehiculoData;
import co.com.ceiba.repositorio.VehiculoDataRepositorio;
import co.com.ceiba.servicios.RegistrarVehiculoServicio;
import co.com.ceiba.servicios.implementacion.RegistrarVehiculoServicioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {

//    @Bean
//    public FabricaVehiculo crearFabricaVehiculo() {
//        return new FabricaVehiculo();
//    }
//
//
//    @Bean
//    public ProveedorTiempo crearProveedorTiempo() {
//        return new ProveedorTiempo();
//    }
//
//    @Bean
//    public VehiculoRepositorio crearVehiculoRepositorio(VehiculoDataRepositorio repositorio, MapeoVehiculoData mapeoVehiculoData) {
//        return new VehiculoRepositorioAdaptador(repositorio, mapeoVehiculoData);
//    }
//
//    @Bean
//    public RegistrarVehiculoServicio crearRegistrarVehiculoServicio(VehiculoRepositorio repositorioVehiculos) {
//
//        return new RegistrarVehiculoServicioImpl(repositorioVehiculos);
//    }
//
//    @Bean
//    public ManejadorRegistroVehiculo crearManejadorRegistroVehiculo(RegistrarVehiculoServicio registrarVehiculoServicio,
//                                                                    FabricaVehiculo fabricaVehiculo,
//                                                                    ProveedorTiempo proveedorTiempo) {
//        return new ManejadorRegistroVehiculo(registrarVehiculoServicio, fabricaVehiculo, proveedorTiempo);
//    }
}
