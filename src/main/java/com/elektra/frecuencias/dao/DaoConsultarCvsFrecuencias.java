package com.elektra.frecuencias.dao;

import com.elektra.frecuencias.modelos.DickModel;
import com.elektra.frecuencias.modelos.ModeloRespuestaSp;

import javax.inject.Singleton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DaoConsultarCvsFrecuencias {

  public ModeloRespuestaSp consultaDatos(String nombre, String tipoNombre){
    List<DickModel> dick = leerDick();
    ModeloRespuestaSp respuestaSp = new ModeloRespuestaSp();
    int registro = 0;
    int total = 0;

    for(int i = 0; i < dick.size(); i++){
      if(dick.get(i).getFiTipoDato().equals(tipoNombre) && dick.get(i).getFcDatoBeneFic().equals(nombre)){
        registro = Integer.parseInt(dick.get(i).getFnRegistros());
        total = Integer.parseInt(dick.get(i).getFnTotal());
      }
    }
    respuestaSp.setFNREGISTROS(BigDecimal.valueOf(registro));
    respuestaSp.setFNTOTAL(BigDecimal.valueOf(total));
    respuestaSp.setFNUSUARIO("HARD");

    return respuestaSp;
  }

  private List<DickModel> leerDick(){
    List<DickModel> lista = new ArrayList<DickModel>();
    String file = "/deployments/Datos_Frecuencia.csv";
    System.out.println("Entra el try de leer diccionario");
    try {
      BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
      String line;

      while ((line = br.readLine()) != null) {
        String[] cloums = line.split(",");
        if (cloums.length < 4) { // title,author,year
          System.err.println("Some error message here");
          continue;
        }
        lista.add(new DickModel(cloums[0].replace("\"","")
          ,cloums[1],cloums[2],cloums[3]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lista;
  }

}
