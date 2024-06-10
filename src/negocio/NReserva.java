/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DReserva;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NReserva {
    private DReserva datos;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public NReserva() {
        datos = new DReserva();
    } 
    
 public int insertar(String contenido){
        int idreserva = -2;
        try {
            ArrayList<String> lista = Constantes.getContenido(contenido);
            String user =lista.get(lista.size()-2);
            String pass =lista.get(lista.size()-1);
            idreserva = datos.insertar(user,pass);
            if (idreserva > 0) {
                lista.remove(lista.size()-1);
                lista.remove(lista.size()-1);

                for (int i = 0; i < lista.size(); i++) {
                    datos.detalle(idreserva,Integer.valueOf(lista.get(i)),Integer.valueOf(lista.get(i+1)));
                    i=i+1;
                }
            } else {
                return -2;
            }
        } catch (Exception e) {
            return -2;
        }
       return idreserva;
    } 

  public String mostrar(String contenido){
       ArrayList<String> lista = Constantes.getContenido(contenido);
       return datos.mostrar(lista.get(0),lista.get(1));
    } 
}
