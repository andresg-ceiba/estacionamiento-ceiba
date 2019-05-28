package co.com.ceiba.repositorio;

import co.com.ceiba.data.VehiculoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoDataRepositorio extends MongoRepository<VehiculoData, String> {
}
