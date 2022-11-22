package com.elektra.frecuencias.controlador;

import com.elektra.frecuencias.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaControladorFrecuencias {

  private static final String RUTA = "/datos/frecuencias/obtener-frecuencias";
  private static final String UID = "UID123456789012";
  private static final String ENCABEZADO_UID = "uid";
  private static final String TOKEN = "022DEE73F8528EA4445B133DDB5B224848B2258B";
  private static final String ENCABEZADO_TOKEN = "token";
  private static final String MENSAJE = "mensaje";
  private static final String NOMBRE = "nombre";

  @DisplayName("Endpoint principal")
  @Test
  public void probarObtenerFrecuencias(){
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .queryParam(NOMBRE, "LEONARDO")
      .get(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_EXITO_200));
  }

  @DisplayName("Frecuencias no encontradas")
  @Test
  public void probarNoObtenerFrecuencias(){
    given()
      .when()
      .header(ENCABEZADO_UID,UID)
      .header(ENCABEZADO_TOKEN,TOKEN)
      .queryParam(NOMBRE, "ODRANOEL")
      .get(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_NO_ENCONTRADO));
  }

  @DisplayName("UID incorrecto")
  @Test
  public void probarUidIncorrecto(){
    given()
      .when()
      .header(ENCABEZADO_UID,"UID")
      .header(ENCABEZADO_TOKEN,TOKEN)
      .queryParam(NOMBRE, "Test")
      .get(RUTA)
      .then()
      .statusCode(200)
      .body(MENSAJE,equalTo(Constantes.MENSAJE_CODIGO_400));
  }
}
