package historiadigitalv2;

public class Objetivo {
    private Personaje x;
    private String Ubi;
    private String obj;

    public Objetivo(Personaje x, String ubi, String obj) {	//constructor de la clase Objetivo
        this.x = x;
        Ubi = ubi;
        this.obj = obj;
    }
    
    //getters, setters y el metodo toString() de la clase Objetivo
    
    public String getUbi() {
        return Ubi;
    }
    public void setUbi(String ubi) {
        Ubi = ubi;
    }
    public String getObj() {
        return obj;
    }
    public void setObj(String obj) {
        this.obj = obj;
    }

    public String toString() {		// Devuelve informacion
        return "Su objetivo de localizacion es:"+Ubi+" y tiene que poseer: "+obj;
    }

    public Object getNombre() {
        return x.getNombre();
    }
 
}