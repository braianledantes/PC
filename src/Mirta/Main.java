package Mirta;

public class Main {

    public static void main(String[] args) {
        int nInvitados = 6, mMozos = 3;
        Mesa mesa = new Mesa(nInvitados, mMozos);
        Mirta mirta = new Mirta(mesa);
        Invitado[] invitados = new Invitado[nInvitados];
        Mozo[] mozos = new Mozo[mMozos];

        mirta.start();
        for (int i = 0; i < invitados.length; i++) {
            invitados[i] = new Invitado(mesa);
            invitados[i].start();
        }

        for (int i = 0; i < mozos.length; i++) {
            mozos[i] = new Mozo(mesa);
            mozos[i].start();
        }
    }
}
