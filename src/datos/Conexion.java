/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author CARLOS
 */
public class Conexion {
     
    private static Connection con = null; 
    private static String driver="org.postgresql.Driver";
    
    
    public Conexion() {
          
    }
    
    public Connection abrirConexion(){
        con = null;
        try
        {   
            Class.forName(driver);
          //  con = DriverManager.getConnection("jdbc:postgresql://mail.tecnoweb.org.bo:5432/db_grupo15sa", "grupo15sa", "grup015grup015");
           con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/grupo03sc", "postgres", "12345");
            //System.out.println("conexion realizada con exito");

        }catch(Exception ee){
            System.out.println(ee.toString()+" conexion fallida");
        } 
        return con;
    }
    
       
    public Connection cerrarConexion(){
        try {
            con.close();
             //System.out.println("Cerrando conexion a ");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        con=null;
        return con;
    }
    
    public void Consulta(String query) throws Exception{
        Statement consulta;
        abrirConexion();
        try {
            consulta = (Statement) con.createStatement();
            consulta.execute(query);
            consulta.close();
            System.out.println("Guardado con Exito");
        } catch (SQLException e) {
            System.out.println(e.toString() + " -> Es el error sql");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Error al guardar");
            throw new Exception();
        }
        
    }
    
    public LinkedList<Object> consultaRes(String query) throws Exception{
        LinkedList<Object> datos = new LinkedList<>();
         Statement Consulta;
        ResultSet resultado = null;
        abrirConexion();
        try {
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                datos.add(resultado.getObject(1));
            }
            Consulta.close();

        } catch (Exception e) {
            System.out.println("no se pudo carga la tabla");
        }
        
        return datos;
    }
    
    public LinkedList<LinkedList<String>> list(String query,int cantidadAtributos){
        Statement Consulta;
        ResultSet resultado = null;
        abrirConexion();
        LinkedList<LinkedList<String>> datos = new LinkedList<>();
        try {
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                LinkedList<String> tupla = new LinkedList<>();
                for (int i = 0; i < cantidadAtributos; i++) {
                    tupla.add(resultado.getString(i+1));
                }
                datos.add(tupla);
            }
            Consulta.close();

        } catch (Exception e) {
            System.out.println("no se pudo carga la tabla");
        }
        
        return datos;
    }
    
    public LinkedList<String> Lista(String query){
        abrirConexion();
        LinkedList<String> model = new LinkedList<>();
        Statement Consulta;
        ResultSet resultado = null;
        try {
         Consulta = (Statement) con.createStatement();
         resultado = Consulta.executeQuery(query);
            while (resultado.next()) {
                String nombre = resultado.getString(1);
                model.push(nombre);
            }
            Consulta.close();

        } catch (Exception e) {
            System.out.println("no se pudo CARGAR LOS DATOS TABLA");
        }
        return model;
    }
    
    
    public static void main(String args[]) {
        Conexion demo = new Conexion();
        demo.abrirConexion();
    }
}
