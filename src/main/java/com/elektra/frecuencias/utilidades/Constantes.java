package com.elektra.frecuencias.utilidades;

import java.math.BigDecimal;

public class Constantes {

  public final static String NOMBRE_MS = "ms-frecuencias";
  public final static String HTTP_200 = "200";
  public final static String HTTP_201 = "201";
  public final static String HTTP_500 = "500";


  public static final String C3REMESASC = "c3remesas";
  public final static String C3MULTIMARCAS = "C3MULTIMARCAS";

  public static final BigDecimal TIPO_NOMBRE = BigDecimal.valueOf(1);
  public static final BigDecimal TIPO_APELLIDO = BigDecimal.valueOf(2);
  public static final BigDecimal ZERO_BY_DEFAULT = BigDecimal.valueOf(0);
  public static final int ZERO_DEFUAULT = 0;


  public static final int TAMANO_UDI = 15;
  public static final int CICLOS_UDI = 3;

  public static final String ESTADO_OK = "OK";

  public static final String MENSAJE_EXITO = "Operacion exitosa.";
  public static final String MENSAJE_NO_ENCONTRADO = "No se encontro el nombre en la Base de datos";

  public final static String MENSAJE_CODIGO500 = "Ocurrió un incoveniente al procesar la solicitud.";
}
