package baz.com.moli.models;

import baz.com.moli.utils.Constantes;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CursorRespuestaSpModel {

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
          * @descripcion: obtiene
          * @autor: Método getter que reemplaza valores null por cero
          * @params:
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
          * @descripcion: Método getter que reemplaza valores null por cero
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @params:
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

  /**
          * <b>getFnUsuario</b>
          * @descripcion: Método getter que reemplaza valores null por cero
          * @autor: Francisco Javier Cortes Torres, Desarrollador
          * @params:
          * @ultimaModificacion: 06/10/22
        */


  public String getFnUsuario(){
    if (this.FNUSUARIO == null || this.FNUSUARIO.isEmpty() ){
      return this.FNUSUARIO == null ? "" : this.FNUSUARIO;
    }
    else {
      return this.FNUSUARIO;
    }
  }

}
