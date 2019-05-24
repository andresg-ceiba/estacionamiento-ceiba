package co.com.ceiba.dominio.comun;

import java.time.LocalDateTime;

public class ProveedorTiempo {

    public LocalDateTime obtenerHoraActual(){
        return LocalDateTime.now();
    }
}
