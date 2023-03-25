package ClienteServidorArchivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LectorArchivo {
    public static String leerArchivo(String ruta) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(ruta));
        String linea;
        StringBuilder contenido = new StringBuilder();
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea);
        }
        lector.close();
        return contenido.toString();
    }
}
