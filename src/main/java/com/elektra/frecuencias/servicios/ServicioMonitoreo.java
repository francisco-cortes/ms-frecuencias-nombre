package com.elektra.frecuencias.servicios;

import com.elektra.frecuencias.dto.DtoRespuestaEstado;
import com.elektra.frecuencias.util.Constantes;
import com.baz.servicios.Uid;

import javax.inject.Singleton;

/**
 * <b>ServicioMonitoreo</b>
 * @descripcion: Monitoreo service para el modulo
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/10/22
 */
@Singleton
public class ServicioMonitoreo {
  /**
   * <b>generarUid</b>
   * @descripcion: genera un UID para verificar el estado del módulo
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 04/10/22
   */
  public static DtoRespuestaEstado generarUid(){
    DtoRespuestaEstado uidAlive = new DtoRespuestaEstado();
    uidAlive.setMensaje(Constantes.ESTADO_OK);
    /*
    genera folio a través de RemesasUtils.jar
     */
    uidAlive.setFolio("UDI" + Uid.generarUid(Constantes.TAMANO_UDI,Constantes.CICLOS_UDI));
    return uidAlive;
  }

}
