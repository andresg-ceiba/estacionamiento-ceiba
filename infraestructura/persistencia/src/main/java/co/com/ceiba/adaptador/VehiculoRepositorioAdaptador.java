package co.com.ceiba.adaptador;

import co.com.ceiba.repositorio.VehiculoDataRepositorio;
import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.mapeo.MapeoVehiculoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehiculoRepositorioAdaptador implements VehiculoRepositorio {


    private final VehiculoDataRepositorio repositorio;

    private final MapeoVehiculoData mapeoVehiculoData;

    @Autowired
    public VehiculoRepositorioAdaptador(VehiculoDataRepositorio repositorio, MapeoVehiculoData mapeoVehiculoData) {
        this.repositorio = repositorio;
        this.mapeoVehiculoData = mapeoVehiculoData;
    }


    @Override
    public Vehiculo registrar(Vehiculo vehiculo) {

        VehiculoData vehiculoDataEntrante = mapeoVehiculoData.aData(vehiculo);

        return mapeoVehiculoData.aEntidad(repositorio.save(vehiculoDataEntrante));
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Vehiculo> consultarTodos() {
        return null;
    }

    @Override
    public Integer consultarCantidadMotos() {
        return null;
    }

    @Override
    public Integer consultarCantidadCarros() {
        return null;
    }
}
