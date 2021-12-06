package historiadigitalv2;

public class Objeto {

    private String nombre;
    private Localizacion actual;
    private Humano due�o;
 
    @Override
    public String toString() {		// Devuelve el nombre
        return getNombre();
    }

    //getters, setters y el metodo toString() de la clase Objeto
    public Humano getDue�o() {
        return due�o;
    }

    public void setDue�o(Humano due�o) {
        this.due�o = due�o;
    }

    public String getNombre() {
        return nombre;
    }

    public Localizacion getActual() {
        return actual;
    }

    public void setActual(Localizacion actual) {
        this.actual = actual;
    }

    public Objeto(String nombre, Localizacion actual, Humano due�o) {	//constructor de la clase Objeto
        this.nombre = nombre;
        this.actual = actual;
        this.due�o = due�o;
    }

    public void abandono() {
        actual = this.due�o.getActual();  //  dejar el objeto en la localizacion actual
        due�o = null;
    }

}