package com.elektra.frecuencias.controlador;

import com.elektra.frecuencias.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaControladorFrecuencias {

  @DisplayName("Enpoint principal")
  @Test
  public void probarObtenerFrecuencias(){
    given()
      .when()
      .header("uid","UID1234567891012")
      .queryParam("nombre", "LEONARDO")
      .get("/datos/frecuencias/obtener-frecuencias")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.MENSAJE_EXITO_200));
  }

  @DisplayName("Enpoint principal")
  @Test
  public void probarNoObtenerFrecuencias(){
    given()
      .when()
      .queryParam("nombre", "ODRANOEL")
      .get("/datos/frecuencias/obtener-frecuencias")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo(Constantes.MENSAJE_CODIGO_500));
  }

}
