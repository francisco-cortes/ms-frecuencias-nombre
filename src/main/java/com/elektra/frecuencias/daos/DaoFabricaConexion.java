package com.elektra.frecuencias.daos;

import com.elektra.frecuencias.propiedades.Properties;
import com.elektra.frecuencias.utilidades.Constantes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;

/**
        * <b>DaoFabricaConexion</b>
        * @descripcion: breve descripción del contenido
        * @autor: Francisco Javier Cortes Torres, Desarrollador
        * @ultimaModificacion: 06/10/22
       */
@ApplicationScoped
public class DaoFabricaConexion {

  /**
   * Inyección de propiedades
   */
  @Inject
  private Properties properties;

  private Connection conexion;

  /**
   * obtenerConexion
   * Descrpcion: creoa le objeto SQL.Connection
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: paisCero(String)
   * returns: String
   **/
  public Connection obtenerConexion() throws ClassNotFoundException, SQLException {
    /*
    propiedades necesarias
     */
    System.setProperty("oracle.jdbc.fanEnabled","false");
    Class.forName("oracle.jdbc.OracleDriver");
    /*
    cadena de coenxion otrogada por servicios de base datos
     */
    String cadenaConexion = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(LOAD_BALANCE=ON) " +
      "(ADDRESS=(PROTOCOL=TCP)(HOST=" + properties.conexionesdb().get(Constantes.C3REMESASC).ip() + ")(PORT="
      + properties.conexionesdb().get(Constantes.C3REMESASC).port() + "))" +
      ")(CONNECT_DATA=(SERVICE_NAME=" + properties.conexionesdb().get(Constantes.C3REMESASC).name() +
      ")(SERVER=DEDICATED)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETIRES=180)(DELAY=5))))";
    /*
    construlle y retorna el objeto connection a treves del drive manager
     */
    return DriverManager.getConnection(cadenaConexion,
      properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().usuario(),
      properties.conexionesdb().get(Constantes.C3REMESASC).credenciales().contrasena());

  }

  /**
   * cerrarConexion
   * Descrpcion: Cierra y termina los procesos resultantes de la consulta a base de datos
   * Autor: Francisco Javier Cortes Torres, Desarrollador
   * params: Connection, Statement, ResultSet
   * returns: Void
   **/
  public void cerrarConexion(Connection conexion, Statement declaracionInvocable, ResultSet resultado)
    throws SQLException {

    if (!conexion.isClosed()) {
      conexion.close();
    }

    if(!resultado.isClosed()){
      resultado.close();
    }

    if (!declaracionInvocable.isClosed()){
      declaracionInvocable.close();
    }
  }

}
