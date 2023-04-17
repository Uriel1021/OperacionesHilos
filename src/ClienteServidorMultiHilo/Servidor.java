package ClienteServidorMultiHilo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Servidor extends Thread {
    private Socket socket;
    private ArrayList<Servidor> listaDeHilos;
    private PrintWriter salida;
    private JFrame ventana;
    private JTextArea areaDeMensajes;

    public Servidor(Socket socket, ArrayList<Servidor> listaDeHilos) {
        this.socket = socket;
        this.listaDeHilos = listaDeHilos;
        // Crear la ventana y el JTextArea para mostrar los mensajes
        ventana = new JFrame("Servidor");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        areaDeMensajes = new JTextArea(20, 40);
        areaDeMensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaDeMensajes);
        panel.add(scroll);
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    @Override
    public void run() {
        try {
            // Leemos el flujo de entrada del cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Devolvemos la salida al cliente pasando la salida al cliente
            // y limpiando el buffer de manera automática
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Server infinito
            while (true) {
                String cadenaDeSalida = entrada.readLine();
                // Finalizamos el servidor
                if (cadenaDeSalida.equals("Salir")) {
                    break;
                }
                mostrarMensaje(cadenaDeSalida);
                System.out.println("Recibido por el servidor [" + socket.getInetAddress().getHostAddress() + "]: " + cadenaDeSalida);

            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + Arrays.toString(e.getStackTrace()));
        } finally {
            // Eliminamos el hilo de la lista de hilos al terminar el hilo
            listaDeHilos.remove(this);
            System.out.println("Cliente desconectado [" + socket.getInetAddress().getHostAddress() + "]");
            mostrarMensaje("Cliente desconectado");
        }
    }

    private void mostrarMensaje(String cadenaDeSalida) {
        // Agregar la IP del cliente a la cadena que se muestra en el JTextArea y se envía a los demás clientes
        String mensajeConIP = "[" + socket.getInetAddress().getHostAddress() + "]: " + cadenaDeSalida;
        // Mostrar el mensaje en el JTextArea y enviarlo a todos los clientes
        areaDeMensajes.append(mensajeConIP + "\n");
        for (Servidor servidor : listaDeHilos) {
            // Enviamos el mensaje a todos los clientes, excepto al cliente actual
            if (servidor != this) {
                servidor.salida.println(mensajeConIP);
            }
        }
    }
}