package com.elektra.frecuencias.controlador;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.DtoExcepcion;
import com.baz.excepciones.InternalServerErrorException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <b>ExcepcionesController</b>
 * @descripcion: Controlador de excepciones y respuestas controladas
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@RestControllerAdvice
public class ControladorExcepciones {

  /**
   * <b>badRequestException</b>
   * @descripcion: Método para personalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionIncorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {BadRequestException.class})
  public DtoExcepcion badRequestException(BadRequestException peticionIncorrecta){
    return new DtoExcepcion(peticionIncorrecta.getCodigo(),
      peticionIncorrecta.getMensaje(),
      peticionIncorrecta.getFolio(),
      peticionIncorrecta.getDetalles());
  }
  /**
   * <b>InternalServerErrorException</b>
   * @descripcion: Método para personalizar la salida del tipo 400.
   * @autor: Francisco Javier Cortes Torres.
   * @param: peticionIncorrecta Datos de la excepción.
   * @ultimaModificacion: 17/10/2022
   */
  @ExceptionHandler(value = {InternalServerErrorException.class})
  public DtoExcepcion internalServerException(InternalServerErrorException errorInterno) {
    return new DtoExcepcion(errorInterno.getCodigo(),
      errorInterno.getMensaje(),
      errorInterno.getFolio(),
      errorInterno.getDetalles());
  }
}
