package ClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void lanzarServer(String datos){
        //Scanner scanner = new Scanner(System.in);
        DataInputStream entrada;
        DataOutputStream salida;
        try{
            System.out.println("Sevidor iniciado");
            /*
                Esta clase implementa sockets de servidor.
                Un socket de servidor espera que lleguen solicitudes a través de la red.
                Realiza alguna operación basada en esa solicitud y devuelve un
                resultado al solicitante.
             */
            ServerSocket servidor = new ServerSocket(3030);
            while(true){
                Socket cliente = servidor.accept();

                entrada = new DataInputStream(cliente.getInputStream());
                salida = new DataOutputStream(cliente.getOutputStream());

                String msjcliente = entrada.readUTF();
                //String datos = scanner.next();//entrada.readLine();

                //imprimer dato recibido del lado del cliente
                salida.writeUTF(datos);
                //cerramos conexión

                cliente.close();
                System.out.println("Información recibida del cliente: " + msjcliente);
            }
            //sudo apt install telnetd -y
            //sudo systemctl status inetd
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
