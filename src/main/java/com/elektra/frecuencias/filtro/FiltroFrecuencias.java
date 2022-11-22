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
    LogServicio log = new LogServicio();
    String nombreClaseMetodo  = "FiltroHipocoristico-filtro";
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    String uid = requestContext.getHeaderString("uid");
    String token = requestContext.getHeaderString("token");
    ValidarDto validarDto;

    try {
      if (!"/datos/frecuencias/obtener-frecuencias".equals(requestContext.getUriInfo().getPath())) {
        return;
      }

      Resultado resultado = new Resultado(uid, Constantes.CODIGO_EXITO, Constantes.MENSAJE_EXITO);
      validarDto = new ValidarDto();
      validarDto.validarPeticionAes(new Encabezado(uid, token), resultado);

      if (!resultado.getCodigo().equals(Constantes.CODIGO_EXITO)) {
        UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_400, Constantes.CODIGO_SOLICITUD_INCORRECTA,
          resultado.getMensaje(), uid);
      }
      return;

    }
    catch(BadRequestException | UnauthorizedException excepcion) {
      log.registrarExcepcion(excepcion, null);
      throw excepcion;
    }
    catch(Exception excepcion){
      log.registrarExcepcion(excepcion, null);
      UTILIDAD_GENERAR_EXCEPCION.generarExcepcion(Constantes.CODIGO_HTTP_500, Constantes.CODIGO_ERROR_GENERAL,
        excepcion.getMessage(), uid);
    }
  }
}
