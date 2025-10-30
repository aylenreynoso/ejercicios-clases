package prints;

public class PrintOrden2 {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo = new Thread(() -> System.out.println("Hilo"));
        Thread subhilo = new Thread(() -> {
            try {
                System.out.println("Subhilo");
                hilo.start();
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }});
        subhilo.start();
        subhilo.join();

        System.out.println("Terminamos?");

    }
}
