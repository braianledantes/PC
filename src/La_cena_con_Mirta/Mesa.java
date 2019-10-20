package La_cena_con_Mirta;

import colores.ColoresString;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mesa {
    private Random random;
    private int nInvitados, mMozos;
    private Semaphore mozos, mutex;
    private Semaphore mirta_sentarse;
    private Semaphore esperandoAComer;
    private Semaphore mirta_comer;
    private Semaphore comer;
    private Semaphore responder;
    private Semaphore mutex_resp;
    private Semaphore enojarse;
    private Semaphore levantarse;
    private boolean se_sirvio_a_mirta;
    private boolean se_respondio;

    public Mesa(int nInvitados, int mMozos) throws RuntimeException {
        if (nInvitados < mMozos) {
            throw new RuntimeException("Mas mozos que invitados");
        }
        random = new Random();
        this.nInvitados = nInvitados;
        this.mMozos = mMozos;
        this.mutex = new Semaphore(1);


        se_sirvio_a_mirta = false;
        se_respondio = false;

        mirta_sentarse = new Semaphore(0);
        mozos = new Semaphore(0);
        esperandoAComer = new Semaphore(0);
        mirta_comer = new Semaphore(0);
        comer = new Semaphore(0);
        responder = new Semaphore(0);
        mutex_resp = new Semaphore(1);
        enojarse = new Semaphore(0);
        levantarse = new Semaphore(0);
    }

    public void sentarse(Thread t) throws InterruptedException {
        if (t instanceof Mirta) {
            mirta_sentarse.acquire(nInvitados); // se sienta cuando llegen todos los invitados
            Thread.sleep(500 + random.nextInt(500));
            System.out.println(ColoresString.ANSI_PURPLE + "Mirta sentada");
            mozos.release(mMozos);
        } else {
            Thread.sleep(250 + random.nextInt(500));
            System.out.println(ColoresString.ANSI_BLUE + "Invitado sentado");
            mirta_sentarse.release(); // llego un invitado
        }
    }

    public void servir_comida() throws InterruptedException {
        mozos.acquire();
        System.out.println(ColoresString.ANSI_BLACK + "Mozo sirviendo comida");
        Thread.sleep(1000 + random.nextInt(1000));
        mutex.acquire();
        if (!se_sirvio_a_mirta) {
            mirta_comer.release();
            se_sirvio_a_mirta = true;
        }
        // todo ver que hacer para que los mozos avisen a todos los comensales que pueden comer
        esperandoAComer.release(2);
        mutex.release();
    }

    public void comer(Thread t) throws InterruptedException {
        if (t instanceof Mirta) {
            mirta_comer.acquire();
            System.out.println(ColoresString.ANSI_PURPLE + "Mirta comiendo");
            comer.release(nInvitados);
        } else {
            esperandoAComer.acquire();
            comer.acquire();
            System.out.println(ColoresString.ANSI_BLUE + "Invitado comiendo");
        }
        Thread.sleep(2000 + random.nextInt(4000));
    }

    public void lanzar_pregunta_polemica() throws InterruptedException {
        System.out.println(ColoresString.ANSI_PURPLE + "Mirta pregunta");
        responder.release();
    }

    public void lanzar_respuesta_polemica() throws InterruptedException {
        mutex_resp.acquire();
        if (!se_respondio) {
            se_respondio = true;
            responder.acquire();
            Thread.sleep(1000 + random.nextInt(2000));
            enojarse.release();
            System.out.println(ColoresString.ANSI_BLUE + "Invitado responde");
        }
        mutex_resp.release();
    }

    public void enojarse() throws InterruptedException {
        enojarse.acquire();
        System.out.println(ColoresString.ANSI_PURPLE + "Mirta se enoja");
    }

    public void levantarse(Thread t) throws InterruptedException {
        if (t instanceof Mirta) {
            System.out.println(ColoresString.ANSI_PURPLE + "Mirta chau");
            levantarse.release(nInvitados);
        } else {
            levantarse.acquire();
            System.out.println(ColoresString.ANSI_BLUE + "Invitado chau");
        }
    }
}
