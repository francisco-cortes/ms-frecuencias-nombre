package com.elektra.frecuencias.propiedades.models;

import io.quarkus.runtime.annotations.ConfigGroup;

/**
 * <b>Configuraciones</b>
 * @descripcion: Interfaz que contiene las propiedades particulares de la
 * consulta de frecuencia de nombres propios.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 08/07/2022
 */
@ConfigGroup
public interface Configuraciones {
  Credenciales credenciales();
  String ip();
  String port();
  String name();
}
