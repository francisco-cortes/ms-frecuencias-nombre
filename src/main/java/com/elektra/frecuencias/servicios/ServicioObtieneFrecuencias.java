package com.elektra.frecuencias.servicios;

import com.baz.excepciones.InternalServerErrorException;
import com.elektra.frecuencias.dao.DaoConsultaFrecuencia;
import com.elektra.frecuencias.dto.DtoRespuestaFrecuencias;
import com.elektra.frecuencias.modelos.ModeloRespuestaSp;
import com.elektra.frecuencias.modelos.Resultado;
import com.elektra.frecuencias.util.Constantes;
import com.baz.log.LogServicio;
import com.elektra.frecuencias.util.UtilidadGenerarExcepcion;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * <b>ServicioObtieneFrecuencias</b>
 * @descripcion: Service que implementa logica necesaria para obtener una respuesta de la forma
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 07/06/22
 */
@Singleton
public class ServicioObtieneFrecuencias {
  /*
  inyeccion de clase dao
   */
  @Inject
  private DaoConsultaFrecuencia daoConsultaFrecuencia;

  @Inject
  private UtilidadGenerarExcepcion utilidadGenerarExcepcion;
  /**
   * <b>frecuenciasTotales</b>
   * @descripcion: obtiene frecuencias como nombre y apellido para una cadena
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: String
   * @ultimaModificacion: 06/10/22
   */
  public DtoRespuestaFrecuencias frecuenciasTotales(String nombre, String uid){
    String nombreClaseMetodo = "ServicioObtieneFrecuencias-frecuenciasTotales";
    LogServicio log = new LogServicio();
    log.iniciarTiempoMetodo(nombreClaseMetodo, Constantes.NOMBRE_MS);

    Resultado resultado = new Resultado(uid, Constantes.CODIGO_ERROR_GENERAL,
      Constantes.MENSAJE_CODIGO_500);

    DtoRespuestaFrecuencias respuesta = new DtoRespuestaFrecuencias();
    ModeloRespuestaSp frecuenciaNombres;
    ModeloRespuestaSp frecuenciaApellidos;
    nombre = nombre.toUpperCase();
    try {
      frecuenciaNombres = daoConsultaFrecuencia.consultarFrecuencias(nombre,
        Constantes.TIPO_NOMBRE, log);
      frecuenciaApellidos = daoConsultaFrecuencia.consultarFrecuencias(nombre,
        Constantes.TIPO_APELLIDO, log);
      /*
      construcción de respuestas a través de getter
      */
      respuesta.setFrecuenciaNombre(frecuenciaNombres.getFnRegistros());
      respuesta.setTotalRegistrosNombre(frecuenciaNombres.getFnTotal());
      respuesta.setFrecuenciaApellidos(frecuenciaApellidos.getFnRegistros());
      respuesta.setTotalRegistrosApellidos(frecuenciaApellidos.getFnTotal());
      /*
      válida si se encontró el nombre con sus frecuencias
      */
      if(frecuenciaNombres.getFnTotal().intValue() <= Constantes.ZERO_DEFUAULT
        && frecuenciaApellidos.getFnTotal().intValue() <= Constantes.ZERO_DEFUAULT){
        respuesta.setMensaje(Constantes.MENSAJE_NO_ENCONTRADO);
      }
      else {
        respuesta.setMensaje(Constantes.MENSAJE_EXITO_200);
      }
    }
    catch (InternalServerErrorException excepcion){
      log.registrarExcepcion(excepcion, resultado.getMensaje());
      throw excepcion;
    }
    catch (Exception excepcion){
      log.registrarExcepcion(excepcion, Constantes.MENSAJE_ERROR_SQL);
      /*
      arroja respuesta controlada a través del controlador de exepciones
       */
      utilidadGenerarExcepcion.generarExcepcion(Constantes.CODIGO_HTTP_500, resultado.getCodigo(),
        resultado.getMensaje() + " " + excepcion.getMessage(), uid);
    }
    finally {
      log.terminarTiempoMetodo(nombreClaseMetodo);
    }
    return respuesta;
  }

}
