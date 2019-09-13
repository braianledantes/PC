package TP1_Excepciones;

public class Punto6B {

    public static void main(String[] args) {
        Punto6A.Cliente cliente1 = new Punto6A.Cliente("Cliente 1", new int[]{2, 2, 1, 5, 2, 3});
        Punto6A.Cliente cliente2 = new Punto6A.Cliente("Cliente 2", new int[]{1, 3, 5, 1, 1});
        long initialTime = System.currentTimeMillis();
        CajeraThread cajera1 = new CajeraThread("Cajera 1", cliente1, initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2", cliente2, initialTime);
        // Tiempo inicial de referencia
        cajera1.start();
        cajera2.start();
/*
        cajera1.procesarCompra(cliente1, initialTime);
        cajera2.procesarCompra(cliente2, initialTime);*/
    }

    public static class CajeraThread extends Thread {
        private String nombre;
        private Punto6A.Cliente cliente;
        private long initialTime;

        public CajeraThread(String nombre,Punto6A.Cliente cliente, long initialTime) {
            this.nombre = nombre;
            this.cliente = cliente;
            this.initialTime = initialTime;
        }

        public long getInitialTime() {
            return initialTime;
        }

        public void setInitialTime(long initialTime) {
            this.initialTime = initialTime;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Punto6A.Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Punto6A.Cliente cliente) {
            this.cliente = cliente;
        }

        // Constructor, y metodos de acceso
        public void run() {
            System.out.println("La cajera " + this.nombre +
                    " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                    + this.cliente.getNombre() + " EN EL TIEMPO: "
                    + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
            for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
                this.esperarXsegundos(cliente.getCarroCompra()[i]);
                System.out.println("Procesado el producto " + (i + 1) + " del cliente " +
                        this.cliente.getNombre() + "->Tiempo: " +
                        (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
            }
            System.out.println("La cajera" + this.nombre + "HA TERMINADO DE PROCESAR"
                    + this.cliente.getNombre() + " EN EL TIEMPO: " +
                    (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        }

        private void esperarXsegundos(int seg) {
            try {
                Thread.sleep(seg * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

}


