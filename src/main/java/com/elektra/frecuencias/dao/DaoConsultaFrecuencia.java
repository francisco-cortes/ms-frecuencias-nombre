package com.elektra.frecuencias.dao;

import com.baz.servicios.CifradorAes;
import com.baz.servicios.DaoUtils;
import com.elektra.frecuencias.modelos.ModeloRespuestaSp;
import com.elektra.frecuencias.propiedades.Propiedades;
import com.elektra.frecuencias.util.Constantes;
import com.baz.log.LogServicio;
import com.elektra.frecuencias.util.UtilidadGenerarExcepcion;
import oracle.jdbc.OracleTypes;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>ConsultarFrecuenciaNombreDao</b>
 * @descripcion: DAO para acceder a un StoredProcedure
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 07/06/22
 */

@ApplicationScoped
public class DaoConsultaFrecuencia {
  /**
   * Inyección de objeto para conexión a DB
   */
  @Inject
  private DaoFabricaConexion daoFabricaConexion;
  @Inject
  private Propiedades propiedades;

  @Inject
  private UtilidadGenerarExcepcion utilidadGenerarExcepcion;

  private CifradorAes cifradorAes;
  /**
   * <b>consultarFrecuencias</b>
   * @descripcion: Método ejecuta un StoredProcedure que obtiene una tabla de 3 columnas
   * y guarda la información en un mode lo de datos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @ultimaModificacion: 07/06/22
   */
  @Transactional
  public ModeloRespuestaSp consultarFrecuencias(String nombre, BigDecimal tipoDato, LogServicio log)
    throws SQLException, ClassNotFoundException,
    InvalidAlgorithmParameterException, UnsupportedEncodingException,
    NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
    BadPaddingException, InvalidKeyException {
    cifradorAes = new CifradorAes(false);
    DaoUtils daoUtils = new DaoUtils();
    String nombreClaseMetodo = "DaoConsultaFrecuencia-consultarFrecuencias";
    log.iniciarTiempoMetodo(nombreClaseMetodo,Constantes.NOMBRE_MS);
    /*
    constantes para indice de sp
     */
    final int INDICE_NOMBRE = 1;
    final int INDICE_TIPO_DATO = 2;
    final int INDICE_CURSOR_SALIDA = 3;
    final int INDICE_CODIGO_EXITO = 4;
    final int INDICE_MENSAJE_EXITO = 5;
    //nombre de sp
    String spConsultaFrecuencia = daoUtils.obtenerCallableStatementProcedimiento(
      cifradorAes.desencriptarDato(propiedades.conexionesdb().get(Constantes.C3REMESASC).esquema()),
      cifradorAes.desencriptarDato(propiedades.conexionesdb().get(Constantes.C3REMESASC).paquete()),
      cifradorAes.desencriptarDato(propiedades.conexionesdb().get(Constantes.C3REMESASC).sp()),
      5);
    /*
    objetos para consumo de sp
     */
    Connection conexion = null;
    CallableStatement declaracion = null;
    ResultSet resultSet = null;
    /*
    modelo para respuesta de cursor
     */
    ModeloRespuestaSp cursor = new ModeloRespuestaSp();
    try {
      /*
      Solicita conexión
       */
      conexion = daoFabricaConexion.obtenerConexion();
      /*
      Asigna cadena de llamada a SP ("{ call C3MULTIMARCAS.PAFONETICO03.SPCONSULTAFRECU(?, ?, ?, ?, ?) }");
       */
      declaracion = conexion.prepareCall(spConsultaFrecuencia);
      /*
      Define parámetros de procedimiento y asigna valores de entrada
       */
      declaracion.setString(INDICE_NOMBRE, nombre);
      declaracion.setBigDecimal(INDICE_TIPO_DATO, tipoDato);
      declaracion.registerOutParameter(INDICE_CURSOR_SALIDA, OracleTypes.REF_CURSOR);
      declaracion.registerOutParameter(INDICE_CODIGO_EXITO, OracleTypes.VARCHAR);
      declaracion.registerOutParameter(INDICE_MENSAJE_EXITO, OracleTypes.VARCHAR);
      /*
      Ejecuta procedimiento
       */
      declaracion.executeQuery();
      /*
      Obtiene respuestas del procedimiento
       */
      resultSet = (ResultSet) declaracion.getObject(INDICE_CURSOR_SALIDA);
      if(declaracion.getString(INDICE_MENSAJE_EXITO).isEmpty()){
        log.registrarMensaje(nombreClaseMetodo, "Resultado Vacio de SPCONSULTAFRECU");
      }
      else {
        /*
        iteración de cursor
         */
        while (resultSet.next()){
          cursor.setFNREGISTROS(resultSet.getBigDecimal("FNREGISTROS"));
          cursor.setFNTOTAL(resultSet.getBigDecimal("FNTOTAL"));
          cursor.setFNUSUARIO(resultSet.getString("FCUSUARIO"));
        }
      }
    }
    finally {
      /*
      cerrar conexión a base postgres
      */
      daoFabricaConexion.cerrarConexion(conexion,declaracion,resultSet);
      log.terminarTiempoMetodo(nombreClaseMetodo);
    }
    return cursor;
  }
}
