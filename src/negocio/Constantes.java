package negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;



public final class Constantes {

    public static final String CONTENIDO = "Subject";
    public static final String FORM = "From";
    public static final String FECHA = "Date";
    public static final String IMAGEN = "X-Attachment-Id";
    public static final String RETURNPAHT = "Return-Path";

      public static final String SERVER = "mail.tecnoweb.org.bo";

    //public static final String SERVER = "virtual.fcet.uagrm.edu.bo";
    public static final String DOMINIO = "@tecnoweb.org.bo";
    //public static final String DOMINIO = "@virtual.ficct.uagrm.edu.bo";

    //public static final String DOMINIO = "@virtual.fcet.uagrm.edu.bo";
    public static final String CORREO_SISTEMA = "grupo15sa";
    public static final String PASSWRD_SISTEMA = "grup015grup015";
    public static final String CORREO_ADMINISTRADOR = "grupo15sa";
    public static final String CORREO_DEPOSITO = "invitado";
    
    
    
    public static final String AYUDA = "AYUDA";
    public static final String REGISTRAR = "A";
    public static final String MODIFICAR = "M";
    public static final String ELIMINAR = "E";
    public static final String MOSTRAR = "L";
    public static final String REPORTE = "REPORT";
    public static final String ESTADISTICAS = "ESTADISTICAS";
    
    public static   String head = "";
            
   
    public static String mostrarAyuda(String nombreTabla) {
       String ayuda="";
      
        if (nombreTabla.equals("all")) {
//            ayuda+=   "AYUDA GENERAL ENVIE ESOS COMANDOS PARA VER AYUDA DETALLADA\n\n\n\n" 
//                     +"AYUDA :[perfil]\n "
//                    + "AYUDA : [contactos]\n"
//                    + "AYUDA : [seguido]\n"
//                    + "AYUDA : [seguidores]\n"
//                    + "AYUDA : [buscar]\n"
//                    + "AYUDA : [reportes]\n"
//                    + "AYUDA : [estadisticas]\n"
//                    + "AYUDA : [imagen]\n\n\n";
                ayuda+="<!DOCTYPE html>\n" +
"<html lang=\"en\" dir=\"ltr\">\n" +
"  <head>\n" +
"    <meta charset=\"utf-8\">\n" +
"    <title></title>\n" +
"  </head>\n" +
"  <body>\n" +
"\n" +
"       <h1>SISTEMA DE VENTAS</h1>\n" +
"       <h2>AYUDA</h2>\n" +
"       <li> USUARIOS </li>\n" +
"         <ul>\n" +
"            Adicionar Usuario       =    A : {Usuario,user,pass,correo,nombre,telefono,tipo,administrador,pass administrador} <br>\n" +
"            Listar    Usuario       =    L : {Usuario,user,pass} <br>\n" +
"            Modificar Usuario       =    M : {Usuario,id,pass,correo,nombre,telefono,tipo,administrador,pass administrador}<br>\n" +
"            Eliminar  Usuario       =    E : {Usuario,id,administrador,pass administrador}<br>\n" +
"         </ul>\n" +
"\n" + 
"         <li>PROMOCION</li>\n" +
"         <ul>\n" +
"           Adicionar Promocion       =    A : {Promocion,fechainicio,fechafin,porcentaje,idproducto,administrador,pass administrador} <br>\n" +
"           Listar    Promocion       =    L : {Promocion,administrador,pass administrador} <br>\n" +
"           Modificar Promocion       =    M : {Promocion,id,fechainicio,fechafin,porcentaje,idproducto,administrador,pass administrador}<br>\n" +
"           Eliminar  Promocion       =    E : {Promocion,id,fechainicio,fechafin,porcentaje,idproducto,administrador,pass administrador}<br>\n" +
"         </ul>\n" +
"\n" +
"         <li>PRODUCTO</li>\n" +
"         <ul>\n" +
"             Adicionar Zona       =    A : {Producto,nombre,codigo,precio,costo,cantidad,administrador,pass administrador} <br>\n" +
"             Listar    Zona       =    L : {Producto,administrador,pass administrador}                   <br>\n" +
"             Modificar Zona       =    M : {Producto,id,nombre,codigo,precio,costo,cantidad,administrador,pass administrador}<br>\n" +
"             Eliminar  Zona       =    E : {Producto,id,administrador,pass administrador}<br>\n" +
"         </ul>\n" +
"\n" +
"         <li>INGRESO / EGRESO</li>\n" +
"         <ul>\n" +
"             Adicionar Inventario       =    A : {Inventario,fecha,tipo,idproducto,cantidad,costo,administrador,pass administrador} <br>\n" +
"         </ul>\n" +
"\n" +
"         <li>RESERVA</li>\n" +
"         <ul>\n" +
"           Adicionar    =    A : {Reserva,idproducto,cantidad,cliente,pass cliente}<br>\n" +
"           Listar       =    L : {Reserva,cliente,pass cliente}<br>\n" +
"         </ul>\n" +
"\n" +                          
"         <li>VENTA</li>\n" +
"         <ul>\n" +
"             Adicionar Venta       =    A : {Venta,idproducto,cantidad,administrador,pass administrador} <br>\n" +
"         </ul>\n" +
"\n" + 
"         <li>PAGO</li>\n" +
"         <ul>\n" +
"           Adicionar Pago   =  A : {Pago,idventa,total,administrador,pass administrador}<br>\n" +
"           Listar    Pago   =  L : {Pago,cliente,pass cliente}<br>\n" +
"         </ul>\n" +
"\n" +  
"         <li>Reportes</li>\n" +
"         <ul>\n" +
"           Mostrar    =   REPORT : {Pago,administrador,pass administrador}<br>\n" +
"           Mostrar    =   REPORT : {Ventas,administrador,pass administrador}<br>\n" +
"           Mostrar    =   REPORT : {Inventario,administrador,pass administrador}<br>\n" +
"         </ul>\n" +
"\n" +                      
         " <ul>\n" +
"           Mostrar    =   ESTADISTICAS : {Producto,administrador,pass administrador}\n" +
"         </ul>\n" +
"  </body>\n" +
"</html> ";
        }
        
       
       return ayuda;
    }

