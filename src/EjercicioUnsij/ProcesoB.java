package EjercicioUnsij;

/*
el proceso B se encarga de agregar más páginas a la impresora
 */
public class ProcesoB implements Runnable {
    private Impresora impresora;

    public ProcesoB(Impresora impresora) {
        this.impresora = impresora;
    }

    @Override
    public void run() {
        impresora.agregarPaginas(10);
    }
}




