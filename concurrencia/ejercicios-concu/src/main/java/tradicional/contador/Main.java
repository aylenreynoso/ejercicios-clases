package tradicional.contador;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Contador contador = new Contador();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                //Contador.incrementarClase();
                contador.incrementarInstancia();
            }
        }, "Hilo-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                //Contador.incrementarClase();
                contador.incrementarInstancia();
            }
        }, "Hilo-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Valor final: " + Contador.obtenerValor());
    }
}

