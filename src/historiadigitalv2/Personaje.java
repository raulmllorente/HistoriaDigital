package historiadigitalv2;

import java.util.ArrayList;

public class Personaje extends Humano{
 
    private static ArrayList<String> acciones;			// registro
    
	public Personaje(String nombre, Localizacion actual, Objeto posesion) { //constructor de la clase Personaje
		super(nombre, actual, posesion);
		acciones= new ArrayList<String>();
    }
 
    
    public void ir (Localizacion w) {	  //metodo ir() que realiza la accion de desplazamiento del personaje en cuestion
	        if(w.adyacente(getActual())) {		
	            setActual(w);
	            System.out.println("El personaje "+getNombre()+" se ha movido a: "+getActual());
	            nuevaAccion("El personaje "+getNombre()+" se ha movido a: "+getActual());
	        }else {
            	System.out.println("La localizacion no es adyacente");
        	}
    	}  

   public void nuevaAccion(String p) {			
	   acciones.add(p);
   }
    
    
   public void pedir(Personaje x) { // pedro.pedir(Juan) - Juan le pide a pedro
	    setNotficicacion(x);
	   nuevaAccion("El personaje "+this.getNombre()+" ha pedido un objeto a "+x.getNombre());
    }


   
   public void dar() {  //metodo dar() que se encarga de dar el objeto
       if (getNotficicacion() != null && getNotficicacion().getActual().equals(this.getActual())) {	
    	   getPosesion().setDueño(getNotficicacion());
    	   getNotficicacion().setPosesion(this.getPosesion());
    	   setPosesion(null);
    	   setNotficicacion(null);
    	   System.out.println("El personaje "+getNombre()+" ha dado el objeto: "+ getPosesion()); 
    	   nuevaAccion("El personaje "+getNombre()+" ha dado el objeto: "+ getPosesion());
       }
    } 

    public void coger(Objeto c) {	 //metodo coger() que se encarga de realizar la accion coger el objeto
	    		if (getActual().ubiObjeto(c.getNombre())) {
	    			getActual().borrarObj(c);
	            	setPosesion(getActual().getObjeto(c.getNombre()));
	            	c.setDueño(this);
	            	System.out.println("El personaje "+getNombre()+" ha cogido el objeto: "+c.getNombre()); 
	            	nuevaAccion("El personaje "+getNombre()+" ha cogido el objeto: "+c.getNombre());
	        	} 
    }
          
    public void dejar() {		//metodo dejar() que realiza la accion de dejar el objeto en cuestion
				System.out.println("El personaje "+getNombre()+" ha dejado el objeto: "+getPosesion()); 
				nuevaAccion("El personaje "+getNombre()+" ha dejado el objeto: "+getPosesion());
	    		getPosesion().abandono();
	    		setPosesion(null);
   	   }
    
    public void nada() {	//metodo nada() que informara si el personaje no ha hecho nada
    	System.out.println("El personaje "+getNombre()+" no ha hecho nada"); 
    	nuevaAccion("El personaje "+getNombre()+" no ha hecho nada");
    }
    
}
