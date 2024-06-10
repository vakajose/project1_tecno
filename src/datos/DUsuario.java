/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.util.LinkedList;
import negocio.Constantes;

/**
 *
 * @author CARLOS SOLIZ
 */
public class DUsuario {
    private Integer id;
    private String user;
    private String pass ;
    private String correo ;
    private String nombre ;
    private Integer telefono ;
    private String tipo;
    private final Conexion con ;
    
    public DUsuario(){
        con = new Conexion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int insertar(String user , String pass){
        int i=-2;
        
        String query = (String) ("select * from usuario_registrar('" +  getUser()+ "','" + getPass()+ "','" +getCorreo()+ "','" + getNombre()+ "'," + getTelefono()+ ",'" + getTipo()+ "','" + user+ "','" + pass +"');");
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
        int i=0;
        String query = "select * from usuario_listar('" +user+ "','" + pass +"');";
        String resul="";
        try {
            LinkedList<String> titulos = new LinkedList<>();
            titulos.add("ID");
            titulos.add("USER");
            titulos.add("PASS");
            titulos.add("CORREO");
            titulos.add("NOMBRE");
            titulos.add("TELEFONO");
            titulos.add("TIPO");
            resul = Constantes.HTML(titulos, con.list(query,7));
            i++;
        } catch (Exception e) {
            System.out.println("error : " + e.toString());
        }
        return "<h2>USUARIO</h2> <br>"+ resul;
    }
    
    
    public int modificar(String user,String pass){
        int i=-2;
        
         String query = (String) ("select * from usuario_modificar(" + getId()+ ",'" +  getUser()+ "','" + getPass()+ "','" +getCorreo()+ "','" + getNombre()+ "'," + getTelefono()+ ",'" + getTipo()+ "','" + user+ "','" + pass +"');");
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
        String query = "select * from usuario_eliminar(" +getId()+ ",'" + user+ "','" + pass +"');";
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
