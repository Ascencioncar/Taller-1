import java.util.Scanner;

class DispensadorBilletes {
    private int denominacion;
    private DispensadorBilletes siguiente;

    public DispensadorBilletes(int denominacion, DispensadorBilletes siguiente) {
        this.denominacion = denominacion;
        this.siguiente = siguiente;
    }

    public void dispensar(int cantidad) {
        if (cantidad % 5000 != 0) {
            throw new IllegalArgumentException("Error: La cantidad debe ser múltiplo de 5.000");
        }

        int numBilletes = cantidad / denominacion;
        int restante = cantidad % denominacion;

        if (numBilletes > 0) {
            System.out.println("Dispensando " + numBilletes + " billete(s) de " + denominacion);
        }

        if (restante > 0 && siguiente != null) {
            siguiente.dispensar(restante);
        }
    }
}

public class CajeroAutomatico {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        // Cadena de responsabilidad. 
        DispensadorBilletes dispensador5000 = new DispensadorBilletes(5000, null);
        DispensadorBilletes dispensador10000 = new DispensadorBilletes(10000, dispensador5000);
        DispensadorBilletes dispensador20000 = new DispensadorBilletes(20000, dispensador10000);
        DispensadorBilletes dispensador50000 = new DispensadorBilletes(50000, dispensador20000);
        DispensadorBilletes dispensador100000 = new DispensadorBilletes(100000, dispensador50000);

        // Solicitud de retiro
        System.out.println("Ingrese el monto a retirar (múltiplo de 5.000):");
        int monto = leer.nextInt();

        leer.close();
        System.out.println("Solicitando " + monto + " en el cajero:");
        
        dispensador100000.dispensar(monto);
    }
}
