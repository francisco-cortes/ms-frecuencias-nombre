package com.elektra.frecuencias.util;

import java.math.BigDecimal;

public class Constantes {

  /*
  versión
   */
  public final static String NOMBRE_MS = "ms-modulo-hipocoristicos";
  public final static String MS_VERSION = "1.0.0";
  /*
  Respuesta HTTP
   */
  public final static String CODIGO_HTTP_200 = "200";
  public final static String CODIGO_HTTP_400 = "400";
  public final static String CODIGO_HTTP_401 = "401";
  public final static String CODIGO_HTTP_404 = "404";
  public final static String CODIGO_HTTP_500 = "500";

  public static final String MENSAJE_EXITO_200 = "Operacion exitosa.";
  public static final String MENSAJE_NO_ENCONTRADO = "No se encontro el nombre en la Base de datos";

  public static final String MENSAJE_ERROR_SQL = "ERROR SQL al intentar ejecutar el sp";
  public final static String MENSAJE_CODIGO_400 = "Datos de entrada incorrectos, por favor valide su información.";

  public final static String MENSAJE_CODIGO_401 = "No estas autorizado, favor de validar.";
  public final static String MENSAJE_CODIGO_404 = "Recurso no encontrado";
  public final static String MENSAJE_CODIGO_500 = "Ocurrió un problema en el proceso de frecuencias.";

  public final static String CODIGO_ENCONTRADO= "IDCMF00000";
  public final static String CODIGO_NO_ENCONTRADO = "IDCMF00001";
  public final static String CODIGO_ERROR_SQL = "IDCMF00002";
  public final static String CODIGO_DATOS_INCORRECTOS = "IDCMF00003";
  public final static String CODIGO_SOLICITUD_INCORRECTA = "IDCMF00004";
  public final static String CODIGO_NO_AUTORIZADO = "IDCMF00005";
  public final static String CODIGO_ERROR_GENERAL = "IDCMF00006";

  public static final String C3REMESASC = "c3remesas";
  public final static String C3MULTIMARCAS = "C3MULTIMARCAS";
  public static final BigDecimal TIPO_NOMBRE = BigDecimal.valueOf(1);
  public static final BigDecimal TIPO_APELLIDO = BigDecimal.valueOf(2);
  public static final BigDecimal ZERO_BY_DEFAULT = BigDecimal.valueOf(0);
  public static final int ZERO_DEFUAULT = 0;
  public static final int TAMANO_UDI = 15;
  public static final int CICLOS_UDI = 3;
  public static final String ESTADO_OK = "OK";

  public final static boolean ES_REQUERIDO = true;
  public final static boolean NO_REQUERIDO = false;
  public static final String CODIGO_EXITO = "E";
  /*
  Números
  */
  public static final int UNO = 1;
  public static final int DOS = 2;
  public static final int TRES = 3;
  public static final int CUATRO = 4;
  public static final int CINCO = 5;

}
