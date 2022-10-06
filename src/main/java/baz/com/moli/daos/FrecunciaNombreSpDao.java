package baz.com.moli.daos;

import baz.com.moli.models.CursorRespuestaSpModel;
import baz.com.moli.utils.Constantes;
import com.baz.iservicios.DaoUtilsService;
import com.baz.servicios.DaoUtils;
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
public class FrecunciaNombreSpDao {
  /**
   * Inyeccion de objeto para conexion a DB
   */
  @Inject
  private FabricaConexionDao fabricaConexionDao;

  /**
   * <b>frecuenciaNombreSp</b>
   * @descripcion: Metodo ejecuto un StoredProcedure que obtiene una tabla de 3 columnas y guarda la informacion en un mode lo de datos
   * @autor: Francisco Javier Cortes Torres, Desarrollador
   *
   * @ultimaModificacion: 07/06/22
   * @return
   */

  @Transactional
  public CursorRespuestaSpModel ejecutarSp(String nombre, BigDecimal tipoDato) throws SQLException{

    DaoUtilsService daoUtilsService = new DaoUtils();

    //nombre de sp
    //log.registrarMensaje(nombreClaseMetodo, "SP: "+ SP_CONSULTA_FRECUENCIA);
    String SP_CONSULTA_FRECUENCIA = daoUtilsService.obtenerProcedure(
      Constantes.C3MULTIMARCAS, "PAFONETICO03",
      "SPCONSULTAFRECU", 5);

    Connection conexion = null;
    CallableStatement declaracion = null;
    ResultSet resultSet = null;

    CursorRespuestaSpModel cursor = new CursorRespuestaSpModel();

    try {

      /*
       * Solicita conexion
       */
      conexion = fabricaConexionDao.obtenerConexion();

      /*
       * Asigna cadena de llamada a SP
       */
      declaracion = conexion.prepareCall(SP_CONSULTA_FRECUENCIA); //("{ call C3MULTIMARCAS.PAFONETICO03.SPCONSULTAFRECU(?, ?, ?, ?, ?) }");

      /*
       * Define parametros de procedimiento y asigna valores de entrada
       */
      declaracion.setString(1, nombre);
      declaracion.setBigDecimal(2, tipoDato);
      declaracion.registerOutParameter(3, OracleTypes.REF_CURSOR);
      declaracion.registerOutParameter(4, OracleTypes.VARCHAR);
      declaracion.registerOutParameter(5, OracleTypes.VARCHAR);

      /*
       * Ejecuta procedimiento
       */
      declaracion.executeQuery();

      /*
       * Obtiene respuestas del procedimiento
       */
      resultSet = (ResultSet) declaracion.getObject(3);

      if(declaracion.getString(5).isEmpty()){

        //log.registrarMensaje(nombreClaseMetodo, "Resultado Vacio de SPCONSULTAFRECU");

      }
      else {

        while (resultSet.next()){

          cursor.setFNREGISTROS(resultSet.getBigDecimal("FNREGISTROS"));
          cursor.setFNTOTAL(resultSet.getBigDecimal("FNTOTAL"));
          cursor.setFNUSUARIO(resultSet.getString("FCUSUARIO"));

        }

      }

    }
    catch (SQLException | ClassNotFoundException e){

    }
    finally {
      /*
      cerrar conexion a base postgress
      */
      assert conexion !=null;
      assert declaracion !=null;
      assert resultSet != null;
      fabricaConexionDao.cerrarConexion(conexion,declaracion,resultSet);
    }
    return cursor;
  }
}
