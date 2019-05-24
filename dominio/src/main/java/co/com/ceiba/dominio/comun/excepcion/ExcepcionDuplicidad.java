package co.com.ceiba.dominio.comun.excepcion;

public class ExcepcionDuplicidad extends RuntimeException {

    public ExcepcionDuplicidad(String mensaje) {
        super(mensaje);
    }
}
