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

  @Inject
  private FrecuenciaNombreSpDao frecuenciaNombreSpDao;

  public FrecuenciasResponseDto frecuenciasTotales(String nombre){
    LogServicio log = new LogServicio();

    FrecuenciasResponseDto respuesta = new FrecuenciasResponseDto();

    CursorRespuestaSpModel frecuenciaNombres;
    CursorRespuestaSpModel frecuenciaApellidos;

    try {
      frecuenciaNombres = frecuenciaNombreSpDao.ejecutarSp(nombre, Constantes.TIPO_NOMBRE);
      frecuenciaApellidos = frecuenciaNombreSpDao.ejecutarSp(nombre,Constantes.TIPO_APELLIDO);
    } catch (SQLException | NullPointerException e) {
      throw new ErrorInternoException(Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,
        Constantes.ZERO_BY_DEFAULT,e.getMessage(),Constantes.MENSAJE_CODIGO500);
    }

    respuesta.setFrecuenciaNombre(frecuenciaNombres.getFnRegistros());
    respuesta.setTotalRegistrosNombre(frecuenciaNombres.getFnTotal());
    respuesta.setFrecuenciaApellidos(frecuenciaApellidos.getFnRegistros());
    respuesta.setTotalRegistrosApellidos(frecuenciaApellidos.getFnTotal());
    respuesta.setMensaje(Constantes.MENSAJE_EXITO);
    respuesta.setCodigoInterno(Constantes.HTTP_200);

    return respuesta;
  }

}
