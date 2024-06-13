/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.Date;
import java.util.LinkedList;
import negocio.Constantes;

/**
 *
 * @author cmroj
 */
public class DPago {
    private Integer id;
    private Date fecha;
    private double total ;
    private Integer idventa ;
    private final Conexion con ;
    
    public DPago(){
        con = new Conexion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getIdventa() {
        return idventa;
    }

    public void setIdventa(Integer idventa) {
        this.idventa = idventa;
    }
    
    public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from pago_insertar(" +  getTotal()+ "," + getIdventa()+ ",'" + user+ "','" + pass +"');");
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
    
    public String mostrar(String user , String pass){
        String query = "select * from pago_mostrar('" +user+ "','" + pass +"');";
        String resul="";
         System.out.println(query);
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("ID");
            titulos.add("FECHA");
            titulos.add("TOTAL");
            titulos.add("NUMERO DE VENTA");
            titulos.add("USUARIO");
            resul = Constantes.HTML(titulos, con.list(query,5));
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>PAGO</h2> <br>"+ resul;
    }
    
    public int modificar(String user,String pass){
        int i=-2;
        
        String query = (String) ("select * from pago_modificar("+ getId() +"," +  getTotal()+ "," + getIdventa()+ ",'" + user+ "','" + pass +"');");
        System.out.println(query);
         try {
            LinkedList<Object> res = this.con.consultaRes(query);
            System.out.println(res);
            i = (int) res.get(0);
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return i;
    }
    
     public int eliminar(String user , String pass){
        int i=-2;
        String query = "select * from pago_eliminar(" +getId()+ ",'" + user+ "','" + pass +"');";
        System.out.println(query);
        try {
            LinkedList<Object> res = this.con.consultaRes(query);
            System.out.println(res);
            i = (int) res.get(0);
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return i;
    }
     
     
}
