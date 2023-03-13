package MemoriaCompartida;

import java.util.Scanner;


/*
    La clase ProcesoA representa un productor, que genera información
    y la almacena en el buffer compartido utilizando el método cargar() de la clase MemoriaCompartida.
 */

public class ProcesoA implements Runnable {
    private MemoriaCompartida memoriaCompartida;

    //Scanner para leer desde teclado
    private Scanner scanner = new Scanner(System.in);

    public ProcesoA(MemoriaCompartida memoriaCompartida) {
        this.memoriaCompartida = memoriaCompartida;
    }

    @Override
    public void run() {
        String valorAGenerar;
        for (int i = 0; i <= 10; i++) {
            System.out.print("Generando " + i + " Escriba un valor: ");
            valorAGenerar = scanner.nextLine();
            this.memoriaCompartida.cargar(valorAGenerar);
            System.out.println("Escribiendo valor: " + valorAGenerar);
        }
    }
}