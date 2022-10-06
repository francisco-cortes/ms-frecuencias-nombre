package baz.com.moli.controllers;

import baz.com.moli.dtos.FrecuenciasResponseDto;
import baz.com.moli.exceptions.ErrorInternoException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>ExepcionesController</b>
 * @descripcion: Controlador de exepciones y respuestas controladas
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@RestControllerAdvice
public class ExcepcionesController {
  /**
   * <b>ErrorInternoException</b>
   * @descripcion: Método para personzalizar la salida de una excepción del tipo 500.
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 5/10/22
   */
  @ExceptionHandler(value = {ErrorInternoException.class})
  public FrecuenciasResponseDto errorInterno(ErrorInternoException errorInterno) {
    /*
    construccion del metodo de salida
     */
    return new FrecuenciasResponseDto(errorInterno.getFrecuenciaNombre(),errorInterno.getTotalRegistrosNombre(),
      errorInterno.getFrecuenciaApellidos(),errorInterno.getTotalRegistrosApellidos(),errorInterno.getMensaje(),
      errorInterno.getCodigoInterno());
  }
}
