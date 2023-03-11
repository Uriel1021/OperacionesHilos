package Hilos;

public class HilosOperaciones implements Runnable {

    private int tipo;
    //Constructor que toma un argumento tipo y lo asigna a tipo
    public HilosOperaciones(int tipo) {
        this.tipo = tipo;
    }

    //Metodo run recibe tipo para realizar cualquier operacion
    public void run() {
        switch (tipo) {
            case 1:
                calcularFibonacci();
                break;
            case 2:
                calcularFactorial();
                break;
            case 4:
                calcularRaices();
                break;
            default:
        }
    }

    //Calcular los primeros 10 elementos de la serie de fibonacci
    private void calcularFibonacci() {
        System.out.println("\nSERIE DE FIBONACCI\n");
        int n = 10;
        int a = 0, b = 1, c;
        System.out.print("\nSerie de Fibonacci de " + n + " términos: ");
        for (int i = 1; i <= n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
    }
    //Calcular el factorial de un numero
    private void calcularFactorial() {
        System.out.println("\nFACTORIAL\n");
        int n = 12;
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);
    }

    //Encontrar X1 y X2 con la formula general
    private void calcularRaices() {

        System.out.println("\nRAIZ CUADRATICA\n");
        double a = 1;
        double b = 11;
        double c = 24;
        double discriminante = b * b - 4 * a * c;
        if (discriminante < 0) {
            System.out.println("\nNo hay raíces reales.\n");
        } else if (discriminante == 0) {
            double x = -b / (2 * a);
            System.out.println("\nHay una raíz real: x = \n" + x);
        } else {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            System.out.println("\nHay dos raíces reales: x1 = " + x1 + ", x2 = " + x2 +"\n");
        }
    }
    //En el metodo main se crea un hilo y como parametro recibe el tipo de operacion
    public static void main(String[] args) {
        HilosOperaciones hilo1 = new HilosOperaciones(4);
        //Hilos.HilosOperaciones hilo2 = new Hilos.HilosOperaciones(2);
        //Hilos.HilosOperaciones hilo3 = new Hilos.HilosOperaciones(4);

        Thread t1 = new Thread(hilo1);
        //Thread t2 = new Thread(hilo2);
        //Thread t3 = new Thread(hilo3);


        t1.start();
        //t2.start();
        //t3.start();
    }
}