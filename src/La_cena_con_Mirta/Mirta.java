package La_cena_con_Mirta;

public class Mirta extends Thread {
    private Mesa mesa;

    public Mirta(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            mesa.sentarse(this);
            mesa.comer(this);
            mesa.lanzar_pregunta_polemica();
            mesa.enojarse();
            mesa.levantarse(this);
            //System.out.println(ColoresString.ANSI_PURPLE + "Mirta se fue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
