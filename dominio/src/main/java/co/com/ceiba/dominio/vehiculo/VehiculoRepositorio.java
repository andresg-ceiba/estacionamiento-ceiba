package co.com.ceiba.dominio.vehiculo;

import java.util.List;

public interface VehiculoRepositorio {

    Vehiculo consultarPorPlaca(String placa);

    Vehiculo registrar(Vehiculo vehiculo);

    Boolean existe(String placa);

    void eliminar(Vehiculo vehiculo);

    List<Vehiculo> consultarTodos();

    Long consultarCantidadMotos();

    Long consultarCantidadCarros();
}
