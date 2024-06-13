/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DPago;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NPago {
    private DPago datos;
    
    public NPago() {
        datos = new DPago();
    }
    
      public int insertar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setTotal(Double.parseDouble(lista.get(0)));
        datos.setIdventa(Integer.valueOf(lista.get(1)));
       return datos.insertar(user,pass);
    }
      
    
    public String mostrar(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
       return datos.mostrar(lista.get(0),lista.get(1));
       
    }
    
    
    public int modificar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setId(Integer.valueOf(lista.get(0)));
        datos.setTotal(Double.parseDouble(lista.get(1)));
        datos.setIdventa(Integer.valueOf(lista.get(2)));
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
