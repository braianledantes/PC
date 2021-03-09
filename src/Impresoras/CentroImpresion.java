package Impresoras;

public class CentroImpresion {
    Impresora impresoraA;
    Impresora impresoraB;
    int cantOcupadas = 0;

    public void imprimir(int tipoImpresora) {
        if (tipoImpresora == 1) {
            imprimirA();
        } else if (tipoImpresora == 2) {
            imprimirB();
        } else {
            synchronized (this) {
                boolean imprimio = false;
                while (!imprimio) {
                    while (cantOcupadas > 0) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    imprimio = impresoraA.intentarImprimir() || impresoraB.intentarImprimir();
                }
            }
        }
    }

    private void imprimirA() {
        synchronized (this) {
            cantOcupadas++;
        }
        impresoraA.imprimir();
        synchronized (this) {
            cantOcupadas--;
            this.notify();
        }
    }

    private void imprimirB() {
        synchronized (this) {
            cantOcupadas++;
        }
        impresoraB.imprimir();
        synchronized (this) {
            cantOcupadas--;
            this.notify();
        }
    }

}
