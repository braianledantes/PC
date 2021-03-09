package fumadores;

public class MesaMonitores implements Mesa {
    private int ingredienteFaltante = 0; // cero indica que falta un ingrediente

    @Override
    public synchronized void agregarIngredientes() {
        try {
            while (ingredienteFaltante != 0) {
                this.wait();
            }
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = (int) (Math.floor(Math.random() * 3) + 1);
            System.out.println(Thread.currentThread().getName() + " agrego un ingrediente " + ingredienteFaltante);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyAll();
    }

    @Override
    public synchronized void armarCigarrillo(int ingrediente) {
        try {
            while (ingredienteFaltante != ingrediente) {
                this.wait();
            }
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = 0;
            System.out.println(Thread.currentThread().getName() + " armo un cigarro con " + ingrediente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyAll();
    }
}
