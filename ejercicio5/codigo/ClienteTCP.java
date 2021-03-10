//
// ServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class ClienteTCP {

	public static void main(String[] args) {
		
		//byte []buferEnvio;
		String buferEnvio;
		//byte []buferRecepcion=new byte[256];
		String buferRecepcion;
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		Socket socketServicio=null;
		
		try {
			// Creamos un socket que se conecte a "h0st" y "port":
			//////////////////////////////////////////////////////
			socketServicio = new Socket(host, port);
			//////////////////////////////////////////////////////			
			
			PrintWriter  _outputStream = new PrintWriter(socketServicio.getOutputStream(), true);
			BufferedReader _inputStream = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
			boolean jugar = true;
			while (jugar){
				//buferEnvio="Al monte del volcán debes ir sin demora";
				//Cadena de conexion con el servidor
				System.out.println("Quiero jugar");
				String cadenaConexion = "PLAY";
				// Enviamos el String de conexion por el outputStream;
				//////////////////////////////////////////////////////
				_outputStream.println(cadenaConexion);
				//////////////////////////////////////////////////////

				// Recibe las letras con las que tiene que jugar
				//////////////////////////////////////////////////////
				String letras = _inputStream.readLine();
				//////////////////////////////////////////////////////

				//Mostrar las letras y pasar por teclado la palabra
				System.out.println("Estas son las letras con las que juegas: ");
				for (char char1 : letras.toCharArray()) {
					System.out.print(char1 + " ");
				}
				System.out.println("\tCual es tu palabra? ");

				//Leer la palabra del jugador
				String palabra;
				Scanner keyboard = new Scanner(System.in);
				palabra = keyboard.nextLine();
				//keyboard.close();

				//Mandar la palabra al servidor
				// Enviamos el String de conexion por el outputStream;
				//////////////////////////////////////////////////////
				_outputStream.println(palabra);
				//////////////////////////////////////////////////////

				//Solucion
				// Recibe la resolucion de su palabra
				//////////////////////////////////////////////////////
				String solucion = _inputStream.readLine();
				//////////////////////////////////////////////////////
				System.out.println(solucion);

				//Recibe cuantas soluciones encuentra el servidor
				//////////////////////////////////////////////////////
				String nsol = _inputStream.readLine();
				int n = Integer.parseInt(nsol);
				//////////////////////////////////////////////////////
				if(n != 0){
					System.out.println("Estas son las soluciones del servidor: ");
					//Recibe cada una de las palabras y sus puntuaciones
					solucion = _inputStream.readLine();
					System.out.println(solucion);
					//Recibe la mejor solucion
					System.out.println("La mejor solucion es: ");
					solucion = _inputStream.readLine();
					System.out.println(solucion);
				}
				else
					System.out.println("No se ha encontrado ninguna solucion");
				
				//Pregunta si quiere jugar de nuevo
				//System.out.print("EL servidor pregunta: ");
				solucion = _inputStream.readLine();
				System.out.println(solucion);


				//keyboard = new Scanner(System.in);
				String res = keyboard.nextLine();
				//System.out.println("Respuesta: " + res);
				//keyboard.close();
				if (res.contains("S")|| res.contains("s")) {
					System.out.println("Has decidido jugar de nuevo");
					jugar = true;
				}
				else {
					System.out.println("Has decidido no jugar");
					jugar = false;
					//Mandamos al servidor "FIN"
					_outputStream.println("FIN");
				}
					
			}
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socketServicio.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
