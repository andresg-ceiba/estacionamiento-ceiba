package co.com.ceiba.dominio.comun;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;

public class ProveedorTiempoTest {


    private ProveedorTiempo proveedorTiempo;

    @Before
    public void setUp() throws Exception {
        Clock reloj = Clock.fixed(Instant.parse("2019-05-26T00:00:00.00Z"),ZoneId.of("UTC"));

        proveedorTiempo = new ProveedorTiempo(reloj);
    }

    @Test
    public void obtenerHoraActual() {
        LocalDateTime horaTest= LocalDateTime.of(2019,5,26,0,0);

        assertEquals(horaTest,proveedorTiempo.obtenerHoraActual());

    }
}