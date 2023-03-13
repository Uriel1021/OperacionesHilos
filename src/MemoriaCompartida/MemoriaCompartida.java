package MemoriaCompartida;

/*
    La clase MemoriaCompartida representa un espacio de memoria compartida que actúa como buffer
    para almacenar información. En este caso, el buffer almacena un valor tipo string.
    La clase tiene dos métodos sincronizados: cargar() y obtener().
 */
public class MemoriaCompartida {
    // Variable encargada de controlar la generación de información
    private boolean puedoEscribir = true;

    // Dato a almacenar en la MC para ser consumido
    private String dato;

    // cargar() se encarga de almacenar un valor tipo string en el buffer
    public synchronized void cargar(String dato) {
        while (!puedoEscribir) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getLocalizedMessage());
            }
        }
        this.dato = dato;
        puedoEscribir = !puedoEscribir;

        // Invocamos al metodo sleep para dormir al hilo un tiempo determinado medio segundo
        //Este metodo puede ser sobrecargado (polimorfismo por parametro)
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            System.out.println(ie.getLocalizedMessage());
        }
        notify();
    }

    //obtener() se encarga de leer y devolver el valor almacenado en el buffer.
    public synchronized String obtener() {
        while (puedoEscribir) {
            try {
                wait();
            } catch (InterruptedException ie) {
                System.out.println(ie.getLocalizedMessage());
            }
        }
        puedoEscribir = !puedoEscribir;
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            System.out.println(ie.getLocalizedMessage());
        }
        notify(); // para despertar un hilo
        return dato; //Retornamos dato almacenado
    }
}