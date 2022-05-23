package com.hamma.exercice4;

import java.util.*;
import java.util.concurrent.*;


public class Application {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long timeMillisDebut = System.currentTimeMillis();
        System.out.println("creating service");
        ExecutorService service = Executors.newFixedThreadPool(1000);
        List<Integer> listeDiviseursTemp = new ArrayList<>();
        List<MyCallable> futureList = new ArrayList<MyCallable>();
        for (int i = 0; i < 100000; i++){
            MyCallable myCallable = new MyCallable((long)i);
            futureList.add(myCallable);
        }
        System.out.println("Veuillez Attendre SVP, recherche en cours.....");
        List<Future<List<Integer>>> futures=null;
        try{
            futures = service.invokeAll(futureList);
        } catch(Exception err){
            err.printStackTrace();
        }
        System.out.println("Completed");
        service.shutdown();
        for (Future<List<Integer>> resultat:futures){
            List<Integer> listeDiviseurs = resultat.get();
            if( listeDiviseurs.size()>=listeDiviseursTemp.size()){
                listeDiviseursTemp=listeDiviseurs;
            }
        }
        long timeMillisFin = System.currentTimeMillis();
        long duree=timeMillisFin-timeMillisDebut;
        long dureeSecondes = TimeUnit.MILLISECONDS.toSeconds(duree);
        System.out.println("Le nopmbre qui est le plus grand nombre de diviseurs est "+listeDiviseursTemp.get(listeDiviseursTemp.size()-1)+" avec "+listeDiviseursTemp.size()+" diviseurs qui sont : ");
        for (int i = 0; i < listeDiviseursTemp.size(); i++) {
            System.out.println(listeDiviseursTemp.get(i) + " ,");
        }
        System.out.println("Le traitement a duré : "+dureeSecondes +" Secondes");
    }
}
