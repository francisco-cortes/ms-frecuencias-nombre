package com.elektra.frecuencias.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>StatusResponse</b>
 * @descripcion: Entidad que obtiene el mensaje que representa el estado del microservicio.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 12/07/22
 */

@Data
@Setter
@JsonPropertyOrder({"mensaje","folio"})
public class DtoRespuestaEstado {
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
