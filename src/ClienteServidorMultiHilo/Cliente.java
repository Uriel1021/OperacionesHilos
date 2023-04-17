package ClienteServidorMultiHilo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente implements Runnable {
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private String cliente;
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField mensajeField;

    public Cliente(Socket socket) throws IOException {
        this.socket = socket;
        this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.salida = new PrintWriter(socket.getOutputStream(), true);
        this.cliente = "Sin conexiones";
        // Creamos la ventana con su respectiva interfaz gráfica
        crearVentana();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String respuesta = entrada.readLine();
                chatArea.append(respuesta + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error...." + Arrays.toString(ex.getStackTrace()));
        } finally {
            try {
                entrada.close();
            } catch (Exception ex) {
                System.out.println("Ha ocurrido un error...." + Arrays.toString(ex.getStackTrace()));
            }
        }
    }

    // Método para crear la ventana con su respectiva interfaz gráfica
    private void crearVentana() {
        frame = new JFrame("Cliente");
        JPanel panel = new JPanel();
        chatArea = new JTextArea(20, 40);
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        mensajeField = new JTextField(30);
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new EnviarListener());
        panel.add(scrollPane);
        panel.add(mensajeField);
        panel.add(enviarButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setVisible(true);
    }

    // Clase interna para manejar el evento del botón "Enviar"
    public class EnviarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev) {
            String mensaje = mensajeField.getText();
            if (!mensaje.isEmpty()) {
                if (cliente.equals("Sin conexiones")) {
                    cliente = mensaje;
                    salida.println(mensaje);
                    mensajeField.setText("");
                } else {
                    String mensajeCompleto = "Usuario [" + cliente + "]: " + mensaje;
                    salida.println(mensajeCompleto);
                    chatArea.append(mensajeCompleto + "\n");
                    mensajeField.setText("");
                }
            }
        }
    }
}
