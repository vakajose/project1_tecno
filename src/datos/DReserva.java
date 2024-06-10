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
public class DReserva {
    private Integer id;
    private String numero;
    private Date fecha ;
    private double total ;
    private Integer idusuario ;
    private final Conexion con ;
    
    public DReserva(){
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
  public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from reserva_insertar('" + user+ "','" + pass +"');");
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
    public int detalle(Integer idreserva,Integer idproducto , Integer cantidad ){
        int i=0;
        
        String query = (String) ("insert into detallereserva(idreserva,idproducto,cantidad,precio) values(" + idreserva + "," + idproducto + ","+ cantidad+","+ "(select p.precio from producto p where p.id = " + idproducto +"));");
        try {
            System.out.println(query);
            this.con.Consulta(query);
            i++;
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return i;
     }
      public String mostrar(String user , String pass){
        String query = "select * from reserva_mostrar('" +user+ "','" + pass +"');";
        String resul="";
         System.out.println(query);
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("NUMERO");
            titulos.add("FECHA");
            titulos.add("TOTAL");
            titulos.add("PRODUCTO");
            titulos.add("CANTIDAD");
            titulos.add("PRECIO");
            resul = Constantes.HTML(titulos, con.list(query,6));
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>RESERVA</h2> <br>"+ resul;
    }
}
