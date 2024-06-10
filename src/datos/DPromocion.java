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
public class DPromocion {
    private Integer id;
    private Date fechai;
    private Date fechaf ;
    private Integer porcentaje ;
    private Integer idproducto ;
    private final Conexion con ;
    
    public DPromocion(){
        con = new Conexion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechai() {
        return fechai;
    }

    public void setFechai(Date fechai) {
        this.fechai = fechai;
    }

    public Date getFechaf() {
        return fechaf;
    }

    public void setFechaf(Date fechaf) {
        this.fechaf = fechaf;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }
    
    public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from promocion_insertar('" +  getFechai()+ "','" + getFechaf()+ "'," +getPorcentaje()+ "," + getIdproducto()+ ",'" + user+ "','" + pass +"');");
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
        String query = "select * from promocion_listar('" +user+ "','" + pass +"');";
        String resul="";
         System.out.println(query);
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("ID");
            titulos.add("FECHA INICIO");
            titulos.add("FECHA FIN");
            titulos.add("PORCENTAJE");
            titulos.add("IDPRODUCTO");
            titulos.add("PRODUCTO");
            resul = Constantes.HTML(titulos, con.list(query,6));
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>PROMOCION</h2> <br>"+ resul;
    }
    public int modificar(String user,String pass){
        int i=-2;
        
        String query = (String) ("select * from promocion_modificar("+  getId()+ ",'" +  getFechai()+ "','" + getFechaf()+ "'," +getPorcentaje()+ "," + getIdproducto()+ ",'" + user+ "','" + pass +"');");
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
        String query = "select * from promocion_eliminar(" +getId()+ ",'" + user+ "','" + pass +"');";
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
