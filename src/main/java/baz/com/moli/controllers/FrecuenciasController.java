package baz.com.moli.controllers;

import baz.com.moli.dtos.FrecuenciasResponseDto;
import baz.com.moli.exceptions.ErrorInternoException;
import baz.com.moli.services.ObtenerFrecuenciasService;
import baz.com.moli.utils.Constantes;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
        * <b>FrecuenciasController</b>
        * @descripcion: Enpoints
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 06/10/22
       */

@RestController
@RequestMapping("/datos/frecuencias")
@Tag(name = "Controlador - Frecuencias")
public class FrecuenciasController {
  @Inject
  private ObtenerFrecuenciasService obtenerFrecuenciasService;

  /**
          * <b>frecuencias</b>
          * @descripcion: Enpoint principal
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @params: String
          * @ultimaModificacion: 06/10/22
        */
  @Operation(summary = "Metodo que consulta las frecuencias de aparicion de un nombre o apellido",
    description = "Metodo GET")
  @APIResponses(value =
    {
      @APIResponse(
        responseCode = Constantes.HTTP_200,
        description = "Respuesta Controlada",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = FrecuenciasResponseDto.class))),
      @APIResponse(
        responseCode = Constantes.HTTP_500,
        description = "Error Interno en la aplicación",
        content = @Content(mediaType = "application/json",
          schema =  @Schema(implementation = ErrorInternoException.class))),

    })
  @GetMapping(value ="/obtener-frecuencias",
    produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
    consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
  public Response frecuencias(@RequestParam("nombre") String nombre){
    /*
    Frecuencias de un nombre
     */
    FrecuenciasResponseDto respuesta = obtenerFrecuenciasService.frecuenciasTotales(nombre.toUpperCase());
    return Response.ok().entity(respuesta).build();
  }
}
