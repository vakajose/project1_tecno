/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DProducto;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NProducto {
      private DProducto datos;
    
    public NProducto() {
        datos = new DProducto();
    }
    
      public int insertar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setNombre(lista.get(0));
        datos.setCodigo(lista.get(1));
        datos.setPrecio(Double.parseDouble(lista.get(2)));
        datos.setCosto(Double.parseDouble(lista.get(3)));
        datos.setCantidad(Integer.valueOf(lista.get(4)));
       return datos.insertar(user,pass);
    }
      
    
    public String mostrar(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
        if (lista.size() >2) {
            return datos.mostrar(lista.get(0),lista.get(1));
        } else {
            return datos.mostrar("","");
        }
    }
    
    
    public int modificar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setId(Integer.valueOf(lista.get(0)));
        datos.setNombre(lista.get(1));
        datos.setCodigo(lista.get(2));
        datos.setPrecio(Double.parseDouble(lista.get(3)));
        datos.setCosto(Double.parseDouble(lista.get(4)));
        datos.setCantidad(Integer.valueOf(lista.get(5)));
       return datos.modificar(user,pass);
    }
    
    
    public int eliminar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setId(Integer.valueOf(lista.get(0)));
       return datos.eliminar(user,pass);
    }
}
