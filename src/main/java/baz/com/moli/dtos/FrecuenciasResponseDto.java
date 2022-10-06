package baz.com.moli.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonPropertyOrder({"frecuenciaNombre","totalregistrosNombre","frecuenciaApellidos","totalRegistrosApellidos"})
public class FrecuenciasResponseDto {
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa las veces que el dato fue registrado como nombre"
  )
  private BigDecimal frecuenciaNombre;
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa el total de elementos registrados como nombre"
  )
  private BigDecimal totalRegistrosNombre;
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa las veces que el dato fue registrado como apellido"
  )
  private BigDecimal frecuenciaApellidos;
  @Schema(
    example = "900",
    description = "Numero Float, el numero representa el total de elementos registrados como apellido"
  )
  private BigDecimal totalRegistrosApellidos;
  @Schema(
    example = " ERROR SQL ",
    description = "Cadena, mensaje de expcion en caso de existir"
  )
  private String mensaje;
  @Schema(
    example = " 200 ",
    description = "Cadena, codigo interno de la operacion del modulo"
  )
  private String codigoInterno;
}
