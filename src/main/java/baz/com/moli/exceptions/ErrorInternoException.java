package baz.com.moli.exceptions;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

/**
 * <b>ErrorInternoExepcion</b>
 * @descripcion: breve descripción del contenido
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternoException extends RuntimeException{
  /*
  cuantas veces se encontro la cadena como nombre
   */
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa las veces que el dato fue registrado como nombre"
  )
  private BigDecimal frecuenciaNombre;
  /*
  total de todos lo nombres en la base de datos
   */
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa el total de elementos registrados como nombre"
  )
  private BigDecimal totalRegistrosNombre;
  /*
  total de veces que se encontro la cadena como apellido
   */
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa las veces que el dato fue registrado como apellido"
  )
  private BigDecimal frecuenciaApellidos;
  /*
  total de todos los apellidos en la base de datos
   */
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa el total de elementos registrados como apellido"
  )
  private BigDecimal totalRegistrosApellidos;
  /*
  mensaje de operacion interna
   */
  @Schema(
    example = " ERROR SQL ",
    description = "Cadena, mensaje de expcion en caso de existir"
  )
  private String mensaje;
  /*
  codigo de operacion interna
   */
  @Schema(
    example = " 200 ",
    description = "Cadena, codigo interno de la operacion del modulo"
  )
  private String codigoInterno;

  /**
          * <b>ErrorInternoException</b>
          * @descripcion: breve descripcion del metodo
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @params: Big
          * @ultimaModificacion: 06/10/22
        */

  public ErrorInternoException(BigDecimal frecuenciaNombre, BigDecimal totalRegistrosNombre,
                               BigDecimal frecuenciaApellidos, BigDecimal totalRegistrosApellidos,
                               String mensaje, String codigoInterno){
    this.frecuenciaNombre = frecuenciaNombre;
    this.totalRegistrosNombre = totalRegistrosNombre;
    this.frecuenciaApellidos = frecuenciaApellidos;
    this.totalRegistrosApellidos = totalRegistrosApellidos;
    this.mensaje = mensaje;
    this.codigoInterno = codigoInterno;
  }

  /*
  getters
   */
  public BigDecimal getFrecuenciaNombre(){
    return frecuenciaNombre;
  }

  public BigDecimal getTotalRegistrosNombre(){
    return totalRegistrosNombre;
  }

  public BigDecimal getFrecuenciaApellidos(){
    return frecuenciaApellidos;
  }

  public BigDecimal getTotalRegistrosApellidos(){
    return totalRegistrosApellidos;
  }

  public String getMensaje(){
    return mensaje;
  }

  public String getCodigoInterno(){
    return codigoInterno;
  }

}
