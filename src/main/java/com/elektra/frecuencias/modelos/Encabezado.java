package com.elektra.frecuencias.modelos;

import com.baz.anotaciones.Validacion;
import com.elektra.frecuencias.util.Constantes;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
public class Encabezado {
  @Validacion(tipoDato = Validacion.tiposDato.VARCHAR, requerido = Constantes.NO_REQUERIDO,
    caracteresValidos = Validacion.LETRAS_Y_NUMEROS, longitudMin = 40, longitudMax = 40 )
  @Schema(example = "48E48D91B2B009CEAF5A368BB3103A34021B7000", required = true, minLength = 40 ,maxLength = 40,
    description = "Token para inciar la solicitud.")
  private String token;

  @Validacion(tipoDato = Validacion.tiposDato.VARCHAR, requerido = Constantes.ES_REQUERIDO,
    caracteresValidos = Validacion.LETRAS_Y_NUMEROS, longitudMin =15, longitudMax = 15 ,
    expresionRegular = "UID\\d{12}")
  @Schema(example = "UID123412341332", required = true, minLength =15 ,maxLength = 15,
    description = "Identificador único de trama.", name = "x-request-id")
  private String uid;

  public Encabezado(String uid, String token){
    this.uid = uid;
    this.token = token;

  }
}
