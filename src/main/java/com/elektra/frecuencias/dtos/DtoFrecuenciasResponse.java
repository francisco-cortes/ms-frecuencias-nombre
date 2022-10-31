package com.elektra.frecuencias.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

/**
        * <b>DtoFrecuenciasResponse</b>
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
public class DtoFrecuenciasResponse {
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
  /*
  codigo de operacion interna
   */
  @Schema(
    example = " 200 ",
    description = "Cadena, codigo interno de la operacion del modulo"
  )
  private String codigoInterno;
}
