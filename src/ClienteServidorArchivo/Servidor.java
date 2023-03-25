package ClienteServidorArchivo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO_SERVIDOR = 4040;

    public static void main(String[] args) {
        lanzarServer();
    }

    public static void lanzarServer(){
        DataInputStream entrada;
        DataOutputStream salida;

        try {
            ServerSocket servidor = new ServerSocket(PUERTO_SERVIDOR);
            System.out.println("Servidor iniciado en el puerto " + PUERTO_SERVIDOR);

            while(true){
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde " + cliente.getInetAddress().getHostAddress());

                entrada = new DataInputStream(cliente.getInputStream());
                salida = new DataOutputStream(cliente.getOutputStream());

                String contenido = entrada.readUTF();
                log("Archivo recibido del cliente:");
                log(contenido);

                // Aquí es donde se procesaría el archivo recibido...

                salida.writeUTF("Archivo procesado exitosamente.");

                cliente.close();
                System.out.println("Cliente desconectado.");
            }
        } catch (Exception ex) {
            log("Error: " + ex.toString());
        }
    }

    private static void log(String cadena){
        System.out.println(cadena);
    }
}
