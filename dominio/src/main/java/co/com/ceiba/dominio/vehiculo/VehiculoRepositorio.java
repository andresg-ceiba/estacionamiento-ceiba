package co.com.ceiba.dominio.vehiculo;

import java.util.List;

public interface VehiculoRepositorio {

    /**
     * Permite crear un usuario
     * @param vehiculo
     * @return el id generado
     */
    public Long registrar(Vehiculo vehiculo);

    /**
     * Permite eliminar un vehiculo
     * @param id
     */
    public void eliminar(Long id);

    /**
     * Permite consultar todos los vehiculos
     */
    public List<Vehiculo> consultarTodos();
}
