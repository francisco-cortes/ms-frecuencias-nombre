package baz.com.moli.service;
import baz.com.moli.dtos.FrecuenciasResponseDto;
import baz.com.moli.services.ObtenerFrecuenciasService;
import baz.com.moli.utils.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ObtenerFrecuenciasServiceTest {
  @Inject
  private ObtenerFrecuenciasService obtenerFrecuenciasService;

  @DisplayName("Prueba Unitaria 1 nombre encontrado")
  @Test
  public void testFrecuenciasEncontradas(){
    final String NOMBRE_EXISTENTE = "BELEN";
    boolean passed;
    FrecuenciasResponseDto frecuenciasResponseDto = obtenerFrecuenciasService.frecuenciasTotales(NOMBRE_EXISTENTE);
    if(frecuenciasResponseDto.getFrecuenciaNombre().intValue() > Constantes.ZERO_DEFUAULT){
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
    FrecuenciasResponseDto frecuenciasResponseDto = obtenerFrecuenciasService.frecuenciasTotales(NOMBRE_INEXISTENTE);
    if(frecuenciasResponseDto.getFrecuenciaNombre().intValue() <= Constantes.ZERO_DEFUAULT){
      passed = true;
    }
    else {
      passed = false;
    }
    assertTrue(passed);
  }

}
