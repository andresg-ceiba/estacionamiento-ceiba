package co.com.ceiba.dominio.comun.excepcion;

public class ExcepcionSinDatos extends RuntimeException {


    public ExcepcionSinDatos(String message) {
        super(message);
    }
}
