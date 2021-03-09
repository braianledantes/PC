package fumadores;

import java.util.concurrent.Semaphore;

public class MesaSemaforo implements Mesa {
    private int ingredienteFaltante = 0;
    private final Semaphore agente = new Semaphore(1);
    private final int cantFumadores;
    private final Semaphore[] fumadores;

    public MesaSemaforo(int cantFumadores) {
        this.cantFumadores = cantFumadores;
        fumadores = new Semaphore[cantFumadores];
        for (int i = 0; i < fumadores.length; i++) {
            fumadores[i] = new Semaphore(0);
        }
    }

    @Override
    public void agregarIngredientes() {
        try {
            agente.acquire();
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = (int) (Math.floor(Math.random() * fumadores.length) + 1);
            System.out.println(Thread.currentThread().getName() + " agrego un ingrediente " + ingredienteFaltante);
            fumadores[ingredienteFaltante - 1].release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void armarCigarrillo(int ingrediente) {
        try {
            fumadores[ingrediente - 1].acquire();
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = 0;
            System.out.println(Thread.currentThread().getName() + " armo un cigarro con " + ingrediente);
            agente.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
