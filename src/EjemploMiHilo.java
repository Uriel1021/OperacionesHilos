public class EjemploMiHilo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++)
            System.out.println(i + " " + Thread.currentThread().getName());
        System.out.println("Finaliza proceso del hilo " +
                Thread.currentThread().getName());
    }
    public static void main (String [] args) {
        new Thread (new EjemploMiHilo(), "Hilo 1").start();
        new Thread (new EjemploMiHilo(), "Hilo 2").start();
        System.out.println("Finaliza proceso principal");
    }
}