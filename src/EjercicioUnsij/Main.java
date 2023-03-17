package EjercicioUnsij;

import java.util.Scanner;

/*
La clase Main solicita al usuario la cantidad de páginas
que se desea imprimir y la cantidad de páginas disponibles
en una impresora. Si la cantidad de páginas necesarias es
mayor que la cantidad de páginas disponibles, el programa
solicita al usuario que ingrese nuevamente la cantidad de
páginas disponibles hasta que sea suficiente para imprimir
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int paginasNecesarias;
        int paginasDisponibles;

        System.out.print("Ingrese la cantidad de páginas que desea imprimir: ");
        paginasNecesarias = scanner.nextInt();

        System.out.print("Ingrese la cantidad de páginas disponibles en la impresora: ");
        paginasDisponibles = scanner.nextInt();

        /*
        asegurarse de que haya suficientes páginas en la impresora
        antes de crear los procesos de impresión
         */
        while (paginasDisponibles < paginasNecesarias) {
            System.out.println("No hay suficientes páginas en la impresora");
            System.out.print("Ingrese la cantidad de páginas disponible en la impresora: ");
            paginasDisponibles = scanner.nextInt();
        }

        Impresora impresora = new Impresora(paginasDisponibles);
        ProcesoA procesoA = new ProcesoA(paginasNecesarias, impresora);
        ProcesoB procesoB = new ProcesoB(impresora);

        /*
        procesoA y procesoB se pasan como argumentos
        al constructor de los objetos Thread hiloA y hiloB
         */
        Thread hiloA = new Thread(procesoA);
        Thread hiloB = new Thread(procesoB);

        hiloA.start();
        hiloB.start();
    }
}