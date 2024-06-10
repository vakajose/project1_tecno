/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DPromocion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NPromocion {
    private DPromocion datos;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public NPromocion() {
        datos = new DPromocion();
    }
    
    public int insertar(String contenido){
        try {
            ArrayList<String> lista = Constantes.getContenido(contenido);
            String user =lista.get(lista.size()-2);
            String pass =lista.get(lista.size()-1);

            datos.setFechai(formatter.parse(lista.get(0)));
            datos.setFechaf(formatter.parse(lista.get(1)));
            datos.setPorcentaje(Integer.valueOf(lista.get(2)));
            datos.setIdproducto(Integer.valueOf(lista.get(3)));
            return datos.insertar(user,pass);
        } catch (Exception e) {
            return -2;
        }
    }
   public String mostrar(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
       return datos.mostrar(lista.get(0),lista.get(1));
    }
     public int modificar(String contenido){
         try {
            ArrayList<String> lista = Constantes.getContenido(contenido);
            String user =lista.get(lista.size()-2);
            String pass =lista.get(lista.size()-1);
            datos.setId(Integer.valueOf(lista.get(0)));
            datos.setFechai(formatter.parse(lista.get(1)));
            datos.setFechaf(formatter.parse(lista.get(2)));
            datos.setPorcentaje(Integer.valueOf(lista.get(3)));
            datos.setIdproducto(Integer.valueOf(lista.get(4)));
            return datos.modificar(user,pass);
         } catch (Exception e) {
              return -2;
         }
    }
      public int eliminar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setId(Integer.valueOf(lista.get(0)));
       return datos.eliminar(user,pass);
    }
}
