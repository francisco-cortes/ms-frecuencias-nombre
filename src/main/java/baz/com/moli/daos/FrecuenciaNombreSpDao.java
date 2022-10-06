package baz.com.moli.daos;

import baz.com.moli.exceptions.ErrorInternoException;
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
public class FrecuenciaNombreSpDao {
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

    final int CANTIDAD_PARAMETROS_SP = 5;
    final int INDICE_NOMBRE = 1;
    final int INDICE_TIPO_DATO = 2;
    final int INDICE_CURSOR_SALIDA = 3;
    final int INDICE_CODIGO_EXITO = 4;
    final int INDICE_MENSAJE_EXITO = 5;

    DaoUtilsService daoUtilsService = new DaoUtils();

    //nombre de sp
    //log.registrarMensaje(nombreClaseMetodo, "SP: "+ SP_CONSULTA_FRECUENCIA);
    String SP_CONSULTA_FRECUENCIA = daoUtilsService.obtenerProcedure(
      Constantes.C3MULTIMARCAS, "PAFONETICO03",
      "SPCONSULTAFRECU", CANTIDAD_PARAMETROS_SP);

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
    catch (SQLException | ClassNotFoundException | NullPointerException e){
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
      fabricaConexionDao.cerrarConexion(conexion,declaracion,resultSet);
    }
    return cursor;
  }
}
