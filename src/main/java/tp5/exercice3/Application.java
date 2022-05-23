package com.hamma.exercice3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    private static boolean listNonInterrupt = true;
    private static List<Integer> listeDiviseursTemp = new ArrayList<>();
    private static int nombreConcerne=0;
    public static void main(String[] args) {
        long timeMillisDebut = System.currentTimeMillis();
        System.out.println("Veuillez Attendre SVP, recherche en cours.....");
        for (int i = 1; i <= 100000; i++) {
            Calcul cl = new Calcul(i);
            Thread t1 = new Thread(cl);
            t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if( cl.getlisteDiviseurs().size()>=listeDiviseursTemp.size()){
                listeDiviseursTemp=cl.getlisteDiviseurs();
                nombreConcerne=i;
            }

        }
        long timeMillisFin = System.currentTimeMillis();
        long duree=timeMillisFin-timeMillisDebut;
        long dureeSecondes = TimeUnit.MILLISECONDS.toSeconds(duree);
        System.out.println("Le nopmbre qui est le plus grand nombre de diviseurs est " + nombreConcerne + " avec "+listeDiviseursTemp.size()+" diviseurs qui sont : ");
        for (int i = 0; i < listeDiviseursTemp.size(); i++) {
            System.out.println(listeDiviseursTemp.get(i) + " ,");
        }
        System.out.println("Le traitement a duré : "+dureeSecondes +" Secondes");

    }

    public static class Calcul implements Runnable {
        private Integer nombre;

        public Calcul(Integer nombre) {
            this.nombre = nombre;
        }


        public List<Integer> getlisteDiviseurs() {
            return listeDiviseurs;
        }
        private List<Integer> listeDiviseurs = new ArrayList<>();


        @Override
        public void run() {


            for (int i = 1; i <= 100000; i++) {
                if (nombre%i == 0) {
                    listeDiviseurs.add(i);
                }

            }

        }
    }

    private static boolean listInterrupeted() {
        return listNonInterrupt;
    }
}
