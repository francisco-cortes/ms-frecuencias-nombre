package baz.com.moli.services;

import baz.com.moli.daos.FrecuenciaNombreSpDao;
import baz.com.moli.dtos.FrecuenciasResponseDto;
import baz.com.moli.exceptions.ErrorInternoException;
import baz.com.moli.models.CursorRespuestaSpModel;
import baz.com.moli.utils.Constantes;
import com.baz.log.LogServicio;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

/**
 * <b>ObtenerFrecuenciasService</b>
 * @descripcion: Service que implemeta logica nesesaria para obtener una respuesta de la forma
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 07/06/22
 */

@Singleton
public class ObtenerFrecuenciasService {

  //nombre de la clase para log
  private static final String NOMBRE_CLASE = "ObtenerFrecuenciasService";
  /*
  inyeccion de clase dao
   */
  @Inject
  private FrecuenciaNombreSpDao frecuenciaNombreSpDao;

  /**
          * <b>frecuenciasTotales</b>
          * @descripcion: obtiene frecuencias como nombre y apellido para una cadena
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @params: String
          * @ultimaModificacion: 06/10/22
        */

  public FrecuenciasResponseDto frecuenciasTotales(String nombre){
    /*
    Loger
     */
    final String NOMBRE_METODO = "frecuenciasTotales";
    LogServicio log = new LogServicio();

    FrecuenciasResponseDto respuesta = new FrecuenciasResponseDto();

    /*
    inicio de varibles
     */
    CursorRespuestaSpModel frecuenciaNombres;
    CursorRespuestaSpModel frecuenciaApellidos;

    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO, Constantes.NOMBRE_MS);

    try {

      frecuenciaNombres = frecuenciaNombreSpDao.ejecutarSp(nombre, Constantes.TIPO_NOMBRE, log);
      frecuenciaApellidos = frecuenciaNombreSpDao.ejecutarSp(nombre,Constantes.TIPO_APELLIDO, log);

    }
    catch (Exception e) {
      log.registrarExcepcion(e,"Error al obtener frecuencias");
      log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,e.getMessage() + " para: " + nombre);
      throw new ErrorInternoException(Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,
        Constantes.ZERO_BY_DEFAULT,e.getMessage(),Constantes.MENSAJE_CODIGO500);
    }

    /*
    construccion de respuestaa traves de setter y getter
     */
    respuesta.setFrecuenciaNombre(frecuenciaNombres.getFnRegistros());
    respuesta.setTotalRegistrosNombre(frecuenciaNombres.getFnTotal());
    respuesta.setFrecuenciaApellidos(frecuenciaApellidos.getFnRegistros());
    respuesta.setTotalRegistrosApellidos(frecuenciaApellidos.getFnTotal());
    /*
    valida se se encotro el nombre con sus frecuencias
     */
    if(frecuenciaNombres.getFnTotal().intValue() <= Constantes.ZERO_DEFUAULT
      && frecuenciaApellidos.getFnTotal().intValue() <= Constantes.ZERO_DEFUAULT){
      respuesta.setMensaje(Constantes.MENSAJE_NO_ENCONTRADO);
      respuesta.setCodigoInterno(Constantes.HTTP_201);
    }
    else {
      respuesta.setMensaje(Constantes.MENSAJE_EXITO);
      respuesta.setCodigoInterno(Constantes.HTTP_200);
    }
    log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,Constantes.MENSAJE_EXITO);
    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    log.obtenerTiempoTotal(NOMBRE_CLASE+NOMBRE_METODO);
    return respuesta;
  }

}
