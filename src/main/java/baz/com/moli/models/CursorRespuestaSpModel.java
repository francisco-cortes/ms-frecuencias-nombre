package baz.com.moli.models;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CursorRespuestaSpModel {

  private BigDecimal FNREGISTROS;
  private BigDecimal FNTOTAL;
  private String FNUSUARIO;

  /**
          * <b>getFnRegistros</b>
          * @descripcion: obtiene
          * @autor: Método getter que reemplaza valores null por cero
          * @params:
          * @ultimaModificacion: 06/10/22
        */

  public BigDecimal getFnRegistros(){
    if (this.FNREGISTROS == null){
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
    if (this.FNTOTAL == null){
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
    if (this.FNUSUARIO == null){
      return this.FNUSUARIO == null ? "" : this.FNUSUARIO;
    }
    else {
      return this.FNUSUARIO;
    }
  }

}
