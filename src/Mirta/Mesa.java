package Mirta;

import java.util.concurrent.Semaphore;

public class Mesa {
    private int nInvitados, mMozos;
    private Semaphore invitados, mirta, mozos, mutex;

    public Mesa(int nInvitados, int mMozos) {
        if (nInvitados < mMozos) {
            //throw new Exception("muchos mozos");
            //TODO terminar
        }
        this.nInvitados = nInvitados;
        this.mMozos = mMozos;
        this.invitados = new Semaphore(nInvitados);
        this.mirta = new Semaphore(1);
        this.mozos = new Semaphore(mMozos);
    }

    public void sentarse() {

    }

    public void comer() {

    }

    public void servir_comida() {

    }

    public void levantarse() {

    }

    public void lanzar_pregunta_polemica() {

    }

    public void lanzar_respuesta_polemica() {

    }
}
