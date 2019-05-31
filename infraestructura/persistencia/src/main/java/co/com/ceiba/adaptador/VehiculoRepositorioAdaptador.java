package co.com.ceiba.adaptador;

import co.com.ceiba.data.VehiculoData;
import co.com.ceiba.dominio.vehiculo.TipoVehiculo;
import co.com.ceiba.dominio.vehiculo.Vehiculo;
import co.com.ceiba.dominio.vehiculo.VehiculoRepositorio;
import co.com.ceiba.mapeo.MapeoVehiculoData;
import co.com.ceiba.repositorio.VehiculoDataRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VehiculoRepositorioAdaptador implements VehiculoRepositorio {


    private final VehiculoDataRepositorio repositorio;

    private final MongoOperations operations;

    private final MapeoVehiculoData mapeoVehiculoData;

    @Autowired
    public VehiculoRepositorioAdaptador(VehiculoDataRepositorio repositorio, MongoOperations operations, MapeoVehiculoData mapeoVehiculoData) {
        this.repositorio = repositorio;
        this.operations = operations;
        this.mapeoVehiculoData = mapeoVehiculoData;
    }

    @Override
    public Vehiculo consultarPorPlaca(String placa) {

        return mapeoVehiculoData.aEntidad(
                operations.findOne(
                        Query.query(Criteria.where("_id").is(placa)), VehiculoData.class));
    }

    @Override
    public Vehiculo registrar(Vehiculo vehiculo) {

        VehiculoData vehiculoDataEntrante = mapeoVehiculoData.aData(vehiculo);

        return mapeoVehiculoData.aEntidad(repositorio.save(vehiculoDataEntrante));
    }

    @Override
    public Boolean existe(String placa) {
        return operations.exists(Query.query(Criteria.where("_id").is(placa)), VehiculoData.class);
    }


    @Override
    public void eliminar(Vehiculo vehiculo) {
        repositorio.delete(mapeoVehiculoData.aData(vehiculo));
    }

    @Override
    public List<Vehiculo> consultarTodos() {
        return repositorio.findAll()
                .stream()
                .map(mapeoVehiculoData::aEntidad)
                .collect(Collectors.toList());
    }

    @Override
    public Long consultarCantidadMotos() {

        return repositorio.countByTipo(TipoVehiculo.MOTO.name());
    }

    @Override
    public Long consultarCantidadCarros() {
        return repositorio.countByTipo(TipoVehiculo.CARRO.name());
    }
}
