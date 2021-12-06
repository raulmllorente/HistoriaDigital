package historiadigitalv2;

public class Objeto {

    private String nombre;
    private Localizacion actual;
    private Humano dueño;
 
    @Override
    public String toString() {		// Devuelve el nombre
        return getNombre();
    }

    //getters, setters y el metodo toString() de la clase Objeto
    public Humano getDueño() {
        return dueño;
    }

    public void setDueño(Humano dueño) {
        this.dueño = dueño;
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

    public Objeto(String nombre, Localizacion actual, Humano dueño) {	//constructor de la clase Objeto
        this.nombre = nombre;
        this.actual = actual;
        this.dueño = dueño;
    }

    public void abandono() {
        actual = this.dueño.getActual();  //  dejar el objeto en la localizacion actual
        dueño = null;
    }

}