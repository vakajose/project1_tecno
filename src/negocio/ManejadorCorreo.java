package negocio;

import comunicacion.correo;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.mail.MessagingException;



public class ManejadorCorreo {

    public String cmd;
    public String from;
    public String Date;
    public int cantidad;
    private NUsuario usuarios;
    private NProducto producto;
    private NPromocion promocion;
    private NInventario inventario;
    private NReserva reserva;
    
    
    
    public correo clienteSmtp;

   
    public ManejadorCorreo(String servidor, String usuario) {
      
        clienteSmtp = new correo(servidor, usuario);
        usuarios = new NUsuario();
        producto = new NProducto();
        promocion = new NPromocion();
        inventario = new NInventario();
        reserva = new NReserva();
                
    }

    public void procesarCorreo(ArrayList<String> lista) throws MessagingException {
        obtenerDatosCorreo(lista);
        String lineaSubject = Cadena.getLineaSubject(lista);
        StringTokenizer st = new StringTokenizer(lineaSubject, ":");
        st.nextToken();
        try {

            if (st.hasMoreTokens()) {
                String aux = st.nextToken();
                String acmd = aux.trim();
                if (Cadena.esComando(acmd)) {
                    this.cmd = acmd;
                    if (st.hasMoreTokens()) {
                        String ctd = st.nextToken();
                        ctd = ctd.trim();
                        String contenido = "";

                        contenido = Cadena.getContenido(ctd, cmd);
                        realizarOperacion(cmd, contenido);

                    }else{
                        clienteSmtp.enviar_email(this.from, "AYUDA", Constantes.mostrarAyuda("all"));
                    }

                }
                
            }
        } catch (Exception e) {
            clienteSmtp.enviar_email(this.from, "ERROR A PROCESAR", "Error al intentar obtener datos asegurese que esten los datos correctos  \r\n"+Constantes.mostrarAyuda("all") + e.toString());
        }

    }

    private void obtenerDatosCorreo(ArrayList<String> lista) {
        for (int i = 0; i < lista.size(); i++) {
            String aux = lista.get(i);

            aux = aux.trim();
            StringTokenizer st = new StringTokenizer(aux, ":");
            if (st.countTokens() > 1) {
                if (st.hasMoreTokens()) {
                    String aa = st.nextToken().trim();
                    if (Cadena.esSubject(aa)) {

                        this.cmd = st.nextToken().trim();
                    } else if (Cadena.esForm(aa)) {
                        this.from = Cadena.getCorreo(st.nextToken().trim());
                    } else if (Cadena.esFecha(aa)) {
                        this.Date = st.nextToken().trim();
                    }
                }
            }
        }
    }

    
    //prueba-----
    
    

//----------------------------    
    
    public void obtenerCantidadCorreo(ArrayList<String> lista) {
        //	"+OK 8 messages:"
        String aux = lista.get(0);
        StringTokenizer st = new StringTokenizer(aux.trim(), " ");
        st.nextToken();
        cantidad = Integer.parseInt(st.nextToken());

    }

    public void realizarOperacion(String comando, String contenido) throws Exception {
        StringTokenizer st = new StringTokenizer(contenido, "|");
        String tabla = st.nextToken();
        String cont = "";
        if (st.hasMoreTokens()) {
            cont = st.nextToken();
        }
        
        System.out.println("-----CONTENIDO----  " + contenido);
        if (Constantes.REGISTRAR.equalsIgnoreCase(cmd)) {
            Registrar(tabla, cont);
        } else if (Constantes.MODIFICAR.equalsIgnoreCase(cmd)) {
            Modificar(tabla, cont);
        }else if (Constantes.ELIMINAR.equalsIgnoreCase(cmd)) {
           Eliminar(tabla, cont);
        }else if (Constantes.MOSTRAR.equalsIgnoreCase(cmd)) {
            Mostrar(tabla, cont);
        }else if (Constantes.REPORTE.equalsIgnoreCase(cmd)) {
            Reporte(tabla, cont);
        }else if (Constantes.ESTADISTICAS.equalsIgnoreCase(cmd)) {
            Estadisticas(tabla,cont);
        }

    }
    private void Reporte(String tabla, String cont) throws MessagingException {
       String lista="";
        
      /*  if(tabla.equals("Pagos")){
            lista = pago.Reporte(cont);
        }
        if(tabla.equals("Emergencias")){
            lista = emergencia.Reporte(cont);
        }
       
        if(tabla.equals("Cortes")){
            lista = cortes.Reporte(cont);
        }*/
        
        if(!lista.equals("")){
          clienteSmtp.enviar_email(this.from,"Lista de datos" ,lista);
        }else{
          clienteSmtp.enviar_email(this.from, "ERROR EN REGISTRO", "Verifique el contendio y consulte ayuda");
        }
    }


