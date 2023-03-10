public class EjemploHilo extends Thread{
    public EjemploHilo(String nombreHilo){
        super(nombreHilo);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++)
            System.out.println(i + " " + getName());
        System.out.println("Finaliza proceso del hilo " + getName());
    }
    public static void main (String [] args) {
        new EjemploHilo ("Hilo 1").start();
        new EjemploHilo ("Hilo 2").start();
        System.out.println("Finaliza proceso principal");
    }
}
