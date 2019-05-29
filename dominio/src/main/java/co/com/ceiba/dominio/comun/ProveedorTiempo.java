package co.com.ceiba.dominio.comun;

import java.time.Clock;
import java.time.LocalDateTime;

public class ProveedorTiempo {

    private final Clock reloj;

    public ProveedorTiempo(Clock reloj) {
        this.reloj = reloj;
    }

    public LocalDateTime obtenerHoraActual() {
        return LocalDateTime.now(reloj);
    }
}
