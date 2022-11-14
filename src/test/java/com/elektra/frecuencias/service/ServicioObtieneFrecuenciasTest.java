package com.elektra.frecuencias.service;
import com.elektra.frecuencias.dto.DtoRespuestaFrecuencias;
import com.elektra.frecuencias.servicios.ServicioObtieneFrecuencias;
import com.elektra.frecuencias.util.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ServicioObtieneFrecuenciasTest {
  @Inject
  private ServicioObtieneFrecuencias servicioObtieneFrecuencias;

  @DisplayName("Prueba Unitaria 1 nombre encontrado")
  @Test
  public void testFrecuenciasEncontradas(){
    final String NOMBRE_EXISTENTE = "BELEN";
    boolean passed;
    DtoRespuestaFrecuencias dtoRespuestaFrecuencias = servicioObtieneFrecuencias.frecuenciasTotales(NOMBRE_EXISTENTE,
      "TESTUID");
    if(dtoRespuestaFrecuencias.getFrecuenciaNombre().intValue() > Constantes.ZERO_DEFUAULT){
      passed = true;
    }
    else {
      passed = false;
    }
    assertTrue(passed);
  }

  @DisplayName("Prueba Unitaria 0 nombre encontrado")
  @Test
  public void testFrecuenciasNOEncontradas(){
    final String NOMBRE_INEXISTENTE = "XDDDDASDASDASDKASKDASD";
    boolean passed;
    DtoRespuestaFrecuencias dtoRespuestaFrecuencias = servicioObtieneFrecuencias.frecuenciasTotales(NOMBRE_INEXISTENTE,
      "TESTUID");
    if(dtoRespuestaFrecuencias.getFrecuenciaNombre().intValue() <= Constantes.ZERO_DEFUAULT){
      passed = true;
    }
    else {
      passed = false;
    }
    assertTrue(passed);
  }

}
