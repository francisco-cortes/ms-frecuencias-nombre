package baz.com.moli.services;

import baz.com.moli.daos.FrecunciaNombreSpDao;
import baz.com.moli.dtos.FrecuenciasResponseDto;
import baz.com.moli.models.CursorRespuestaSpModel;
import baz.com.moli.utils.Constantes;
import com.baz.log.LogServicio;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
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
  private FrecunciaNombreSpDao frecunciaNombreSpDao;

  public FrecuenciasResponseDto frecuenciasTotales(String nombre){
    LogServicio log = new LogServicio();

    FrecuenciasResponseDto respuesta = new FrecuenciasResponseDto();

    CursorRespuestaSpModel frecuenciaNombres = new CursorRespuestaSpModel(BigDecimal.valueOf(0),
      BigDecimal.valueOf(0),"Na");
    CursorRespuestaSpModel frecuenciaApellidos = new CursorRespuestaSpModel(BigDecimal.valueOf(0),
      BigDecimal.valueOf(0),"Na");

    try {
      frecuenciaNombres = frecunciaNombreSpDao.ejecutarSp(nombre, Constantes.TIPO_NOMBRE);
      frecuenciaApellidos = frecunciaNombreSpDao.ejecutarSp(nombre,Constantes.TIPO_APELLIDO);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    respuesta.setFrecuenciaNombre(frecuenciaNombres.getFnRegistros());
    respuesta.setTotalRegistrosNombre(frecuenciaNombres.getFnTotal());
    respuesta.setFrecuenciaApellidos(frecuenciaApellidos.getFnRegistros());
    respuesta.setTotalRegistrosApellidos(frecuenciaApellidos.getFnTotal());
    respuesta.setMensaje("OPERACION EXITOSA");
    respuesta.setCodigoInterno("200");

    return respuesta;
  }

}
