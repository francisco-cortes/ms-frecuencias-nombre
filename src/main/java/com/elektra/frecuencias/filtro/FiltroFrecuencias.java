package com.elektra.frecuencias.filtro;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.UnauthorizedException;
import com.baz.log.LogServicio;
import com.baz.servicios.ValidarDto;
import com.elektra.frecuencias.modelos.Encabezado;
import com.elektra.frecuencias.modelos.Resultado;
import com.elektra.frecuencias.util.Constantes;
import com.elektra.frecuencias.util.UtilidadGenerarExcepcion;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * <b>FiltroFrecuencias</b>
 * @descripcion: Filtra la petición http.
 * @autor: Francisco Javier Cortes Torre, Desarrollador
 * @ultimaModificacion: 13/10/2022
 */
@Provider
@PreMatching
public class FiltroFrecuencias implements ContainerRequestFilter {

  private static final UtilidadGenerarExcepcion UTILIDAD_GENERAR_EXCEPCION = new UtilidadGenerarExcepcion();

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    System.out.println("ENTRA AL FILTER");
    LogServicio log = new LogServicio();
    String nombreClaseMetodo  = "FiltroFrecuencias-filtros";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    String uid = requestContext.getHeaderString("uid");
    String token = requestContext.getHeaderString("token");
    ValidarDto validarDto;

    System.out.println("VALOR DE uid "+ uid);

    if (!"/datos/frecuencias/obtener-frecuencias".equals(requestContext.getUriInfo().getPath())) {
      return;
    }

    System.out.println("ENTRA EN EL TRY");
    Resultado resultado = new Resultado(uid, Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO_200);
    validarDto = new ValidarDto();
    System.out.println("entra a validar peticion ");
    try {
      validarDto.validarPeticionAes(new Encabezado(uid, token), resultado);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    System.out.println(resultado.getMensaje());
    System.out.println("sale de validar peticion");
    System.out.println(resultado.getCodigo());
    return;
  }
}
