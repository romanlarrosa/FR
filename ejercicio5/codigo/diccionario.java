import java.util.*;
import java.io.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

class Letra {
    // Variables de instancia
    private char letra;
    private int frecuencia;
    private int puntos;

    // Metodos
    // Constructores
    Letra(char _letra, int _frecuencia, int _puntos) {
        letra = _letra;
        frecuencia = _frecuencia;
        puntos = _puntos;
    }

    Letra(Letra orig) {
        letra = orig.letra;
        frecuencia = orig.frecuencia;
        puntos = orig.puntos;
    }

    // Getters
    public char getLetra(){return letra;}
    public int getFrecuencia(){return frecuencia;}
    public int getPuntos(){return puntos;}
}

class ConjuntoLetras {
    //Variables de instancia
    ArrayList<Letra> conjunto = new ArrayList<Letra>();

    ConjuntoLetras(){}
    ConjuntoLetras(ConjuntoLetras orig)
    {
        conjunto = orig.conjunto;
    }
    public void addLetra(Letra l)
    {
        conjunto.add(l);
    }

    public int puntosDe(char x)
    {
        x = Character.toUpperCase(x);
        for(Letra l: conjunto)
        {
            if (l.getLetra() == x)
            {
                return l.getPuntos();
            }
        }
        return 0;
    }

    public int puntuacion(String palabra, String modo)
    {
        if (modo == "L")
        {
            return palabra.length();
        }
        else
        {
            int puntos = 0;
            for (int i=0; i < palabra.length(); i++)
            {
                puntos += puntosDe(palabra.charAt(i));
            }
            return puntos;
        }
    }
    public void leerLetras()
    {
        Letra aux = null;

        aux = new Letra('A', 12, 1);
        conjunto.add(aux);
        aux = new Letra('E', 12, 1);
        conjunto.add(aux);
        aux = new Letra('O', 9, 1);
        conjunto.add(aux);
        aux = new Letra('I', 6, 1);
        conjunto.add(aux);
        aux = new Letra('S', 6, 1);
        conjunto.add(aux);
        aux = new Letra('N', 5, 1);
        conjunto.add(aux);
        aux = new Letra('L', 4, 1);
        conjunto.add(aux);
        aux = new Letra('R', 6, 1);
        conjunto.add(aux);
        aux = new Letra('U', 5, 1);
        conjunto.add(aux);
        aux = new Letra('T', 4, 1);
        conjunto.add(aux);
        aux = new Letra('D', 5, 2);
        conjunto.add(aux);
        aux = new Letra('G', 2, 2);
        conjunto.add(aux);
        aux = new Letra('C', 5, 3);
        conjunto.add(aux);
        aux = new Letra('B', 2, 3);
        conjunto.add(aux);
        aux = new Letra('M', 2, 3);
        conjunto.add(aux);
        aux = new Letra('P', 2, 3);
        conjunto.add(aux);
        aux = new Letra('H', 2, 4);
        conjunto.add(aux);
        aux = new Letra('F', 1, 4);
        conjunto.add(aux);
        aux = new Letra('V', 1, 4);
        conjunto.add(aux);
        aux = new Letra('Y', 1, 4);
        conjunto.add(aux);
        aux = new Letra('Q', 1, 5);
        conjunto.add(aux);
        aux = new Letra('J', 1, 8);
        conjunto.add(aux);
        aux = new Letra('X', 1, 8);
        conjunto.add(aux);
        aux = new Letra('Z', 1, 10);
        conjunto.add(aux);
    }
    public void getConjunto()
    {
        for (Letra letra : conjunto) {
            System.out.println(
                letra.getLetra() + "\t" + letra.getFrecuencia() + "\t" + letra.getPuntos()
            );
        }
    }
}

class BolsaLetras
{
    //Atributos
    ArrayList<Character> bolsa = new ArrayList<Character>();

    //Metodos
    //Constructores
    BolsaLetras(ConjuntoLetras conjunto)
    {
        for (Letra l : conjunto.conjunto) {
            for(int i = 0; i < l.getFrecuencia(); i++) {
                bolsa.add(l.getLetra());
            }
        }
        Collections.shuffle(bolsa);
    }
    BolsaLetras(BolsaLetras orig) {
        bolsa = orig.bolsa;
    }

