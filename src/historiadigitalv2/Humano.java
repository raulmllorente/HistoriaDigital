package historiadigitalv2;

import java.util.ArrayList;

public abstract class Humano {
	  	private String nombre;
	    private Localizacion actual;
	    private Objeto posesion;
	    private Objetivo propio;
	    private Personaje notficicacion;
	    
		public Humano(String nombre, Localizacion actual, Objeto posesion) {	 //constructor de la clase Humano
	        this.nombre=nombre;
	        this.actual = actual;
	        this.posesion = posesion;
	    }
		
		
		//getters y setters de las variables de la clase Humano
	    public Personaje getNotficicacion() {
			return notficicacion;
		}

		public void setNotficicacion(Personaje notficicacion) {
			this.notficicacion = notficicacion;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Objetivo getPropio() {
			return propio;
		}

		public void setPropio(Objetivo propio) {
			this.propio = propio;
		}
		
	    public Objeto getPosesion() {
	        return posesion;
	    }

	    public void setPosesion(Objeto posesion) {
	        this.posesion = posesion;
	    }

	    public Localizacion getActual() {
	        return actual;
	    }

	    public void setActual(Localizacion actual) {
	        this.actual = actual;
	    }
	    
	    

	    //metodos abstractos
	    public abstract void ir (Localizacion w);   
	    
	    public abstract void pedir(Personaje x);
	    
		public  abstract void dar();
	    
		public abstract void coger(Objeto c); 												 
	    
		public abstract void dejar();
	    
	    public abstract void nada();
}
