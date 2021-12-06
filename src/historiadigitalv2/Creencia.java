package historiadigitalv2;

public class Creencia {
    private Personaje x;
    private Objeto o;
    private Jugador j;
    private Localizacion l;
    private int tipo;

    public Creencia(Personaje personaje, Objeto o, Jugador j, Localizacion l,int t) { //constructor de la clase Creencia
        super();
        this.x = personaje;
        this.j= j;
        this.o = o;
        this.setL(l);
        this.tipo=t;
    }

    //getters y setters de las variables privadas de la clase Creencia
    public Jugador getJ() {
        return j;
    }

    public void setJ(Jugador j) {
        this.j = j;
    }

    public Personaje getX() {
        return x;
    }

    public int getTipo() {
        return tipo;
    }

    public void setX(Personaje x) {
        this.x = x;
    }

    public Objeto getO() {
        return o;
    }

    public void setO(Objeto o) {
        this.o = o;
    }

    public Localizacion getL() {
        return l;
    }

    public void setL(Localizacion l) {
        this.l = l;
    }

}