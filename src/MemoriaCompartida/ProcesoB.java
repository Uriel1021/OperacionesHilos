package MemoriaCompartida;

/*
    La clase ProcesoB representa un consumidor, que lee la información del buffer compartido utilizando el método obtener() de la clase MemoriaCompartida.
 */
public class ProcesoB implements Runnable {
    private MemoriaCompartida memoriaCompartida;

    public ProcesoB(MemoriaCompartida memoriaCompartida) {
        this.memoriaCompartida = memoriaCompartida;
    }

    @Override
    public void run() {
        String leerValor;
        for (int i = 0; i <= 10; i++) {
            leerValor = memoriaCompartida.obtener();
            System.out.println("\nObteniendo: " + i + ". Dsato leído: " + leerValor);
        }
    }
}
