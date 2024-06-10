/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DUsuario;
import java.util.ArrayList;

/**
 *
 * @author CARLOS SOLIZ
 */
public class NUsuario {
    private DUsuario datos;
    
    public NUsuario() {
        datos = new DUsuario();
    }
    
      public int insertar(String contenido){
        ArrayList<String> lista = Constantes.getContenido(contenido);
        String user =lista.get(lista.size()-2);
        String pass =lista.get(lista.size()-1);
        datos.setUser(lista.get(0));
        datos.setPass(lista.get(1));
        datos.setCorreo(lista.get(2));
        datos.setNombre(lista.get(3));
        datos.setTelefono(Integer.valueOf(lista.get(4)));
        datos.setTipo(lista.get(5));
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
        datos.setUser(lista.get(1));
        datos.setPass(lista.get(2));
        datos.setCorreo(lista.get(3));
        datos.setNombre(lista.get(4));
        datos.setTelefono(Integer.valueOf(lista.get(5)));
        datos.setTipo(lista.get(6));
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
