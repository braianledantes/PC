package Mirta;

import colores.ColoresString;

public class Invitado extends Thread {
    private Mesa mesa;

    public Invitado(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            mesa.sentarse(this);
            mesa.comer(this);
            mesa.lanzar_respuesta_polemica();
            mesa.levantarse(this);
           // System.out.println(ColoresString.ANSI_BLUE + "Invitado se fue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
