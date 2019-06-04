package co.com.ceiba.config;

import co.com.ceiba.dominio.comun.excepcion.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControlExcepciones extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ExcepcionDuplicidad.class})
    protected ResponseEntity<Object> handleConflict(ExcepcionDuplicidad ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ExcepcionOperacionNoPermitida.class,
            ExcepcionValorInvalido.class,
            ExcepcionValorObligatorio.class})
    protected ResponseEntity<Object> handleBadRequest(ExcepcionOperacionNoPermitida ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ExcepcionSinDatos.class})
    protected ResponseEntity<Object> handleNotFound(ExcepcionSinDatos ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}


