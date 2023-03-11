package Colas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColaConQueue {
    private  int CAPACIDAD_MAXIMA = 10;
    private Queue<String> elementos;

    public ColaConQueue() {
        elementos = new LinkedList<String>();
    }

    public void encolar(String elemento) {
        if (elementos.size() == CAPACIDAD_MAXIMA) {
            System.out.println("La cola está llena.");
            return;
        }
        elementos.offer(elemento);
        System.out.println("Elemento " + elemento + " agregado a la cola.");
    }

    public void desencolar() {
        if (elementos.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        String elementoEliminado = elementos.poll();
        System.out.println("Elemento " + elementoEliminado + " eliminado de la cola.");
    }

    public void mostrar() {
        if (elementos.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }
        System.out.println("Elementos en la cola:");
        for (String elemento : elementos) {
            System.out.println("- " + elemento);
        }
    }

    public static void main(String[] args) {
        Cola cola = new Cola();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese 'a' para agregar un elemento, 'e' para eliminar un elemento o 'm' para mostrar la cola:");
            String opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("a")) {
                System.out.println("Ingrese el elemento a agregar:");
                String elemento = scanner.nextLine();
                cola.encolar(elemento);
            } else if (opcion.equalsIgnoreCase("e")) {
                cola.desencolar();
            } else if (opcion.equalsIgnoreCase("m")) {
                cola.mostrar();
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }
}
