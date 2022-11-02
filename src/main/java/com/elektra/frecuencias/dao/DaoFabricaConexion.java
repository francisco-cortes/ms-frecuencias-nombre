package com.elektra.frecuencias.dao;

import com.elektra.frecuencias.propiedades.Propiedades;
import com.elektra.frecuencias.util.Constantes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>DaoFabricaConexion</b>
 * @descripcion: Administra las conexiones a Base de Datos.
 * @autor: Francisco Javier Cortes Torres, Desarrollador
 * @ultimaModificacion: 06/10/22
 */
@ApplicationScoped
public class DaoFabricaConexion {

  /**
   * Inyección de propiedades
   */
  @Inject
  private Propiedades propiedades;

  /**
   * <b>obtenerConexion</b>
   * @descripcion: crea le objeto SQL.Connection
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: paisCero(String)
   **/
  public Connection obtenerConexion() throws ClassNotFoundException, SQLException {
    /*
    propiedades necesarias
     */
    System.setProperty("oracle.jdbc.fanEnabled","false");
    Class.forName("oracle.jdbc.OracleDriver");
    /*
    cadena de conexión otorgada por servicios de base datos
     */
    String cadenaConexion = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(LOAD_BALANCE=ON) " +
      "(ADDRESS=(PROTOCOL=TCP)(HOST="
      + propiedades.conexionesdb().get(Constantes.C3REMESASC).ip()
      + ")(PORT="
      + propiedades.conexionesdb().get(Constantes.C3REMESASC).port()
      + "))" +
      ")(CONNECT_DATA=(SERVICE_NAME="
      + propiedades.conexionesdb().get(Constantes.C3REMESASC).name() +
      ")(SERVER=DEDICATED)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETIRES=180)(DELAY=5))))";
    /*
    construye y retorna el objeto connection a través del drive manager
     */
    return DriverManager.getConnection(cadenaConexion,
      propiedades.conexionesdb().get(Constantes.C3REMESASC).credenciales().usuario(),
      propiedades.conexionesdb().get(Constantes.C3REMESASC).credenciales().contrasena());
  }

  /**
   * <b>cerrarConexion</b>
   * @descripcion: Cierra y termina los procesos resultantes de la consulta a base de datos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   * @params: Connection, Statement, ResultSet
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
