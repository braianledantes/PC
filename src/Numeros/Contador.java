package Numeros;

public class Contador extends Thread {
    private int[] arr;
    private Numero numero;
    private int posIni;
    private int posFin;

    public Contador(int[] arr, Numero numero, int posIni, int posFin) {
        this.arr = arr;
        this.numero = numero;
        this.posIni = posIni;
        this.posFin = posFin;
    }

    @Override
    public void run() {

        String s = "[ ";
        for (int i = posIni; i <= posFin; i++) {
            s += arr[i] + " ";
        }
        s += "]";

        System.out.println("posIni = " + posIni + ", posFin = " + posFin + ", arr = " + s);
        int pos = this.posIni;
        while (pos <= this.posFin) {
            if (numero.esMismoNumero(arr[pos])) {
                numero.incrementar();
            }
            pos++;
        }
    }
}
