package comunicacion;

import java.io.*;
import java.net.*;

import org.apache.commons.mail.DefaultAuthenticator;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Smtp {

    private String servidor = "mail.tecnoweb.org.bo";
    // String servidor="virtual.ficct.uagrm.edu.bo";
    private String user_receptor = "grupo15sa@tecnoweb.org.bo";
    private String user_emisor = "grupo15sa@tecnoweb.org.bo";
    //private String user_emisor = "grupo03sa@ficct.uagrm.edu.bo";
    private String line;
    private String comando = "";
    private int puerto = 25;

    public Smtp() {
    
        servidor = "mail.tecnoweb.org.bo";

        user_receptor = "grupo15sa@tecnoweb.org.bo";
   
        user_emisor = "grupo15sa@tecnoweb.org.bo";
        line = "";
        comando = "";
        puerto = 25;
    }

    public Smtp(String servidor, String user_emisor) {

        this.servidor = servidor;
        this.user_emisor = user_receptor;
    //    this.user_emisor = user_emisor;
        line = "";
        comando = "";
        puerto = 25;

    }

    public void escribirCorreo(String para,String subject ,String contenido) {
        try {
            this.user_receptor = para;
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine());

                comando = "EHLO " + servidor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + getMultiline(entrada));

                comando = "MAIL FROM : " + user_emisor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "RCPT TO : " + user_receptor + " \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "DATA\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
         
                comando="Subject: "+subject+" \r\n"+" \n\n\n"+contenido+" \n"+".\r\n";
            
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine());
            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
            System.out.println("se envio el correo ");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("no se pudo conectar  " + e.getMessage());
        }
    }

    public String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.charAt(3) == ' ') {
                lines = lines + "\n" + line;
                // No more lines in the server response
                break;
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }

    public static void main(String[] args) {
        Smtp sender = new Smtp();
        sender.escribirCorreo("yocarlos666@gmail.com","pruebas", "envio de pruebassss");

    }

    public void sendmail() throws EmailException {
        HtmlEmail email = new HtmlEmail();

        // GMail SMTP configuration
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        DefaultAuthenticator n = new DefaultAuthenticator("","");
      //  email.setAuthenticator(n);
        email.setSSLOnConnect(true);

        // Addresses and the subject
        email.setFrom("javacirecep.com <yourname@gmail.com>");
        email.addTo("somereceiver@domain.com");
        email.setSubject("Apache Commons Mail Test");

        // HTML message
        email.setHtmlMsg("<h1>Test</h1><div>This is a test e-mail sent by <b>Apache Commons Email</b> library!</div>");

        // Plain text message
        email.setTextMsg("This is fallback message for email clients not supporting HTML messages!");

        // Attachment
      /*  EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("files/me.jpg");
        attachment.setName("My Photo.jpg");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        email.attach(attachment);*/

        email.send();
    }

}
