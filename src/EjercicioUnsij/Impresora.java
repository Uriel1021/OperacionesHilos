package EjercicioUnsij;

public class Impresora {
    private int paginasDisponibles;

    public Impresora(int paginasDisponibles) {
        this.paginasDisponibles = paginasDisponibles;
    }
    /*
    El método agregarPaginas aumenta la cantidad de
    páginas disponibles en la impresora y notifica a
    todos los hilos en espera que hay páginas disponibles
     */
    public synchronized void agregarPaginas(int cantidad) {
        this.paginasDisponibles += cantidad;
        System.out.println("Se agregaron " + cantidad + " páginas");
        System.out.println("Páginas disponibles en la impresora: " + this.paginasDisponibles);
        notifyAll();
    }
    /*
    El método imprimir comprueba si hay suficientes páginas
    disponibles para la impresión y, si no, entra en espera
    hasta que haya suficientes páginas disponibles
     */
    public synchronized void imprimir(int cantidad) {
        while (cantidad > this.paginasDisponibles) {
            try {
                System.out.println("No hay suficientes páginas en la impresora para imprimir " + cantidad + " páginas.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    /*
    Cuando hay suficientes páginas, la impresión comienza
    y la cantidad de páginas disponibles se reduce
     */
        this.paginasDisponibles -= cantidad;
        System.out.println("Se imprimieron " + cantidad + " páginas.");
        System.out.println("Páginas disponibles en la impresora: " + this.paginasDisponibles);
    }
}