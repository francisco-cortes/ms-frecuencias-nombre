package com.elektra.frecuencias.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * <b>DtoRespuestaFrecuencias</b>
 * @descripcion: breve descripción del contenido
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 06/10/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder({"frecuenciaNombre","totalregistrosNombre","frecuenciaApellidos","totalRegistrosApellidos"})
public class DtoRespuestaFrecuencias {
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
    example = " Operacion Exitosa ",
    description = "Cadena, mensaje de expcion en caso de existir"
  )
  private String mensaje;
}
