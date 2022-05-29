package tp7.exercice1;

public class Couple {
    private String Fn1;
    private String Fn;

    public Couple(String F, int n, int n1) {
        Fn = F+n;
        Fn1 = F+n1;
    }
    public String somme () {
        return this.Fn1+"+"+Fn;
    }
}
