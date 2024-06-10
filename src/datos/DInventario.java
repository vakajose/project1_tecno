/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author cmroj
 */
public class DInventario {
    private Integer id;
    private String numero;
    private Date fecha ;
    private Integer idusuario ;
    private final Conexion con ;
    
    public DInventario(){
        con = new Conexion();
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
     public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from inventario_ingresoegreso('" +  getFecha()+ "','" + user+ "','" + pass +"');");
        try {
            System.out.println(query);
            LinkedList<Object> res = this.con.consultaRes(query);
            System.out.println(res);
            i = (int) res.get(0);
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return i;
    }
     
     public int detalle(Integer idinventario,Integer idproducto , Integer cantidad ,double costo,String tipo){
        int i=0;
        
        String query = (String) ("insert into ingresoegreso(idinventario,idproducto,cantidad,costo,tipo) values(" + idinventario + "," + idproducto + ","+ cantidad+","+ costo +",'"+ tipo +"');");
        try {
            System.out.println(query);
            this.con.Consulta(query);
            i++;
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return i;
     }
}
