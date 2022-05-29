package tp7.exercice1;

import java.util.Scanner;

public class Exercice1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("veuillez entre un nombre:");
        int nombre = sc.nextInt();
        int p=0;
        descente(nombre);
        montee(nombre,p);
        rebond(nombre);
        rechute(nombre);
    }

    public static void descente(int n) {
        if (n > 1) {
            System.out.println(n + "");
            int nMoinsUn = n - 1;
            descente(nMoinsUn);
        } else {
            System.out.println(n + "");
        }
    }

    public static void montee(int n,int p) {
       int resulat=p+1;
        System.out.println(resulat + "");

        if(p<n-1) {
            montee(n,resulat);
        }
    }
    public static void rebond(int n) {
        for (int i=0;i<n;i++){
            System.out.println(n-i+"");
        }
        for (int i=1;i<=n;i++){
            System.out.println(i+"");
        }
    }
    public static void rechute(int n) {
        for (int i=1;i<=n;i++){
            System.out.println(i+"");
        }
        for (int i=0;i<n;i++){
            System.out.println(n-i+"");
        }
    }
}
