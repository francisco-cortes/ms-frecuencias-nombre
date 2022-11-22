package com.elektra.frecuencias.modelos;

import com.baz.anotaciones.Codigo;
import com.baz.anotaciones.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * <b>Resultado</b>
 * @descripcion: breve descripción del contenido
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 04/11/22
 */
@Getter
@Setter
@AllArgsConstructor
public class Resultado {
  private String uid="";
  @Codigo
  private String codigo="";
  @Mensaje
  private String mensaje="";
}
