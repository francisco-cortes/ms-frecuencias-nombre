package com.elektra.frecuencias.modelos;

import com.baz.anotaciones.Codigo;
import com.baz.anotaciones.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
  private String uid="";
  @Codigo
  private String codigo="";
  @Mensaje
  private String mensaje="";
}
