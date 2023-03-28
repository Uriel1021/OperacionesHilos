package ClienteServidorArchivo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private static final String IP_SERVIDOR = "localhost";
    private static final int PUERTO_SERVIDOR = 5050;

    private static DataInputStream entrada;
    private static DataOutputStream salida;

    static void ejecutarCliente(String archivo) {
        try {
            String contenido = LectorArchivo.leerArchivo(archivo);

            Socket cliente = new Socket(IP_SERVIDOR, PUERTO_SERVIDOR);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            log("Enviando archivo al servidor...");
            salida.writeUTF(contenido);
            salida.flush();
            log("Archivo enviado exitosamente.");

            cliente.close();
        } catch (Exception ex) {
            log("Error: " + ex.toString());
        }
    }

    private static void log(String cadena){
        System.out.println(cadena);
    }

    public static void main(String[] args) {
        String rutaArchivo = "/home/uriel/Desktop/prueba.txt";
        ejecutarCliente(rutaArchivo);
    }

}
