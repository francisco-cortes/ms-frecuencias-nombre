package com.elektra.frecuencias.daos;

import com.elektra.frecuencias.excepciones.ErrorInternoException;
import com.elektra.frecuencias.modelos.ModeloRespuestaSp;
import com.elektra.frecuencias.utilidades.Constantes;
import com.baz.log.LogServicio;
import oracle.jdbc.OracleTypes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
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
public class DaoFrecuenciaSp {

  private static final String NOMBRE_CLASE = "DaoFrecuenciaSp";

  /**
   * Inyeccion de objeto para conexion a DB
   */
  @Inject
  private DaoFabricaConexion daoFabricaConexion;

  /**
   * <b>frecuenciaNombreSp</b>
   * @descripcion: Metodo ejecuto un StoredProcedure que obtiene una tabla de 3 columnas y guarda la informacion en un mode lo de datos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 07/06/22
   */

  @Transactional
  public ModeloRespuestaSp ejecutarSp(String nombre, BigDecimal tipoDato, LogServicio log) throws SQLException{

    final String NOMBRE_METODO = "ejecutarSp";
    log.iniciarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO,Constantes.NOMBRE_MS);
    /*
    constants para indice de sp
     */
    final int INDICE_NOMBRE = 1;
    final int INDICE_TIPO_DATO = 2;
    final int INDICE_CURSOR_SALIDA = 3;
    final int INDICE_CODIGO_EXITO = 4;
    final int INDICE_MENSAJE_EXITO = 5;

    //nombre de sp
    String SP_CONSULTA_FRECUENCIA = "call C3MULTIMARCAS.PAFONETICO03.SPCONSULTAFRECU(?, ?, ?, ?, ?)";
    log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO, "SP ejecutado: "+ SP_CONSULTA_FRECUENCIA);
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
       * Solicita conexion
       */
      conexion = daoFabricaConexion.obtenerConexion();

      /*
       * Asigna cadena de llamada a SP
       */
      declaracion = conexion.prepareCall(SP_CONSULTA_FRECUENCIA); //("{ call C3MULTIMARCAS.PAFONETICO03.SPCONSULTAFRECU(?, ?, ?, ?, ?) }");

      /*
       * Define parametros de procedimiento y asigna valores de entrada
       */
      declaracion.setString(INDICE_NOMBRE, nombre);
      declaracion.setBigDecimal(INDICE_TIPO_DATO, tipoDato);
      declaracion.registerOutParameter(INDICE_CURSOR_SALIDA, OracleTypes.REF_CURSOR);
      declaracion.registerOutParameter(INDICE_CODIGO_EXITO, OracleTypes.VARCHAR);
      declaracion.registerOutParameter(INDICE_MENSAJE_EXITO, OracleTypes.VARCHAR);

      /*
       * Ejecuta procedimiento
       */
      declaracion.executeQuery();

      /*
       * Obtiene respuestas del procedimiento
       */
      resultSet = (ResultSet) declaracion.getObject(INDICE_CURSOR_SALIDA);

      if(declaracion.getString(INDICE_MENSAJE_EXITO).isEmpty()){

        log.registrarMensaje(NOMBRE_CLASE+NOMBRE_METODO,
          "Resultado Vacio de SPCONSULTAFRECU");

      }
      else {
        /*
        iteracion de cursor
         */
        while (resultSet.next()){

          cursor.setFNREGISTROS(resultSet.getBigDecimal("FNREGISTROS"));
          cursor.setFNTOTAL(resultSet.getBigDecimal("FNTOTAL"));
          cursor.setFNUSUARIO(resultSet.getString("FCUSUARIO"));

        }

      }

    }
    catch (Exception e){
      log.registrarExcepcion(e,"Mensaje al ejecutar sp");
      throw new ErrorInternoException(Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,Constantes.ZERO_BY_DEFAULT,
        Constantes.ZERO_BY_DEFAULT,e.getMessage(),Constantes.MENSAJE_CODIGO500);
    }
    finally {
      /*
      cerrar conexion a base postgress
      */
      assert conexion !=null;
      assert declaracion !=null;
      assert resultSet != null;
      daoFabricaConexion.cerrarConexion(conexion,declaracion,resultSet);
    }
    log.obtenerTiempoTotal(NOMBRE_CLASE+NOMBRE_METODO);
    log.terminarTiempoMetodo(NOMBRE_CLASE+NOMBRE_METODO);
    return cursor;
  }
}
