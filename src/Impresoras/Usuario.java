package Impresoras;

import java.util.Random;

public class Usuario extends Thread {

    private final CentroImpresion centroImpresion;
    private final Random random = new Random();

    public Usuario(CentroImpresion centroImpresion) {
        this.centroImpresion = centroImpresion;
    }

    @Override
    public void run() {
        while (true) {
            int tipoImpresora = random.nextInt(3);
            centroImpresion.imprimir(tipoImpresora);
        }
    }
}
