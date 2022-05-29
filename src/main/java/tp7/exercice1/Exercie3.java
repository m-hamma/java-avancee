package tp7.exercice1;

public class Exercie3 {
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        ackerman(10, 6);
        long duree = System.currentTimeMillis() - debut;
        System.out.println("durée 1->" + duree);
        debut = System.currentTimeMillis();
        ackermanIter(10, 6);
        duree = System.currentTimeMillis() - debut;
        System.out.println("durée 2->" + duree);
    }

    public static void ackerman(int n, int p) {
        //A(0,p)=p+1
        //A(n,0)=A(n-1,1)
        //A(n,p)=A(n-1,A(n,p-1))
        System.out.println("A(" + n + "," + p + ")=A(" + (n - 1) + ",A(" + n + "," + (p - 1) + "))");
        if (n > 1) {
            ackerman(n - 1, p);
        }
        if (p > 1) {
            ackerman(n, p - 1);
        }

    }

    public static void ackermanIter(int n, int p) {
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < p; j++) {
                System.out.println("A(" + (n-i) + "," + (p - j) + ")=A(" + (n -i- 1) + ",A(" + (n-i) + "," + ((p - j) - 1) + "))");
            }
        }
    }
}
