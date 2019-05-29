package co.com.ceiba.dominio.comun.excepcion;

public class ExcepcionOperacionNoPermitida extends RuntimeException {

    public ExcepcionOperacionNoPermitida(String mensaje) {
        super(mensaje);
    }
}
