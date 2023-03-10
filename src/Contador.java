public class Contador implements Runnable {

    private int contador;
    private String nombre;
    private int limiteConteo;

    public Contador(String nombre, int limiteConteo) {
        this.nombre = nombre;
        this.limiteConteo = limiteConteo;
        contador = 0;
    }

    public void run() {
        while (contador < limiteConteo) {
            System.out.println(nombre + " contando: " + contador);
            contador++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nombre + " ha terminado de contar");
    }

    public static void main(String[] args) {

        // Crea varias instancias de la clase Contadora
        Contador contador1 = new Contador("Máquina de monito 1", 5);
        Contador contador2 = new Contador("Máquina de monito 2", 10);
        Contador contador3 = new Contador("Máquina de monito 3", 20);

        // Crea hilos para cada instancia
        Thread hilo1 = new Thread(contador1);
        Thread hilo2 = new Thread(contador2);
        Thread hilo3 = new Thread(contador3);

        // Establece la prioridad de ejecución para cada hilo
        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.NORM_PRIORITY);
        hilo3.setPriority(Thread.MAX_PRIORITY);

        // Inicia la ejecución de los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
