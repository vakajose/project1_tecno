package negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

public final class Cadena {
	public Cadena() {
		// TODO Auto-generated constructor stub
	}
	public static boolean esSubject(String subject){
		return Constantes.CONTENIDO.equalsIgnoreCase(subject);
	}
	public static boolean esForm(String subject){
		return Constantes.FORM.equalsIgnoreCase(subject);
	}
	public static boolean esFecha(String subject){
		return Constantes.FECHA.equalsIgnoreCase(subject);
	}
        public static boolean esImagen(String subject){
		return Constantes.IMAGEN.equalsIgnoreCase(subject);
	}
	public static void main(String[] args) {
		/* String aa = "INS :Categoria,10,Nuevo";
		 StringTokenizer st = new StringTokenizer(aa, ":");
                 String comando = st.nextToken();
                 String otros = st.nextToken();
		 System.out.println( "Comando : "+ comando );
                 System.out.println(  otros );
                 
                 StringTokenizer st1 = new StringTokenizer(otros, ",");
                 String tabla = st1.nextToken();
                  System.out.println( "Tabla : "+ tabla );
                 while(st1.hasMoreTokens()){
                      System.out.println( "datos :"+ st1.nextToken() );
                 }
                 */
            String aux = "Ivan Hugo <grupo03sa@virtual.fcet.uagrm.edu.bo>";
            StringTokenizer st2 = new StringTokenizer(aux, "|");
            
            System.out.println(st2.nextElement());
           // st2.nextElement();
           /// String b = st2.nextToken();
           // System.out.println(b.substring(0, b.length()-1));
            
            
	}
	public static boolean esComando(String cmd){
		cmd  = cmd.trim();
		if(cmd.equalsIgnoreCase(Constantes.REGISTRAR)||cmd.equalsIgnoreCase(Constantes.MODIFICAR)
                        ||cmd.equalsIgnoreCase(Constantes.AYUDA)
                        ||cmd.equalsIgnoreCase(Constantes.ELIMINAR)
                        ||cmd.equalsIgnoreCase(Constantes.MOSTRAR)
                        ||cmd.equalsIgnoreCase(Constantes.REPORTE)
                        ||cmd.equalsIgnoreCase(Constantes.ESTADISTICAS))
			return true;
		return false;
	}
	public static  String getContenido(String cmd,String comando){
             // String aa = "Subject: LIST :  [categoria,2015-05-05,15.5,holamundo,12]";
             // String aa = "[categoria]";
		String aux = null;
		cmd  = cmd.trim();
		cmd = cmd.substring(1, cmd.length()-1);
                StringTokenizer st = new StringTokenizer(cmd, ",");
		String    nombre = st.nextToken();
		if(!comando.equalsIgnoreCase(Constantes.AYUDA)){
                   
                    String cont = cmd.substring(nombre.length()+1,cmd.length());
                   return nombre.trim()+"|"+cont.trim();
                }else{
                     return nombre.trim();
                }
		
	}
        public static String getCorreo(String cadena){
            String aux="";
            StringTokenizer st = new StringTokenizer(cadena, " ");
            if(st.countTokens()>1){
               StringTokenizer st2 = new StringTokenizer(cadena, "<");
               st2.nextToken();
                aux = st2.nextToken();
                aux = aux.substring(0, aux.length()-1);
            }else{
                aux = st.nextToken();
            }
            
            return aux; 
        }
	public static  String  getLineaSubject( ArrayList<String> lista){
		String res = "";
		for (int i = 0; i < lista.size(); i++) {
			String aux = lista.get(i);
			//, Subject: PRUEBAAA
			aux = aux.trim();
			StringTokenizer st = new StringTokenizer(aux, ":");
			if(st.hasMoreTokens()){
				if(Cadena.esSubject(st.nextToken()))
						return aux;
			}
		}
		return res;
	
	}
}
