package ClienteServidorMultiHilo;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class OperacionesCliente {
    private static final String IP = "172.25.20.98";
    private static final int PORT = 8081;

    public static void lanzarCliente(){
        try (Socket socket = new Socket(IP, PORT)){
            // Retornamos la salida al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(),true);
            //Cachamos las entradas de los usuarios
            Scanner scanner = new Scanner(System.in);
            String datosDeEntrada;
            String cliente = "Sin conexiones";

            Cliente iniciarCliente = new Cliente(socket);
            //Creamos una instancia de Thread para lanzar el hilo
            new Thread(iniciarCliente).start();

            do {

                if (cliente.equals("Sin conexiones")) {

                    datosDeEntrada = scanner.nextLine();

                    //finalizamos si escribimos Salir
                    if (datosDeEntrada.equals("Salir")) {
                        break;
                    }
                }
                else {
                    datosDeEntrada = scanner.nextLine();
                    if (datosDeEntrada.equals("Salir")) {
                        //fin de bloque de código
                        break;
                    }
                }

            } while (!datosDeEntrada.equals("Salir"));

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + Arrays.toString(e.getStackTrace()));
        }
    }
}
