package com.elektra.frecuencias.dao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.wildfly.common.Assert.assertNotNull;

@QuarkusTest
public class DaoFabricaConexionTes {
  @Inject
  private DaoFabricaConexion daoFabricaConexion;

  @DisplayName("Prueba Unitaria sobre FabricaDao para obtener conexion")
  @Test
  public void testGetConexion() throws Exception {
    assertNotNull(daoFabricaConexion.obtenerConexion());
  }
}