    public ArrayList<Character> getBolsa(int n) {
            ArrayList<Character> ret = new ArrayList<Character>();
            for (int i = 0; i< n; i++) {
                ret.add(bolsa.get(i));
            }
            return ret;
    }

    public void barajar() {
        Collections.shuffle(bolsa);
    }

}

class ListaPalabras {
    public SortedSet<String> datos = new TreeSet<String>();

    //Constructores
    ListaPalabras() {}
    ListaPalabras(ListaPalabras orig) {datos = orig.datos;}

    public ArrayList<String> palabrasLongitud(int n) {
        ArrayList<String> aux = new ArrayList<String>();
        for (String palabra : datos) {
            if (palabra.length() == n) {
                aux.add(palabra);
            }
        }
        return aux;
    }

    public boolean correcta(String palabra, ArrayList<Character> letras) {
        ArrayList<Character> aux = new ArrayList<Character>(letras);
        //Pasamos la palabra a mayusculas
        palabra = palabra.toUpperCase();
        for (Character l : palabra.toCharArray()) {
            if(!aux.contains(l)) {
                return false;
            }
            else {
                aux.remove(l);
            }
        }
        //System.out.println(letras);
        //for (Character l : aux) {
        //    System.out.print(l);
        //}
        return true;
    }

    public ArrayList<String> palabrasMejores(int score, ArrayList<Character> letras, ConjuntoLetras conjunto, String modo) {
        ArrayList<String> mejores = new ArrayList<String>();
        for (String p : datos) {
            if(Programa.esCorrecta(p, letras)) {
                mejores.add(p);
            }
        }
        return mejores;
    }

    public void addPalabra(String p) {
        datos.add(p);
    }

    public void leerPalabras(String _archivo) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (_archivo);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            datos.add(linea);

      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
    }

    public void getListaPalabras() {
        for (String string : datos) {
            System.out.println(string);
        }
    }
}

class Programa{

    private Socket socketServicio;
    private InputStream inputStream;
    private OutputStream outputStream;
    ListaPalabras diccionario;

    public Programa(Socket socketServicio) {
        this.socketServicio = socketServicio;

        //Cargamos el diccionario
        diccionario = new ListaPalabras();
        System.out.println("Leyendo palabras...");
        diccionario.leerPalabras("spanish.txt");
        System.out.println("HECHO");
    }

    void procesa() {

		try {
			// Obtiene los flujos de escritura/lectura
			PrintWriter  _outputStream = new PrintWriter(socketServicio.getOutputStream(), true);
			BufferedReader _inputStream = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
			
			////////////////////////////////////////////////////////
			// Leer el PLAY
			String cadenaConexion = _inputStream.readLine();
            ////////////////////////////////////////////////////////
            boolean jugar = true;
            while (jugar) {
                //Generar la lista de palabras que hay que enviar
                ConjuntoLetras conj = new ConjuntoLetras();
                conj.leerLetras();
                BolsaLetras bolsa = new BolsaLetras(conj);
                bolsa.barajar();
                ArrayList<Character> letras = new ArrayList<Character>();
                letras = bolsa.getBolsa(8);
                String stringLetras = letras.toString();

                //Mandamos las letras al cliente
                /////////////////////////////////////////////////////////
                    _outputStream.println(stringLetras);
                /////////////////////////////////////////////////////////

                //Recibir la palabra del cliente
                ////////////////////////////////////////////////////////
                String palabra = _inputStream.readLine();
                ////////////////////////////////////////////////////////

                //Calculamos la solucion
                String solucion = "";
                int score=0;
                System.out.println("La palabra introducida es " + palabra);
                if (esCorrecta(palabra, letras) && diccionario.datos.contains(palabra)){
                    //Calcular puntuacion
                    score = conj.puntuacion(palabra, "L");
                    solucion = solucion + (palabra + "\tPuntuacion: " + score);
                }
                else {
                    solucion = solucion + ("**************PALABRA INCORRECTA**************");
                }
                
                // Mandamos la resolucion de la palabra enviada
                ////////////////////////////////////////////////////////
                _outputStream.println(solucion);
                ////////////////////////////////////////////////////////


                //Calcular las soluciones del programa
                ArrayList<String> pPrograma = new ArrayList<String>();
                pPrograma = diccionario.palabrasMejores(score, letras, conj, "L");
                if(pPrograma.size() == 0){
                    //Mandar 0 soluciones
                    _outputStream.println("0");
                    solucion = ("No se ha encontrado ninguna solución");
                }   
                else {
                    //Mandar el numero de soluciones que hay
                    System.out.println("Soluciones encontradas: " + String.valueOf(pPrograma.size()));
                    _outputStream.println(String.valueOf(pPrograma.size()));

                    System.out.println("Calculando las palabras con mayor puntuacion...");
                    String mejorPalabra = "";
                    int mayorPuntuacion = score;
                    solucion = "";
                    for (String mejores : pPrograma) {
                        int p_actual = conj.puntuacion(mejores, "L");
                        solucion += mejores + ", ";
                        if(p_actual >= mayorPuntuacion) {
                            mayorPuntuacion = p_actual;
                            mejorPalabra = mejores;
                        }
                        //System.out.println(solucion);
                    }
                    //Manda las palabras solucion
                    _outputStream.println(solucion);
                    //Manda la mejor solucion
                    solucion = (mejorPalabra + "\tPuntuacion: " + mayorPuntuacion);
                    _outputStream.println(solucion);
                }

                solucion = ("Quieres jugar de nuevo? [S/n]");
                System.out.println("*******************************************SOLUCION ENVIADA");
                //Mandar la pregunta al cliente
                ////////////////////////////////////////////////////////
                _outputStream.println(solucion);
                ////////////////////////////////////////////////////////

                //Recibe la respuesta de si sigue jugando o no
                ////////////////////////////////////////////////////////
                String res = _inputStream.readLine();
                ////////////////////////////////////////////////////////
                if (res.contains("PLAY")) {
					jugar = true;
				}
				else {
					jugar = false;
				}
            }
		} catch (IOException e) {
			System.err.println("Error al obtener los flujso de entrada/salida.");
		}
    }

