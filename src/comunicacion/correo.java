package comunicacion;

import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.net.InetAddress;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import negocio.Constantes;

public class correo {

    private Smtp cliente;
    public Session sesionCorreo;
    public correo(String servidor, String usuario) {
        cliente = new Smtp(servidor, usuario);
    }

    public correo() {
        cliente = new Smtp();
    }

    private void enviarCoorporativo(String to, String subject, String mensaje) {
        cliente.escribirCorreo(to, subject, mensaje);
    }
// /* ESTA ERA LA PARTE QUE ENVIABA DESDE GMAIL!!!!!!
    private void enviarCorreo(String to, String subject, String mensaje) {
        String host = "smtp.gmail.com";

        final String user = "grupo08scfcet@gmail.com";

        final String password = new String("grupo08grupo08");

        if (!user.equals("") && !password.equals("")) {
            String SMTP_PORT = "465";
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");

            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user, password);
                        }
                    });

            // Compose the message  
            try {
                MimeMessage message = new MimeMessage(session);
                // creates message part
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(message, "text/html");

                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // message.setSubject("Subject");
                // message.setSubject(jTextField5.getText()); 
                // String subject="Prueba de consola de eclipse ";
                message.setSubject(subject);

                //String mensaje=" Mensaje de eclipse ";
                message.setText(mensaje);

                // Send the message 
                Transport.send(message);
                System.out.println("Correo enviado con exito!!!");

                // JOptionPane.showMessageDialog(null,"message sent successfully...");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dear User! Please Enter Email or Password");
        }
    }
//*/
    
     public void EnviarCorreo(String html, String destinatario) throws MessagingException {
        //Properties propiedadesEnvio = System.getProperties();
//        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
//        // propiedadesEnvio.put("mail.smtp.port", "25");
//        propiedadesEnvio.put("mail.smtp.auth", "false");
    String host = "smtp.gmail.com";

        final String user = "grupo08scfcet@gmail.com";

        final String password = new String("grupo08grupo08");

        if (!user.equals("") && !password.equals("")) {
            String SMTP_PORT = "465";
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");

            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");
        // propiedadesEnvio.put("mail.smtp.starttls.enable", "false");
        //sesionCorreo = Session.getDefaultInstance(propiedadesEnvio);
        sesionCorreo = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password); //To change body of generated methods, choose Tools | Templates.
            }

        });
        //sesionCorreo = Session.getInstance(propiedadesEnvio, null);
        Message mensageEnviar = new MimeMessage(sesionCorreo);
        mensageEnviar.setFrom(new InternetAddress(user));
        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensageEnviar.setSubject("Respuesta");
        mensageEnviar.setContent(html, "text/html");
        try {
            Transport.send(mensageEnviar);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

         }
    }
     
     
        public void EnviarCorreoServer(String html, String destinatario) throws MessagingException {
        Properties propiedadesEnvio = System.getProperties();
        propiedadesEnvio.put("mail.smtp.host", Constantes.SERVER);
        // propiedadesEnvio.put("mail.smtp.port", "25");
        propiedadesEnvio.put("mail.smtp.auth", "false");
        // propiedadesEnvio.put("mail.smtp.starttls.enable", "false");
        sesionCorreo = Session.getDefaultInstance(propiedadesEnvio);
//        sesionCorreo = Session.getInstance(propiedadesEnvio, new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("grupo08sc@virtual.fcet.uagrm.edu.bo", "grupo08grupo08"); //To change body of generated methods, choose Tools | Templates.
//            }
//
//        });
//        sesionCorreo = Session.getInstance(propiedadesEnvio, null);
        Message mensageEnviar = new MimeMessage(sesionCorreo);
        mensageEnviar.setFrom(new InternetAddress(Constantes.CORREO_ADMINISTRADOR+Constantes.DOMINIO));
        mensageEnviar.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        mensageEnviar.setSubject("Respuesa");
        mensageEnviar.setContent(html, "text/html");
        try {
            Transport.send(mensageEnviar);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
  
    
    public void enviar_email(String to, String subject, String mensaje) throws MessagingException {
//     if (esCoorporativo(to)) {
//            enviarCoorporativo(to, subject, mensaje);
//        } else {
            //enviarCorreo(to, subject, mensaje);
           // EnviarCorreo(mensaje,to);
           //  EnviarCorreoServer(mensaje, to);   para el mail.tecno
               sendHtmlEmail(to, subject, mensaje);
//        }
    }

    public static void main(String args[]) {
        correo c = new correo();
        c.sendHtmlEmail("yocarlos666@gmail.com","saludo","hola mundo");

    }

    public static boolean esCoorporativo(String to) {
        StringTokenizer st = new StringTokenizer(to, "@");
        st.nextToken();
        String aux = st.nextToken();
        if (aux.equalsIgnoreCase("virtual.fcet.uagrm.edu.bo") || aux.equalsIgnoreCase("ficct.uagrm.edu.bo")) {
            return true;
        }
        return false;
    }
    
    
      public void sendHtmlEmail(String toAddress, String subject, String message) {
        try {
            Properties properties = new Properties();
            properties.load(new correo().getClass().getResourceAsStream("hotmail.properties"));
            Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user"), properties.getProperty("mail.password"));
                }
            };
            Session session = Session.getInstance(properties, auth);
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(properties.getProperty("mail.from")));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(message, "text/html; charset=UTF-8");
            
            
            Transport.send(msg);
            
            System.out.println("Envie MAIL: to=" + toAddress + " subject=" + subject + " data:" + message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
