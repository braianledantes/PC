package pruebas;

import java.util.concurrent.TimeUnit;

public class prueba_interumpr_hilo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("trabajando");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
            //t1.interrupt();
            System.out.println("interrumpi el hilo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