    private void Registrar(String tabla, String contenido) throws Exception {
        int i =0;
        switch (tabla) {
            case "Usuario" : i = usuarios.insertar(contenido); break;
            case "Producto" : i = producto.insertar(contenido);break;
            case "Promocion" : i = promocion.insertar(contenido);break;
            case "Inventario" : i = inventario.insertar(contenido);break;
            case "Reserva" : i = reserva.insertar(contenido);break;
            
         
        }

        if(i>0){
          clienteSmtp.enviar_email(this.from, "RESPUESTA", tabla+" CREADO");
        }else{
            switch (i) {
                case 0:
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "YA EXISTE "+tabla);
                break;
                
                case -1 :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "NO TIENE PERMISOS");
                break;
                
                default :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "OCURRIO UN ERROR AL INSERTAR VERIFIQUE LOS DATOS");
                break;
            }
          
        }
    }

    private void Modificar(String tabla, String contenido) throws Exception {
        int i =0;
         switch (tabla) {
             case "Usuario":  i = usuarios.modificar(contenido); break;
             case "Producto":  i = producto.modificar(contenido); break;
             case "Promocion":  i = promocion.modificar(contenido); break;
         }

        if(i>0){
          clienteSmtp.enviar_email(this.from, "RESPUESTA", tabla +" SE MODIFICO CORRECTAMENTE");
        }else{
         switch (i) {
                case -1 :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "NO TIENE PERMISOS");
                break;
                
                default :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "OCURRIO UN ERROR AL MODIFICAR, VERIFIQUE LOS DATOS");
                break;
            }
        }
    }

  


//  
     private void Eliminar(String tabla, String contenido) throws Exception {
       int i =0;
       switch (tabla){
           case "Usuario": i = usuarios.eliminar(contenido); break;
           case "Producto": i = producto.eliminar(contenido); break;
           case "Promocion": i = promocion.eliminar(contenido); break;
       } 
        if(i>0){
          clienteSmtp.enviar_email(this.from, "RESPUESTA",tabla+ " SE ELIMINO CORRECTAMENTE");
        }else{
         switch (i) {
                case -1 :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "NO TIENE PERMISOS");
                break;
                
                default :
                    clienteSmtp.enviar_email(this.from, "RESPUESTA", "OCURRIO UN ERROR AL ELIMiNAR, VERIFIQUE LOS DATOS");
                break;
            }
        }
    }
//
    private void Mostrar(String tabla, String cont) throws MessagingException {
       String lista="";
       switch (tabla){
           case "Usuario" : lista = usuarios.mostrar(cont);break;
           case "Producto" : lista = producto.mostrar(cont);break;
           case "Promocion" : lista = promocion.mostrar(cont);break;
           case "Reserva" : lista = reserva.mostrar(cont);break;
           
       }
 
        if(!lista.equals("")){
          clienteSmtp.enviar_email(this.from, "RESPUESTA",lista);
        }else{
          clienteSmtp.enviar_email(this.from, "RESPUESTA", "OCURRIO UN ERROR, VERIFIQUE LOS DATOS");
        }
        
    }
    
     private void Clasificar(String tabla , String cont) throws MessagingException {
       String lista="";
       switch (cont){
          // case "Cliente" : lista = cliente.clsificar();break;
           //case "Producto" : lista = producto.clasificar();break;
          
       }
 
        if(!lista.equals("")){
          clienteSmtp.enviar_email(this.from, "",lista);
        }else{
          clienteSmtp.enviar_email(this.from, "ERROR EN REGISTRO", "Verifique el contendio y consulte ayuda");
        }
        
    }
     
      private void Estadisticas(String tabla , String cont) throws MessagingException {
       String lista="";
       switch (tabla){
         //  case "Medicion" : lista = medicion.estadisticas(cont);break;
          
       }
 
        if(!lista.equals("")){
          clienteSmtp.enviar_email(this.from, "",lista);
        }else{
          clienteSmtp.enviar_email(this.from, "ERROR", "Verifique el contendio y consulte ayuda");
        }
        
    }
      
   
   
}
