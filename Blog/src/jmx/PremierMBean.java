package jmx;

public abstract class PremierMBean {

    public abstract String getNom();
    public abstract int getValeur();
    public abstract void setValeur(int valeur);
    public abstract void rafraichir();

}
