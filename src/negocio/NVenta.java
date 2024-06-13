/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DVenta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NVenta {
    private DVenta datos;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public NVenta() {
        datos = new DVenta();
    } 
    public int insertar(String contenido){
        int idventa = -2;
        try {
            ArrayList<String> lista = Constantes.getContenido(contenido);
            String user =lista.get(lista.size()-2);
            String pass =lista.get(lista.size()-1);
            datos.setIdusuario(Integer.valueOf(lista.get(0)));
            idventa = datos.insertar(user,pass);
            if (idventa > 0) {
                lista.remove(0);
                lista.remove(lista.size()-1);
                lista.remove(lista.size()-1);

                for (int i = 0; i < lista.size(); i++) {
                    datos.detalle(idventa,Integer.valueOf(lista.get(i)),Integer.valueOf(lista.get(i+1)));
                    i=i+1;
                }
            } else {
                return -2;
            }
        } catch (Exception e) {
            return -2;
        }
       return idventa;
    }
  
  public String mostrar(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
       return datos.mostrar(lista.get(0),lista.get(1));
    }
     public String reporte(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
       return datos.reporte(lista.get(0),lista.get(1));
    }
}
