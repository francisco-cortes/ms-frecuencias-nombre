package com.elektra.frecuencias.modelos;

import com.elektra.frecuencias.util.Constantes;
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

  /**
   * <b>getFnRegistros</b>
   * @descripcion: Método getter obtiene valor de registros
   * o que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 06/10/22
   */
  public BigDecimal getFnRegistros(){
    if (this.FNREGISTROS == null || this.FNREGISTROS.intValue() == Constantes.ZERO_DEFUAULT){
      return this.FNREGISTROS == null ? BigDecimal.valueOf(0) : this.FNREGISTROS;
    }
    else {
      return this.FNREGISTROS;
    }
  }

  /**
   * <b>getFnTotal</b>
   * @descripcion: Método getter obtiene valor de registros
   * o que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 06/10/22
   */
  public BigDecimal getFnTotal(){
    if (this.FNTOTAL == null || this.FNREGISTROS.intValue() == Constantes.ZERO_DEFUAULT){
      return this.FNTOTAL == null ? BigDecimal.valueOf(0) : this.FNTOTAL;
    }
    else {
      return this.FNTOTAL;
    }
  }

}
