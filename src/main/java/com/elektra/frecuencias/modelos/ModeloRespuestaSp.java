package com.elektra.frecuencias.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
/**
 * <b>ModeloRespuestaSp</b>
 * @descripcion: breve descripción del contenido
 * @autor: Diego Vázquez Pérez, Desarrollador
 * @ultimaModificacion: 04/11/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class ModeloRespuestaSp {

  /*
  frecuencia del nombre/apellido obtenido
   */
  private BigDecimal FNREGISTROS;
  /*
  total de nombres/apellidos existentes en la base de datos
   */
  private BigDecimal FNTOTAL;
  /*
  usuaria que modifica tabla
   */
  private String FNUSUARIO;

  /**
   * <b>getFnRegistros</b>
   * @descripcion: Método getter obtiene valor de registros
   * o que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 06/10/22
   */
  public BigDecimal getFnRegistros(){
    return this.FNREGISTROS;
  }

  /**
   * <b>getFnTotal</b>
   * @descripcion: Método getter obtiene valor de registros
   * o que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 06/10/22
   */
  public BigDecimal getFnTotal(){
    return this.FNTOTAL;
  }
  /**
   * <b>getFnUsuario</b>
   * @descripcion: Método getter que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 06/10/22
   */
  public String getFnUsuario(){
    return this.FNUSUARIO;
  }

}
