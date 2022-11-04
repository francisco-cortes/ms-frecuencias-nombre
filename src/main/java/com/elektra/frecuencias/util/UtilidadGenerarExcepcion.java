package com.elektra.frecuencias.util;

import com.baz.excepciones.BadRequestException;
import com.baz.excepciones.InternalServerErrorException;
import com.baz.excepciones.NotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;

@Singleton
@Service("UtilidadGenerarExcepcion")
public class UtilidadGenerarExcepcion {
  public void generarExcepcion(String codigoHttp, String codigo, String mensaje, String uid) {
    switch(codigoHttp){
      case Constantes.CODIGO_HTTP_400:
        throw new BadRequestException(Constantes.CODIGO_HTTP_400, codigo, Constantes.MENSAJE_CODIGO_400,
          uid, Constantes.NOMBRE_MS, mensaje);
      case Constantes.CODIGO_HTTP_404:
        throw new NotFoundException(Constantes.CODIGO_HTTP_404, codigo, Constantes.MENSAJE_CODIGO_404,
          uid, Constantes.NOMBRE_MS, mensaje);
      default:
        throw new InternalServerErrorException(Constantes.CODIGO_HTTP_500, codigo, Constantes.MENSAJE_CODIGO_500,
          uid, Constantes.NOMBRE_MS, mensaje);
    }
  }
}
