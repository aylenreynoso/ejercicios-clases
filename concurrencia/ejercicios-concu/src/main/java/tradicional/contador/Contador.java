package tradicional.contador;

public class Contador {
    private static int valor = 0;

    //bloquea el monitor asociado a la clase Contador.class
    public static synchronized void incrementarClase() {
        valor++;
    }

    //bloquea el monitor asociado a la instancia (this)
    public synchronized void incrementarInstancia() {
        valor++;
        System.out.println(Thread.currentThread().getName() + " incrementó el contador a " + valor);
    }

    public static int obtenerValor() {
        return valor;
    }
}

