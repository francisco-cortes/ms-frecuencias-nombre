package com.elektra.frecuencias.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>StatusResponse</b>
 * @descripcion: Entidad que obtiene el mensaje que representa el estado del microservicio.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder({"mensaje","folio"})
public class DtoEstadoResponse {
  /*
  mensaje
   */
  @Schema(example = "OK", description = "Mensaje de estado")
  public String mensaje;
  /*
  folio generado por remesas
   */
  @Schema(example = "UID 1234567889", description = "folio de modulo")
  public String folio;
}
