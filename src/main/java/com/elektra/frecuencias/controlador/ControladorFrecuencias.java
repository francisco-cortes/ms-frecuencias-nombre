package com.elektra.frecuencias.controlador;

import com.baz.excepciones.DtoExcepcion;
import com.elektra.frecuencias.dto.DtoRespuestaFrecuencias;
import com.elektra.frecuencias.servicios.ServicioObtieneFrecuencias;
import com.elektra.frecuencias.util.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * <b>ControladorFrecuencias</b>
 * @descripcion: Recurso principal para ejecución
 * de la búsqueda de las frecuencias de un nombre
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 06/10/22
 */

@RestController
@RequestMapping("/datos/frecuencias")
@Tag(name = "Controlador - Frecuencias")
public class ControladorFrecuencias {
  @Inject
  private ServicioObtieneFrecuencias servicioObtieneFrecuencias;

  /**
   * <b>frecuencias</b>
   * @descripcion: Recurso principal
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @param: nombre, String
   * @ultimaModificacion: 06/10/22
   */
  @Operation(summary = "Método que consulta las frecuencias de aparición de un nombre o apellido",
    description = "Método GET")
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoRespuestaFrecuencias.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_400,
        description = "Solicitud incorrecta",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_404,
        description = "No Encontrado",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = DtoExcepcion.class))),
    })
  @GetMapping(value ="/obtener-frecuencias", produces = MediaType.APPLICATION_JSON_VALUE)
  public Response frecuencias(@RequestParam("nombre") String nombre){
    /*
    Frecuencias de un nombre
     */
    DtoRespuestaFrecuencias respuesta = servicioObtieneFrecuencias.frecuenciasTotales(nombre.toUpperCase(), "HARDCODE_UID");
    return Response.ok().entity(respuesta).build();
  }
}
