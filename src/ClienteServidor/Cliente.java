package ClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private static DataInputStream entrada;
    private static DataOutputStream salida;
    static void ejecutarCliente(String msj) {
        String ip = "172.25.3.65";
        int puerto = 81;
        log("Conectado a traves de Socket: " + ip + " Puerto: " + puerto);
        try{
            /*
                Esta clase implementa sockets de clientes.
                Un socket es un end point para la comunicación entre dos máquinas.
             */

            Socket cliente = new Socket(ip, puerto);
            //Creamos un canal o puente para transferir la información
            // BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            //PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()),true);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            log("Enviado mensaje al servidor.....");
            //Enviamos mensaje al servidor
            salida.writeUTF(msj);
            salida.flush();
            //Mensaje devuelto por el servidor
            log("Mensaje recibido del servidor: " + entrada.readLine());
            cliente.close();
        }
        catch (Exception ex){
            log("Error: " + ex.toString());
        }
    }

    private static void log(String cadena){
        //Muestro los datos al TextView
        System.out.println(cadena);
        //txtSalida.append(cadena + "\n");
    }
}
