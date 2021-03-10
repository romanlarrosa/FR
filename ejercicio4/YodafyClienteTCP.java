//
// YodafyServidorIterativo
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

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio;
		//String buferEnvio;
		byte []buferRecepcion=new byte[256];
		//String buferRecepcion;
		int bytesLeidos=0;
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión UDP
		//Socket socketServicio=null;
		InetAddress direccion;
		DatagramPacket paquete;
		DatagramSocket socketServicio;

		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
			socketServicio = new DatagramSocket();
			//////////////////////////////////////////////////////			
			
			//InputStream inputStream = socketServicio.getInputStream();
			//OutputStream outputStream = socketServicio.getOutputStream();
			//PrintWriter  _outputStream = new PrintWriter(socketServicio.getOutputStream(), true);
			//BufferedReader _inputStream = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
			direccion = InetAddress.getByName(host);
			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
			
			
			// Enviamos el array por el outputStream;
			//////////////////////////////////////////////////////
			// ... .write ... (Completar)
			//outputStream.write(buferEnvio, 0, buferEnvio.length);
			//_outputStream.println(buferEnvio);
			paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
			socketServicio.send(paquete);
			//////////////////////////////////////////////////////
			
			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			//////////////////////////////////////////////////////
			// ... .flush(); (Completar)
			//outputStream.flush();
			//////////////////////////////////////////////////////
			
			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			//////////////////////////////////////////////////////
			// bytesLeidos ... .read... buferRecepcion ; (Completar)
			//bytesLeidos = inputStream.read(buferRecepcion);
			//buferRecepcion = _inputStream.readLine();
			paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);
			socketServicio.receive(paquete);
			//////////////////////////////////////////////////////
			
			// MOstremos la cadena de caracteres recibidos:
			String recibido = new String(paquete.getData(), paquete.getOffset(), paquete.getLength());

			System.out.println("Recibido: " + recibido);
			System.out.println("");
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
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