    public static String mostarTablas() {
        String aux = "\n"
               ;
        return  aux;
    }
    
    public static String Mostrar(LinkedList<String> titulos , LinkedList<LinkedList<String>> datos){
        int[] intTam = new int[titulos.size()];
        String lineaH = "+";
        String lineaV = "|";
        String cadena = "";
        
        // llenamos de valores 
        for (int i = 0; i < intTam.length; i++) {
            intTam[i] = titulos.get(i).length();
        }
        
        // buscamos a los mayores de sus atributos
        for (LinkedList<String> dato : datos) {
            for (int i = 0; i < dato.size(); i++) {
                if (dato.get(i).length() > intTam[i]) {
                    intTam[i] = dato.get(i).length();
                }
            }
        }
        int longitud = 0;
        longitud = (3*(titulos.size()-1)) +  (2 *(titulos.size()-(titulos.size()-2))) ;
        for (int i = 0; i < titulos.size(); i++) {
             longitud+=intTam[i];
        }
        
        for (int i = 0; i < longitud; i++) {
            if (i==0 || i==longitud-1) {
                cadena+= lineaV;
            }else{
                cadena += lineaH;
            }
            
        }
        cadena += "<br>\n";
        // poner titulos ;
        
        for (int i = 0; i < titulos.size(); i++) {
            if (i==0) {
                cadena+=lineaV +ponerEspacio(titulos.get(i),intTam[i]) + lineaV;
            }else{
                cadena+=ponerEspacio(titulos.get(i),intTam[i])+lineaV;
            }
        }
        cadena += "<br>\n";
        
         for (int i = 0; i < longitud; i++) {
            if (i==0 || i==longitud-1) {
                cadena+= lineaV;
            }else{
                cadena += lineaH;
            }
         }
         cadena += "<br>\n";
        for (LinkedList<String> i : datos) {
           for (int j = 0; j < i.size(); j++) {
            if (j==0) {
                cadena+=lineaV +ponerEspacio(i.get(j),intTam[j]) + lineaV;
            }else{
                cadena+=ponerEspacio(i.get(j),intTam[j])+lineaV;
            }
        }
           cadena += "<br>\n";
        }
        
         for (int i = 0; i < longitud; i++) {
            if (i==0 || i==longitud-1) {
                cadena+= lineaV;
            }else{
                cadena += lineaH;
            }
            
        }
        cadena = "<body>\n"
                + "<div class=\"container\">\n"
                + "\n"
                + "    <h1>Ayuda</h1>\n"
                + "\n"
                + "    <!---------------Administrar Usuario---------->\n"
                + "\n"
                + "    <div class=\"bs-callout bs-callout-primary\">\n"
                + "        <h4>Administrar Usuario</h4>\n"
                + "        Dentro de las opciones tenemos:\n"
                + "        <ul>\n"
                + cadena
                + "        </ul>\n"
                + "    </div>\n"
                + "\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
        System.out.println(cadena);
        
        return cadena;
    }
  
    public static String ponerEspacio(String dato , int tam){
        int suma = tam - dato.length();
        
        for (int i = 0; i < suma; i++) {
            dato+= " ";
        }
        dato = " "+ dato + " ";
        return  dato;
    }
    public static void main(String[] args) throws ParseException {
        
//        LinkedList<String> a = new LinkedList<>();
//        a.add("Nombre");
//        a.add("Precio");
//        a.add("Cantidad");
//        a.add("Marca");
//        
//        LinkedList<String> b = new LinkedList<>();
//        b.add("P1");
//        b.add("10");
//        b.add("3");
//        b.add("samsung");
//        
//        LinkedList<String> b1 = new LinkedList<>();
//        b1.add("Xiaomi");
//        b1.add("10");
//        b1.add("3");
//        b1.add("MI A1");
//        
//        LinkedList<LinkedList<String>> c = new LinkedList<>();
//        c.add(b);
//        c.add(b1);
//        
//        Mostrar(a, c);
String dateString = "03-11-2012";
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    java.util.Date date = dateFormat.parse(dateString);
    
        System.out.println(date.toString());
//              DCliente c = new DCliente();
//              System.out.println(c.mostrar());
    }
    
    public static  String HTML(LinkedList<String> titulos , LinkedList<LinkedList<String>> datos){
        String cadena = "  <table border='2'>"+
                       "<tr bgcolor='#7FFFD4'>";
         for (int i = 0; i < titulos.size(); i++) {
            cadena+= "<th>" + titulos.get(i) + "</th>";
        }
         cadena+= "</tr>";
         for (LinkedList<String> i : datos) {
             cadena+="<tr>";
           for (int j = 0; j < i.size(); j++) {
             cadena+="<td>" + i.get(j) + "</td>";
            }
           cadena += "</tr>";
        }
         cadena+="</table>";
         return cadena;
    }
    
    
     public static ArrayList<String> getContenido(String contenido) {
        ArrayList<String> cont = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(contenido, ",");
        while (st.hasMoreTokens()) {
            cont.add(st.nextToken().trim());
        }
        return cont;
     }
}
