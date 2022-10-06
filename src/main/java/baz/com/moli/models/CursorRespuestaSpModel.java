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
   * <b>getFnRegistro</b>
   * @descripcion: Método getter que reemplaza valores null por cero
   * @autor: Diego Vázquez Pérez, Desarrollador
   * @ultimaModificacion: 08/07/2022
   */
  public BigDecimal getFnRegistros(){
    if (this.FNREGISTROS == null){
      return this.FNREGISTROS == null ? BigDecimal.valueOf(0) : this.FNREGISTROS;
    }
    else {
      return this.FNREGISTROS;
    }
  }

  public BigDecimal getFnTotal(){
    if (this.FNTOTAL == null){
      return this.FNTOTAL == null ? BigDecimal.valueOf(0) : this.FNTOTAL;
    }
    else {
      return this.FNTOTAL;
    }
  }

  public String getFnUsuario(){
    if (this.FNUSUARIO == null){
      return this.FNUSUARIO == null ? "" : this.FNUSUARIO;
    }
    else {
      return this.FNUSUARIO;
    }
  }

}
