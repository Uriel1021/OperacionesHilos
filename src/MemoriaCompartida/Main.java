package MemoriaCompartida;

/*
    En el método main() se crea una instancia de la clase MemoriaCompartida
    y se pasa a las instancias de las clases ProcesoA y ProcesoB para que accedan a la misma instancia compartida.
    luego se crean dos hilos utilizando la clase Thread y se les pasa como parámetro las instancias de las clases ProcesoA y
    ProcesoB para que se ejecuten en paralelo.
 */
public class Main {
    public static void main(String[] args) {
        MemoriaCompartida memoriaCompartida = new MemoriaCompartida();
        ProcesoA procesoA = new ProcesoA(memoriaCompartida);
        ProcesoB procesoB = new ProcesoB(memoriaCompartida);
        //Creamos instancia de clase Theread
        new Thread(procesoA).start();
        new Thread(procesoB).start();

    }
}