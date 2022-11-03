package com.elektra.frecuencias.modelos;

public class DickModel {

  private String fcDatoBeneFic;

  private String fnRegistros;

  private String fnTotal;

  private String fiTipoDato;

  public DickModel(String fcDatoBeneFic, String fnRegistros, String fnTotal, String fiTipoDato){
    this.fcDatoBeneFic = fcDatoBeneFic;
    this.fnRegistros = fnRegistros;
    this.fnTotal = fnTotal;
    this.fiTipoDato = fiTipoDato;
  }

  public void setFcDatoBeneFic(String fcDatoBeneFic){
    this.fcDatoBeneFic = fcDatoBeneFic;
  }

  public void setFnRegistros(String fnRegistros){
    this.fnRegistros = fnRegistros;
  }

  public void setFnTotal(String fnTotal){
    this.fnTotal = fnTotal;
  }

  public void setFiTipoDato(String fiTipoDato){
    this.fiTipoDato = fiTipoDato;
  }

  ///

  public String getFcDatoBeneFic(){
    return this.fcDatoBeneFic;
  }

  public String getFnRegistros(){
    return this.fnRegistros;
  }

  public String getFnTotal(){
    return this.fnTotal;
  }

  public String getFiTipoDato(){
    return this.fiTipoDato;
  }

}
