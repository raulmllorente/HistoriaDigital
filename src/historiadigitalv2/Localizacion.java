package historiadigitalv2;

//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Localizacion {

 private String nombre;
 private Localizacion[] adyacencias;
	private ArrayList<Objeto> x;
 
 
	public Localizacion(String nombre) { //constructor de la clase Localizacion
     super();
     this.nombre = nombre;
     this.x = new ArrayList<Objeto>();
 }

	 //getters, setters y metodo toString() de la clase Localizacion
 public Localizacion[] getAdyacencias() {
		return adyacencias;
	}
 
	public void setAdyacencias(Localizacion[] adyacencias) {
		this.adyacencias = adyacencias;
	}
	
 public  String getNombre() {
     return nombre;
 }
 
 public ArrayList<Objeto> getX() {
		return x;
	}

	public void setX(ArrayList<Objeto> x) {
		this.x = x;
	}

	public void setX(Objeto obj) {
     this.x.add(obj);
 }
	
	// Devolvemos el nombre de la localizacion
	@Override
 public String toString() {
 	return getNombre();
 }
	
	
	//Identifica si el objeto existe, si existe lo devuelve
	
	public  Objeto getObjeto(String obj) {		
	      for (Objeto n: x) {
	           if (n.getNombre().equals(obj))
	               return n;
	           }
	       return null;
	    }
	
	// Si el objeto se encuentra en la localizacion devuelve true
	
	 public  boolean ubiObjeto(String obj) { 
	        for (Objeto n: x) {
	            if (n.getNombre().equals(obj))
	                return true;
	            }
	        return false;
	    }
	 
	//Devuelve lista de localizaciones (adyacentes) para imprimirlos
	 
 public List<Localizacion> printad() {
 	return Arrays.asList(adyacencias);
 }

	//Devuelve lista de objetos para imprimirlos													
 public List<ArrayList<Objeto>> printobj() {
 	return Arrays.asList(x);
 }
 
 // Devuelve objeto que se encuentre en la misma loc que jugador
 public Objeto objLocal(Jugador j) {
     for (Objeto n: x) {
         if (j.getActual().equals(n.getActual())) {
             return n;
         }
     }
     return null;
 }
 
 // Devuelve el personaje que se encuentre en la misma loc que jugador
 public Objeto persLocal(Jugador j) {
     for (Objeto n: x) {
         if (j.getActual().equals(n.getActual())) {
             return n;
         }
     }
     return null;
 }
 
 // Devuelve el primer objeto 
 public Objeto getPrimerObjeto() {
	    if(x.get(0)!= null) {
	        return x.get(0);
	    }else {
	    	return null;
	    }
 }
 
 // devuelve la primera localizacion adyacente 
 public Localizacion getPrimerAdyacencia() {
     return adyacencias[0];
 }
 
 // Comprobante de adyacencias
 public boolean adyacente(Localizacion x) {
     for (int i = 0; i< adyacencias.length; i++) {
         if (x.equals(adyacencias[i]))
             return true;
     }
     return false;
 }
     
 // Borra objeto de la localizacion
 public void borrarObj (Objeto c) {
     for(int i=0;x.size()>i;i++) {
         if(x.get(i).getNombre().equals(c.getNombre())) {
             x.remove(i);
         }
     }
 }


}