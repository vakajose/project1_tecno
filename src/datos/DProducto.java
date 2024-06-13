/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.LinkedList;
import negocio.Constantes;

/**
 *
 * @author cmroj
 */
public class DProducto {
    private Integer id;
    private String nombre;
    private String codigo ;
    private double precio ;
    private double costo ;
    private Integer cantidad ;
    private final Conexion con ;
    
    public DProducto(){
        con = new Conexion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

     public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from producto_registrar('" +  getNombre()+ "','" + getCodigo()+ "'," +getPrecio()+ "," + getCosto()+ "," + getCantidad()+ ",'" + user+ "','" + pass +"');");
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
        String query = "select * from producto_listar('" +user+ "','" + pass +"');";
        String resul="";
         System.out.println(query);
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("ID");
            titulos.add("NOMBRE");
            titulos.add("CODIGO");
            titulos.add("PRECIO");
            titulos.add((!"".equals(user) &&!"".equals(pass)) ? "COSTO":"DESCUENTO %");
            titulos.add("CANTIDAD");
            resul = Constantes.HTML(titulos, con.list(query,6));
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>PRODUCTO</h2> <br>"+ resul;
    }
      public int modificar(String user,String pass){
        int i=-2;
        
        String query = (String) ("select * from producto_modificar("+ getId() +",'" +  getNombre()+ "','" + getCodigo()+ "'," +getPrecio()+ "," + getCosto()+ "," + getCantidad()+ ",'" + user+ "','" + pass +"');");
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
        String query = "select * from producto_eliminar(" +getId()+ ",'" + user+ "','" + pass +"');";
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
    
        public String estadisticas(String user , String pass){
        String query = "select * from producto_estadistica('" +user+ "','" + pass +"');";
        String resul="";
         System.out.println(query);
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("NOMBRE");
            titulos.add("CODIGO");
            titulos.add("CANTIDAD VENDIDA");
            resul = Constantes.HTML(titulos, con.list(query,3));
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>ESTADISTICAS DE PRODUCTOS MAS VENDIDOS</h2> <br>"+ resul;
    }
}
