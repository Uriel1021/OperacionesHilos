package ClienteServidor;

import java.util.Scanner;

public class MainServidor {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String dato = scanner.nextLine();
        Servidor.lanzarServer(dato);

    }
}
