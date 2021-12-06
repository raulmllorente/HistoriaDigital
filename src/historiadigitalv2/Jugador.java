package historiadigitalv2;

import java.util.ArrayList;

public class Jugador extends Humano{
	
    private ArrayList<Creencia> creencia2;
    
	public ArrayList<Creencia> getCreencia2() {
		return creencia2;
	}

	public Jugador(String nombre, Localizacion actual, Objeto posesion) {	 //constructor de la clase Jugador
		super(nombre, actual, posesion);
		creencia2= new ArrayList<Creencia>();
	}
	
	public void dar() {	//metodo dar() que se encarga de dar el objeto
	       if (getNotficicacion().getActual().equals(this.getActual())) {	
	    	   getPosesion().setDueño(getNotficicacion());
	    	   getNotficicacion().setPosesion(getPosesion());
	    	   setPosesion(null);
	    	   setNotficicacion(null);
	    	   System.out.println("Usted "+getNombre()+" ha dado el objeto: "+getPosesion().getNombre()); 
	       }
	   }
	


	   public void pedir(Personaje x) { // pedro.pedir(Juan) - Juan le pide a pedro
		   setNotficicacion(x);	   
	    }
	   

	    public void coger(Objeto c) {	//metodo coger() que se encarga de realizar la accion coger el objeto
	    	try {
	    		if (getActual().ubiObjeto(c.getNombre()) & getPosesion() == null) {
	            	setPosesion(getActual().getObjeto(c.getNombre()));
	            	c.setDueño(this);
	            	System.out.println("El personaje "+getNombre()+" ha codigo el objeto: "+c.getNombre());
	            	getActual().borrarObj(c);
	        	}
	    	} catch(Exception x) {
	    		System.out.println("Error");
	    	}
	    }
	    
	    public void ir (Localizacion w) {	//metodo ir() que realiza la accion de desplazamiento del jugador
	        if(w.adyacente(this.getActual())) {		
	            setActual(w);
	            System.out.println("El personaje "+getNombre()+" se ha movido a: "+getActual());
	        }
    	} 

	    
	    public void dejar() {	 //metodo dejar() que realiza la accion de dejar el objeto en cuestion
	    	if(getPosesion() != null) {
	    	  getActual().setX(getPosesion());
	     	  this.getPosesion().abandono();
	     	  setPosesion(null);
	    	} else {
	    		System.out.println("No tenia ningun objeto que dejar");
	    	}
		}
	    
	    public void nada() {	//metodo nada() vacio que no realiza nada

	    }

	    public void nuevaCreencia(Creencia c) {		// añadimos creencias
	    		creencia2.add(c);
	    }
	    
}
