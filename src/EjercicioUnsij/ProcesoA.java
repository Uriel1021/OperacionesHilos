package EjercicioUnsij;

/*
El proceso A se encarga de imprimir la cantidad
específica de páginas ingresadas por el usuario
 */
public class ProcesoA implements Runnable {
    private int paginasNecesarias;
    private Impresora impresora;

    public ProcesoA(int paginasNecesarias, Impresora impresora) {
        this.paginasNecesarias = paginasNecesarias;
        this.impresora = impresora;
    }

    @Override
    public void run() {
        impresora.imprimir(paginasNecesarias);
    }
}