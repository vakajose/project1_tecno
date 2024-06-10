/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.DInventario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author cmroj
 */
public class NInventario {
    private DInventario datos;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    public NInventario() {
        datos = new DInventario();
    } 
    
    
    public int insertar(String contenido){
        int idinventario = -2;
        try {
            ArrayList<String> lista = Constantes.getContenido(contenido);
            String user =lista.get(lista.size()-2);
            String pass =lista.get(lista.size()-1);
            datos.setFecha(formatter.parse(lista.get(0)));
            String tipo = lista.get(1);
            idinventario = datos.insertar(user,pass);
            if (idinventario > 0) {
                lista.remove(0);
                lista.remove(0);
                lista.remove(lista.size()-1);
                lista.remove(lista.size()-1);

                for (int i = 0; i < lista.size(); i++) {
                    datos.detalle(idinventario,Integer.valueOf(lista.get(i)),Integer.valueOf(lista.get(i+1)),Integer.valueOf(lista.get(i+2)),tipo);
                    i=i+2;
                }
            } else {
                return -2;
            }
        } catch (Exception e) {
            return -2;
        }
       return idinventario;
    }
}
