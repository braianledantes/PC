package TP1_Excepciones;

public class Punto6A {

    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1", new int[]{2, 2, 1, 5, 2, 3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[]{1, 3, 5, 1, 1});
        Cajera cajera1 = new Cajera("Cajera 1");
        // Tiempo inicial de referencia
        long initialTime = System.currentTimeMillis();
        cajera1.procesarCompra(cliente1, initialTime);
        cajera1.procesarCompra(cliente2, initialTime);
    }

    public static class Cajera {
        private String nombre;

        public Cajera(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void procesarCompra(Cliente cliente, long timeStamp) {
            System.out.println("La cajera " + this.nombre +
                    " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " +
                    cliente.getNombre() + " EN EL TIEMPO: " +
                    (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
            for (int i = 0; i < cliente.getCarroCompra().length; i++) {
                this.esperarXsegundos(cliente.getCarroCompra()[i]);
                System.out.println("Procesado el producto " + (i + 1) + " ->Tiempo: " +
                        (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
            }
            System.out.println("La cajera " + this.nombre +
                    " HA TERMINADO DE PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " +
                    (System.currentTimeMillis() - timeStamp) / 1000 + "seg");

        }

        private void esperarXsegundos(int seg) {
            try {
                Thread.sleep(seg * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    public static class Cliente {
        private String nombre;
        private int[] carroCompra;

        public Cliente(String nombre, int[] carroCompra) {
            this.nombre = nombre;
            this.carroCompra = carroCompra;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int[] getCarroCompra() {
            return carroCompra;
        }

        public void setCarroCompra(int[] carroCompra) {
            this.carroCompra = carroCompra;
        }
    }
}