    public static boolean esCorrecta(String palabra, ArrayList<Character> letras) {
        ArrayList<Character> aux = new ArrayList<Character>(letras);
        //Pasamos la palabra a mayusculas
        palabra = palabra.toUpperCase();
        for (Character l : palabra.toCharArray()) {
            if(!aux.contains(l)) {
                return false;
            }
            else {
                aux.remove(l);
            }
        }
        return true;
    }


    /*public static void main(String [] args)
    {
        //Cargar diccionario
        ListaPalabras diccionario = new ListaPalabras();
        System.out.println("Leyendo palabras...");
        diccionario.leerPalabras("spanish.txt");
        System.out.println("HECHO");
        
        //Cargar bolsa de letras
        ConjuntoLetras conj = new ConjuntoLetras();
        conj.leerLetras();
        BolsaLetras bolsa = new BolsaLetras(conj);
        bolsa.barajar();
        ArrayList<Character> prueba = new ArrayList<Character>();
        prueba = bolsa.getBolsa(5);
        for (char char1 : prueba) {
            System.out.print(char1 + " ");
        }
        //Leer la palabra del jugador
        String palabra;
        Scanner keyboard = new Scanner(System.in);
        palabra = keyboard.nextLine();
        int score = 0;
        

        if (esCorrecta(palabra, prueba) && diccionario.datos.contains(palabra)){
            //Calcular puntuacion
            score = conj.puntuacion(palabra, "L");
            System.out.println(palabra + "\tPuntuacion: " + score);
        }
        else
        System.out.println("PALABRA INCORRECTA");
        

        //Mostrar las soluciones del programa
        ArrayList<String> pPrograma = new ArrayList<String>();
        pPrograma = diccionario.palabrasMejores(score, prueba, conj, "L");
        if(pPrograma.size() == 0)
            System.out.println("No se ha encontrado ninguna solución");
        else {
            String mejorPalabra = "";
            int mayorPuntuacion = score;
            System.out.println("Mis soluciones son: ");
            for (String mejores : pPrograma) {
                int p_actual = conj.puntuacion(mejores, "L");
                System.out.println(mejores + "\tPuntuacion: " + p_actual);
                if(p_actual >= mayorPuntuacion) {
                    mayorPuntuacion = p_actual;
                    mejorPalabra = mejores;
                }
            }
            System.out.println("Mejor solucion: ");
            System.out.println(mejorPalabra + "\tPuntuacion: " + mayorPuntuacion);
        }
    }
    */
}