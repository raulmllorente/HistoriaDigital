package historiadigitalv2;

public class Juego {

    public static void main(String[] args) {
        Fichero fich = new Fichero("estado_inicial.txt",
				   					"objetivos_generales.txt");	//creamos un elemento de tipo Fichero
        try {
			fich.comprobadorFichero();	// comprobaciones de fichero
		} catch (Exception e) {
			e.printStackTrace();
		}
        //creamos arrays de todos los tipos de clases de datos que se rellenaran con las funciones nuev@s....() creadas en la clase Fichero para cada clase de dato
        Localizacion[] loc_array = fich.nuevasLocalizaciones();
        Personaje[] per_array= fich.nuevosPersonajes();
        Objeto[] obj_array = fich.nuevosObjetos();
        Objetivo[] ObjlocPer_array  = fich.nuevosLocPer();
        fich.nuevosPosObj();
        Jugador jug = null;
        for(int x=0;x<per_array.length;x++) {	//se consiguen los datos del Jugador
            if(per_array[x].getNombre().equals("Jugador")) {
                jug = new Jugador("Zelda",per_array[x].getActual(),null);
            }
        } 
        Interfaz p = new Interfaz(loc_array, ObjlocPer_array, obj_array, jug, per_array);	//se crea la interfaz de juego
        p.Comienzo();
    }
}