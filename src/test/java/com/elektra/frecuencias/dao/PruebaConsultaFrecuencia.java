package com.elektra.frecuencias.dao;

import com.baz.log.LogServicio;
import com.elektra.frecuencias.util.Constantes;
import com.elektra.frecuencias.modelos.ModeloRespuestaSp;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class PruebaConsultaFrecuencia {

  @Inject
  private DaoConsultaFrecuencia daoConsultaFrecuencia;

  @DisplayName("Consultar frecuencias de nombre")
  @Test
  public void pruebaConsultarFrecuencias() throws InvalidAlgorithmParameterException, SQLException,
    UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException,
    NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
    LogServicio log = new LogServicio();
    ModeloRespuestaSp respuesta = daoConsultaFrecuencia.consultarFrecuencias("LEONARDO",
      Constantes.TIPO_NOMBRE, log);
    assertTrue((respuesta.getFnRegistros().intValue()) >0);
  }

  @DisplayName("Consultar frecuencias de apellido")
  @Test
  public void pruebaConsultarFrecuenciasApellido() throws InvalidAlgorithmParameterException, SQLException,
    UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException,
    NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ClassNotFoundException {
    LogServicio log = new LogServicio();
    ModeloRespuestaSp respuesta = daoConsultaFrecuencia.consultarFrecuencias("GARCIA",
      Constantes.TIPO_APELLIDO, log);
    assertTrue((respuesta.getFnRegistros().intValue()) >0);
  }


}
