package com.elektra.frecuencias.service;
import com.elektra.frecuencias.dtos.DtoEstadoResponse;
import com.elektra.frecuencias.servicios.ServicioMonitoreo;
import com.elektra.frecuencias.utilidades.Constantes;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ServicioMonitoreoTest {

  @Inject
  private ServicioMonitoreo servicioMonitoreo;

  @DisplayName("Prueba Unitaria sobre Monitoreo")
  @Test
  public void testUid(){
    DtoEstadoResponse dtoEstadoResponse = servicioMonitoreo.generarUid();
    assertEquals(Constantes.ESTADO_OK, dtoEstadoResponse.getMensaje());
  }
}
