package baz.com.moli.properties.models;

import io.quarkus.runtime.annotations.ConfigGroup;

/**
 * <b>Credencias</b>
 * @descripcion: Interfaz que contiene las credencias de DB
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 08/07/2022
 */

@ConfigGroup
public interface Credenciales {
  String usuario();
  String contrasena();
}
