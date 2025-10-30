package prints;

public class PrintOrden1 {
    public static void main(String[] args) throws InterruptedException {
        Thread primero = new Thread(() -> System.out.println("Artistas"));
        Thread A = new Thread(() -> System.out.println("fito"));
        Thread B = new Thread(() -> System.out.println("charly"));
        Thread C = new Thread(() -> System.out.println("flaco"));

        primero.start();
        primero.join();
        A.start();
        B.start();
        C.start();
        System.out.println("Gracias totales");
        C.join();
        B.join();
        A.join();
    }
}
