package com.elektra.frecuencias.service;
import com.elektra.frecuencias.dtos.DtoFrecuenciasResponse;
import com.elektra.frecuencias.servicios.ServicioObtenerFrecuencias;
import com.elektra.frecuencias.utilidades.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ServicioObtenerFrecuenciasTest {
  @Inject
  private ServicioObtenerFrecuencias servicioObtenerFrecuencias;

  @DisplayName("Prueba Unitaria 1 nombre encontrado")
  @Test
  public void testFrecuenciasEncontradas(){
    final String NOMBRE_EXISTENTE = "BELEN";
    boolean passed;
    DtoFrecuenciasResponse dtoFrecuenciasResponse = servicioObtenerFrecuencias.frecuenciasTotales(NOMBRE_EXISTENTE);
    if(dtoFrecuenciasResponse.getFrecuenciaNombre().intValue() > Constantes.ZERO_DEFUAULT){
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
    DtoFrecuenciasResponse dtoFrecuenciasResponse = servicioObtenerFrecuencias.frecuenciasTotales(NOMBRE_INEXISTENTE);
    if(dtoFrecuenciasResponse.getFrecuenciaNombre().intValue() <= Constantes.ZERO_DEFUAULT){
      passed = true;
    }
    else {
      passed = false;
    }
    assertTrue(passed);
  }

}
