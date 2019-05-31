package co.com.ceiba.dominio.comun;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;

import java.util.Arrays;

public final class ValidadorArgumentos {

    public static void validarObligatorio(Object valor, String mensaje) {
        if (valor == null) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

    public static <E> void validarValorEnumValido(String valor, Class<E> objetivo, String mensaje) {

        boolean valorEnumValido = Arrays.stream(objetivo.getEnumConstants())
                .anyMatch(valorEnum -> valorEnum.toString().equals(valor));

        if (!valorEnumValido) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }


}
