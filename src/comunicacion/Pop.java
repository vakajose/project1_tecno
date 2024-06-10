package comunicacion;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

import negocio.ManejadorCorreo;

public class Pop implements Runnable {
	//private String servidor = "virtual.fcet.uagrm.edu.bo";
        private String servidor = "mail.tecnoweb.org.bo";
//       private String servidor = "172.20.172.126";
	private String usuario = "grupo03sc";
	private String contrasena = "grup003grup003";
	private String comando = "";
	private String linea = "";
	private int cantida = 0;
	private int puerto = 110;
        private long tiempo = 2000;
        private boolean estado = true;
	private ManejadorCorreo manejador ;
	public Pop() {
		// TODO Auto-generated constructor stub
		manejador = new ManejadorCorreo(this.servidor,this.usuario);
                estado = true;
	}
        public Pop(String servidor, String usuario, String contrasena) {
		// super();
		this.servidor = servidor;
		this.usuario = usuario;
		this.contrasena = contrasena;
		manejador = new ManejadorCorreo(servidor, usuario);
                estado = true;
	}
        
	public Pop(String servidor, String usuario, String contrasena, String comando, String linea, int puerto) {
		// super();
		this.servidor = servidor;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.comando = comando;
		this.linea = linea;
		this.puerto = puerto;
                manejador = new ManejadorCorreo(servidor, usuario);
                estado = true;
	}

	public void LeerCorreo(int numero) {
		try {
			ArrayList<String> cant = new ArrayList<>();
			ArrayList<String> subt = new ArrayList<>();
		
			Socket socket = new Socket(servidor, puerto);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		
			if (socket != null && entrada != null && salida != null) {
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "USER " + usuario + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "PASS " + contrasena + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "LIST \r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + getMultiline(entrada) + "\r\n");
			
				comando = "RETR "+numero+"\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("///");
				 subt = getMultilineList(entrada);
				 System.out.println("S : "+subt.toString()+"\r\n");
                                 
			
				System.out.println("////");
				comando = "QUIT\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");
				
			if(manejador.cantidad > this.cantida)
			    manejador.procesarCorreo(subt);
				
			else
			    System.out.println("NADA Q PROCESAR");
			}
			
			salida.close();
			entrada.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(" S : no se pudo conectar con el servidor indicado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException ex) {
                Logger.getLogger(Pop.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        public ArrayList<String> LeerCorreoLista(int numero) {
            ArrayList<String> subt = new ArrayList<>();
		try {
			ArrayList<String> cant = new ArrayList<>();
			
		
			Socket socket = new Socket(servidor, puerto);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		
			if (socket != null && entrada != null && salida != null) {
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "USER " + usuario + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "PASS " + contrasena + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "LIST \r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + getMultiline(entrada) + "\r\n");
			
				comando = "RETR "+numero+"\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("///");
				 subt = getMultilineList(entrada);
				 System.out.println("S : "+subt.toString()+"\r\n");
				
				System.out.println("////");
				comando = "QUIT\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");
				
			}
			
			salida.close();
			entrada.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(" S : no se pudo conectar con el servidor indicado");
		} catch (IOException e) {
			e.printStackTrace();
		}
                return subt;
	}
	public boolean hayCorreoNuevo() {
		boolean sw = false;
		try {
			ArrayList<String> cant = new ArrayList<>();
			
			Socket socket = new Socket(servidor, puerto);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			if (socket != null && entrada != null && salida != null) {
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "USER " + usuario + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "PASS " + contrasena + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "LIST \r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				//System.out.println("S : " + getMultiline(entrada) + "\r\n");
				cant = getMultilineList(entrada);
				manejador.obtenerCantidadCorreo(cant);
			        System.out.println("S : "+cant.toString()+"\r\n");

			
				comando = "QUIT\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");
				
				
				
			
			}
			// Cerramos los flujos de salida y de entrada y el socket cliente
			salida.close();
			entrada.close();
			socket.close();
			if(manejador.cantidad > this.cantida)
				return true;

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(" S : no se pudo conectar con el servidor indicado");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sw;
	}
	public void obtenerCantidadCorreos() {
		boolean sw = false;
		try {
			ArrayList<String> cant = new ArrayList<>();
			
			Socket socket = new Socket(servidor, puerto);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		
			if (socket != null && entrada != null && salida != null) {
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "USER " + usuario + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "PASS " + contrasena + "\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");

				comando = "LIST \r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				//System.out.println("S : " + getMultiline(entrada) + "\r\n");
				cant = getMultilineList(entrada);
				manejador.obtenerCantidadCorreo(cant);
			    System.out.println("S : "+cant.toString()+"\r\n");

			
				comando = "QUIT\r\n";
				System.out.print("C : " + comando);
				salida.writeBytes(comando);
				System.out.println("S : " + entrada.readLine() + "\r\n");
			}
		
			salida.close();
			entrada.close();
			socket.close();
			this.cantida  = this.manejador.cantidad;
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println(" S : no se pudo conectar con el servidor indicado");
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
                        System.out.println(" S : no se pudo conectar con el servidor indicado");
		}
		
	}


	public void mostrarLista(ArrayList<String> lista) {
		System.out.println("Mostrar lista");
		for (int i = 0; i < lista.size(); i++) {
			System.out.println("" + lista.get(i));
		}
		String aa = "Subject: REG:[aaa,ddd,d]";
		System.out.println("lgonitud  " + aa.equalsIgnoreCase("subjecT:"));
		System.out.println("lgonitud  " + aa.substring(0, 2));
	}

	public String getMultiline(BufferedReader in) throws IOException {
		ArrayList<String> salida = new ArrayList<>();
		String lines = "";
		while (true) {
			String line = in.readLine();
			if (line == null) {
				// Server closed connection
				throw new IOException(" S : Server unawares closed the connection.");
			}
			if (line.equals(".")) {
				// No more lines in the server response
				break;
			}
			if ((line.length() > 0) && (line.charAt(0) == '.')) {
				// The line starts with a "." - strip it off.
				line = line.substring(1);
			}
			// Add read line to the list of lines
			lines = lines + "\n" + line;
		}
		return lines;
	}

	public ArrayList<String> getMultilineList(BufferedReader in) throws IOException {
		ArrayList<String> salida = new ArrayList<>();
		String lines = "";
		while (true) {
			String line = in.readLine();
			if (line == null) {
				// Server closed connection
				throw new IOException(" S : Server unawares closed the connection.");
			}
			if (line.equals(".")) {
				// No more lines in the server response
				break;
			}
			if ((line.length() > 0) && (line.charAt(0) == '.')) {
				// The line starts with a "." - strip it off.
				line = line.substring(1);
			}
			// Add read line to the list of lines
			salida.add(line);
			lines = lines + "\n" + line;
		}
		return salida;
	}

	@Override
	public void run() {
		this.obtenerCantidadCorreos();
		System.out.println(" Cantidad de Correos al iniciar "+this.cantida);
		System.out.println("  "+this.tiempo);
		System.out.println("  "+this.estado);
		while(estado){
			try {
				if(hayCorreoNuevo()){
					int cant = manejador.cantidad-this.cantida;
					System.out.println(" correo nuevos "+cant);
					for (int i = 0; i <cant; i++) {
						int aux = this.cantida+i+1;
						LeerCorreo(aux);
						
					}
					this.cantida = this.cantida + cant;
					
				}else{
					System.out.println(" No hay correos nuevos ");
				}
				Thread.sleep(tiempo);
				  
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Pop p = new Pop();
		Thread h = new Thread(p);
		h.start();
	}

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }
    

}
