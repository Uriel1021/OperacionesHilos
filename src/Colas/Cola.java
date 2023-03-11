package Colas;

import java.util.Scanner;

public class Cola {
    /*
    se declara un arreglo "elementos" de cadenas de caracteres que almacena los elementos de la cola.
    se declara un entero "frente" que indica el índice del primer elemento de la cola.
    se declara un entero "fin" que indica el índice del último elemento de la cola.
    se declara un entero "tamanio" que indica la cantidad de elementos en la cola.
     */
    private static final int CAPACIDAD_MAXIMA = 10;
    private String[] elementos;
    private int frente;
    private int fin;
    private int tamanio;
    //En el constructor se inicializan las variables
    public Cola() {
        elementos = new String[CAPACIDAD_MAXIMA];
        frente = 0;
        fin = -1;
        tamanio = 0;
    }

    //Metodo encolar para agregar un elemento a la cola
    public void encolar(String elemento) {
        if (tamanio == CAPACIDAD_MAXIMA) {
            System.out.println("La cola está llena.");
            return;
        }
        fin = (fin + 1) % CAPACIDAD_MAXIMA;
        elementos[fin] = elemento;
        tamanio++;
        System.out.println("Elemento " + elemento + " agregado a la cola.");
    }
    //Metodo desencolar para quitar el primer elemento de la cola
    public void desencolar() {
        if (tamanio == 0) {
            System.out.println("La cola está vacía.");
            return;
        }
        String elementoEliminado = elementos[frente];
        frente = (frente + 1) % CAPACIDAD_MAXIMA;
        tamanio--;
        System.out.println("Elemento " + elementoEliminado + " eliminado de la cola.");
    }
    //Metodo mostrar para recorrer la cola
    public void mostrar() {
        if (tamanio == 0) {
            System.out.println("La cola está vacía.");
            return;
        }
        System.out.println("Elementos en la cola:");
        for (int i = 0; i < tamanio; i++) {
            int indice = (frente + i) % CAPACIDAD_MAXIMA;
            System.out.println("- " + elementos[indice]);
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