package com.elektra.frecuencias.propiedades;

import com.elektra.frecuencias.propiedades.models.Configuraciones;
import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

import java.util.Map;

/**
 * <b>Properties</b>
 * @descripcion: Interfaz que representa a las entidades de las propiedades
 * de la applicación.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 08/07/2022
 */

@StaticInitSafe
@ConfigMapping(prefix = "frecuencias")
public interface Properties {
  /**
   * <b>conexionesdb</b>
   * @descripcion: apunta a configuracionesdb
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * mapa de configuraciones
   * @ultimaModificacion: 04/10/22
   */
  Map<String, Configuraciones> conexionesdb();
}
