package com.hamma.exercice4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class MyCallable implements Callable<List<Integer>> {
    Long id;
    public MyCallable(Long val){
        this.id = val;
    }
    public synchronized List<Integer> call(){
        List<Integer> listeDiviseurs = new ArrayList<>();
        for (int i = 1; i <= id; i++) {
            if (id%i == 0) {
                listeDiviseurs.add(i);
            }

        }
        return listeDiviseurs;
    }
}
