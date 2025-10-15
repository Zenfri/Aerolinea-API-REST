package com.example.demo.handler;

import com.example.demo.excepciones.RecursoExistenteExcepcion;
import com.example.demo.excepciones.RecursoNoEncontradoExcepcion;
import com.example.demo.respuesta.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String,String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
            error -> {
                String campo = ((FieldError) error).getField();
                String mensaje = error.getDefaultMessage();
                errores.put(campo, mensaje);
            }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ApiResponse<>(
                    HttpStatus.BAD_REQUEST.value(),
                    errores.toString(),
                    null,
                    request.getRequestURI()
            )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<?> handlerConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();

        ex.getConstraintViolations().forEach(
                error ->{
                    String campo = error.getPropertyPath().toString();
                    String valor = error.getMessage();
                    errores.put(campo, valor);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse<>( HttpStatus.BAD_REQUEST.value(),  errores.toString(), null, request.getRequestURI())
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<?> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){
        String nombreCampo = ex.getName();
        String tipoEsperado = ex.getRequiredType().getSimpleName();
        String valorRecibido = ex.getValue().toString();

        String error = String.format("Error en el tipo de parámetro {%s}, se esperaba %s, pero se recibió '%s'",
                nombreCampo,
                tipoEsperado,
                valorRecibido);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse<>( HttpStatus.BAD_REQUEST.value(),  error, null, request.getRequestURI())
        );
    }



    @ExceptionHandler(RecursoExistenteExcepcion.class)
    ResponseEntity<?> handlerRecursoExistenteExcepcion(RecursoExistenteExcepcion ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ApiResponse<>( HttpStatus.CONFLICT.value(),  ex.getMessage(), null, request.getRequestURI())
        );
    }

    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    ResponseEntity<?> handlerRecursoNoEncontradoExcepcion(RecursoNoEncontradoExcepcion ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(),ex.getMessage(), null, request.getRequestURI())
        );
    }
}
