package co.com.ceiba.dominio.comun;

import co.com.ceiba.dominio.comun.excepcion.ExcepcionLongitudValor;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorInvalido;
import co.com.ceiba.dominio.comun.excepcion.ExcepcionValorObligatorio;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorArgumentos {

    public void validarObligatorio(Object valor, String mensaje) {
        if (valor == null) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

    public void validarLongitud(String valor, int longitud, String mensaje) {
        if (valor.length() < longitud) {
            throw new ExcepcionLongitudValor(mensaje);
        }
    }

    public <T> void validarNoVacio(List<T> lista, String mensaje) {
        if (lista.isEmpty()) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

    public void validarPositivo(Double valor, String mensaje) {
        if (valor <= 0) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public void validarIgual(Double valor, Double valorEsperado, String mensaje) {
        if (!valor.equals(valorEsperado)) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public void validarLongitudMinima(Object valor, int longitudMinima, String mensaje) {
        if (valor.toString().length() < longitudMinima) {
            throw new ExcepcionLongitudValor(mensaje);
        }
    }

    public void validarMenor(LocalDateTime fechaInicial, LocalDateTime fechaFinal, String mensaje) {
        if (fechaInicial.toLocalDate().isAfter(fechaFinal.toLocalDate())) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public void validarMenor(Long numeroInicial, Long numeroFinal, String mensaje) {
        if (numeroInicial > numeroFinal) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public void validarRegex(String cadena, String regex, String mensaje) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);

        if (!matcher.matches()) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public <E> void validarValorEnumValido(String valor, Class<E> objetivo, String mensaje) {

        boolean valorEnumValido = Arrays.stream(objetivo.getEnumConstants())
                .anyMatch(valorEnum -> valorEnum.toString().equals(valor));

        if (!valorEnumValido) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public void validarNumerico(String valor, String mensaje) {
        try {
            Long.parseLong(valor);
        } catch (NumberFormatException numberFormatException) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
