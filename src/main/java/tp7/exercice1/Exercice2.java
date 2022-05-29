package tp7.exercice1;

public class Exercice2 {
    public static void main(String[] args) {
        int profondeur = 4;
        // fibonacci2(4, profondeur);
        long debut=System.currentTimeMillis();
        fibonacci(10, -1);
        long duree=System.currentTimeMillis()-debut;
        debut=System.currentTimeMillis();
        System.out.println("durée 1->"+duree);
        fiboEconome(10);
        duree=System.currentTimeMillis()-debut;
        System.out.println("durée 2->"+duree);
    }

    public static void fibonacci(int n, int p) {

        p = p + 1;
        String tirets = "";
        for (int i = 0; i < (n - p); i++) {
            tirets += "-";
        }
        System.out.println(tirets + "Calcul de Finobacci (" + p + ")");

        if (p == 1) {
            //System.out.println("F1=1");
            fibonacci(n, p);
        } else if (p == 0) {
            //System.out.println("F0=0");
            fibonacci(n, p);
        } else {
            //System.out.println("F" +p+"=F"+(p-1)+"+F"+(p-2));
            if (p < n) {
                fibonacci(n, p);
            }
        }
    }

    public static void fibonacci2(int n, int p) {
        String tirets = "";
        for (int i = 0; i < (p - n); i++) {
            tirets += "-";
        }
        System.out.println(tirets + "Calcul de Finobacci (" + n + ")");
        if (n >= 1) {
            //System.out.println("F1=1");
            n = n - 1;
            fibonacci2(n, p);
        }
    }

    public static void fiboEconome(int n) {
        long debut=System.currentTimeMillis();
        Couple couple = new Couple("F", n - 2, n-1);
        System.out.println("F" + n + "=" + couple.somme());
        if (n > 2) {
            fiboEconome(n-1);
        }
    }
}
