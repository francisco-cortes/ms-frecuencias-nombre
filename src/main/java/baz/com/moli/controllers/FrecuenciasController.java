package baz.com.moli.controllers;

import baz.com.moli.dtos.EstadoResponseDto;
import baz.com.moli.services.MonitoreoService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/remesas/frecuencias")
public class FrecuenciasController {

  /*
  instacia del servicio monitoreo a tra ves de inyeccion
   */
  @Inject
  private MonitoreoService monitoreoService;

  @GET
  @Path("/obtener-frecuencias")
  @Consumes(MediaType.TEXT_PLAIN)
  @Produces(MediaType.APPLICATION_JSON)
  public Response frecuencias(@QueryParam("nombre") String nombre){
    return Response.ok().entity("hola").build();
  }

  /**
   * <b>status</b>
   * @descripcion: Método para validar el estado del microservicio
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 20/06/22
   */
  @GET
  @Path("/estado")
  @Operation(
    summary = "Metodo de consulta al estado y Uid del microservicio",
    description = "description")
  @Produces(MediaType.APPLICATION_JSON)
  public Response status(){
    /*
    modelo con con los datos de salida
     */
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    /*
    retorna el objeto como entidad para el parceo como json
     */
    return Response.ok().entity(estadoResponseDto).build();
  }

}
