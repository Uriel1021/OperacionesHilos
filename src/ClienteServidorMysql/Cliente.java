package ClienteServidorMysql;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String IP_SERVIDOR = "localhost";
    private static final int PUERTO_SERVIDOR = 4040;

    private static DataInputStream entrada;
    private static DataOutputStream salida;

    static void ejecutarCliente(String operacion) {
        try {
            String contenido = LectorMysql.ejecutarOperacion(operacion,"");

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

        Scanner sc = new Scanner(System.in);
        String operacion;
        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Leer registros.");
            System.out.println("2. Crear registro.");
            System.out.println("3. Actualizar registro.");
            System.out.println("4. Eliminar registro.");
            System.out.println("0. Salir.");
            operacion = sc.nextLine();
            switch (operacion) {
                case "1":
                    ejecutarCliente("leer");
                    break;
                case "2":
                    System.out.println("Ingrese el nombre del alumno:");
                    String nombre = sc.nextLine();
                    ejecutarCliente("crear," + nombre);
                    break;
                case "3":
                    System.out.println("Ingrese el ID del alumno:");
                    String idActualizar = sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre del alumno:");
                    String nuevoNombre = sc.nextLine();
                    ejecutarCliente("actualizar," + idActualizar + "," + nuevoNombre);
                    break;
                case "4":
                    System.out.println("Ingrese el ID del alumno:");
                    String idEliminar = sc.nextLine();
                    ejecutarCliente("eliminar," + idEliminar);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (!operacion.equals("0"));
        sc.close();
    }
}
