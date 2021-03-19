package Donantes.locks;

import java.util.concurrent.CountDownLatch;

public class Donante extends Thread {
    private final Sala sala;
    private final CountDownLatch empezar;

    public Donante(String name, Sala sala, CountDownLatch empezar) {
        super(name);
        this.sala = sala;
        this.empezar = empezar;
    }

    @Override
    public void run() {
        try {
            empezar.await();

            //System.out.println(ColoresString.ANSI_RESET + this.getName() + " intentando entrar a la sala");
            if (sala.intentarEntrar()) {
                while (!sala.intentarEntrarASalsa()) {
                    if (!sala.intentarLeerRevista())
                        sala.mirarTV();
                }
                sala.donarSangre();
                sala.salir();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
