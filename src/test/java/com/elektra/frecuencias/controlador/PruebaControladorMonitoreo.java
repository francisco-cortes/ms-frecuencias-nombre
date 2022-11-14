package com.elektra.frecuencias.controlador;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class PruebaControladorMonitoreo {

  @DisplayName("Endpoint status")
  @Test
  public void ProbarEstado(){
    given()
      .when().get("/datos/frecuencias/status")
      .then()
      .statusCode(200)
      .body("mensaje",equalTo("OK"));
  }
}
