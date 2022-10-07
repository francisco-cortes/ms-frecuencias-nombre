package baz.com.moli.service;
import baz.com.moli.dtos.EstadoResponseDto;
import baz.com.moli.services.MonitoreoService;
import baz.com.moli.utils.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class MonitoreoServiceTest {

  @Inject
  private MonitoreoService monitoreoService;

  @DisplayName("Prueba Unitaria sobre Monitoreo")
  @Test
  public void testUid(){
    EstadoResponseDto estadoResponseDto = monitoreoService.generarUid();
    assertEquals(Constantes.ESTADO_OK, estadoResponseDto.getMensaje());
  }
}
