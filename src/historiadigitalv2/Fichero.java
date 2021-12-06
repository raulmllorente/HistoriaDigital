package historiadigitalv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fichero{
	
	private String direccion ;
	private String direccion2; 
	public Fichero(String direccion, String direccion2) {	//constructor de la clase Fichero
		super();
		this.direccion = direccion;
		this.direccion2= direccion2;
	}

	private Localizacion[] loc_array;
	private Personaje[] per_array;
	private Objeto[] obj_array;
	private Objetivo[] locPer_array;
	
	
	
	public int contadorLoc() {	//metodo que contabiliza las Localizaciones encontradas en el fichero del estado inicial
		String tipo="Localizaciones";
		boolean t = false;
		File f = new File(direccion);
		Scanner s;
		int cont = 0;		
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {	
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><]");
				String intento = sl.next();
				if (intento.equals(tipo)) {	//si el texto extraido es de tipo Localizacion se contabilizaran los elementos de este grupo
					t = true;
				}else if(intento.equals("Personajes")||intento.equals("Objetos")) {
					t=false;
				
				}else if(t) {
					cont++;
				}
			}
			
			s.close();
			return cont;
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		return cont;
	}
	
	public int contadorPer() {	//metodo que contabiliza los Personajes encontrados en el fichero del estado inicial 
		String tipo="Personajes";
		boolean t = false;
		int cont = 0;
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {	
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><]");
				String intento = sl.next();
				if (intento.equals(tipo)) {	//si el texto extraido es de tipo Personajes se contabilizaran los elementos de este grupo
					t = true;
				}else if(intento.equals("Localizaciones")||intento.equals("Objetos")) {
					t=false;
				
				}else if(t) {
					cont++;
				}
			}
			s.close();
			return cont;
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		return cont;
	}
	
	public int contadorObj() {	 //metodo que contabiliza los Objetos encontrados en el fichero del estado inicial
		String tipo="Objetos";
		boolean t = false;
		int cont = 0;
		File f = new File(direccion);
		Scanner s;
		try {
			s = new Scanner(f);
			
			while (s.hasNextLine()) {	
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><]");
				String intento = sl.next();
				if (intento.equals(tipo)) {	//si el texto extraido es de tipo Objetos se contabilizaran los elementos de este grupo
					t = true;
				}else if(intento.equals("Localizaciones")||intento.equals("Personajes")) {
					t=false;
				
				}else if(t) {
					cont++;
				}
			}
			s.close();
			return cont;
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		return cont;
	}
	
	 
	
	public int contadorAdyacencias(String ubi) {  //metodo que contabiliza las Adyacencias encontradas en el fichero del estado inicial
		File f = new File(direccion);
		Scanner s;
		boolean t = false;
		int cont=0;
		try {
			s = new Scanner(f);
			while(s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><()\\s,]");	//aqui los delimitadores seran parentesis y saltos de linea ya que las adyacencias estan expresadas de dicha manera en el archivo de texto inicial
				String intento = sl.next();
				if (intento.equals("Localizaciones")) {
					t = true;
				}else if(intento.equals("Objetos")||intento.equals("Personajes")) {	//si el texto extraido es distinto no se contabilizaran
					t=false;
				
				}else if(t) {
					if(intento.equals(ubi)) {
						while(sl.hasNext()) {
							String p= sl.next();		//si no se pone entra en bucle infinito
							cont++;	
						
						}
					}
				}
					
			}
			s.close();
			return cont;			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	
	//crea las nuevas localizaciones y lo devuelve en un array
	
	public Localizacion[] nuevasLocalizaciones() {
		File f = new File(direccion);
		Scanner s;
		loc_array = new Localizacion[contadorLoc()];
		
		int cont = 0;
		try {
			s = new Scanner(f);
			boolean tipo = false;
			while (s.hasNextLine()) {									//Crea localizaciones pero sin adyacencias
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><(),]");
				String intento = sl.next();
				if (intento.equals("Localizaciones")) {
					tipo = true;
				}else if(intento.equals("Personajes") || intento.equals("Objetos")) {
					tipo = false;
				}else if(tipo) {
					
					loc_array[cont] = new Localizacion(intento);
					cont ++;
				}
				sl.close();
			}
			s.close();
			s = new Scanner(f);
			cont = 0;
			while (s.hasNextLine()) {								//añade las adyacencias
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><()\\s,]");
				String intento = sl.next();
				
				if (intento.equals("Localizaciones")) {
					tipo = true;
				}else if(intento.equals("Personajes") || intento.equals("Objetos")) {
					tipo = false;
				}else if(tipo) {
					Localizacion[] ady_array = new Localizacion[contadorAdyacencias(intento)];	
					for(int i=0;sl.hasNext();i++) {						
						String x = sl.next();
						if(x.equals("")) {
							i--;
						}
						for (int j= 0; loc_array.length > j ;j++) {
							if (loc_array[j].getNombre().equals(x)) {
								ady_array[i] = loc_array[j];
							}
						}	
					}
				loc_array[cont].setAdyacencias(ady_array);
				cont++;
				
				}				
			}
			s.close();
					
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		
		return loc_array;
		
	}
	
	//crea los nuevos personajes y los devuelve en un array
	
	public Personaje[] nuevosPersonajes() {
		File f = new File(direccion);
		Scanner s;
		per_array = new Personaje[contadorPer()];
		int cont = 0;
		try {
			s = new Scanner(f);
			boolean tipo = false;
			while (s.hasNextLine()) {									
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><(),]");
				String intento = sl.next();
				if (intento.equals("Personajes")) {
					tipo = true;
				}else if(intento.equals("Localizaciones") || intento.equals("Objetos")) {
					tipo = false;
				}else if(tipo) {
						
					per_array[cont] = new Personaje(intento,null,null);				//crea un nuevo personaje
					String ubi = sl.next();
					for(int i = 0; i< loc_array.length; i++) {
						if (ubi.equals(loc_array[i].getNombre())) {			//busca la localizaacion creada anteriormente
							per_array[cont].setActual(loc_array[i]);		//y la añade al personaje
						}
					}
					cont ++;
					
				}
				
			}
			s.close();
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		
		return per_array;
		
	}
	
													 
	public Objeto[] nuevosObjetos() { 							//crea los nuevos objetos y los devuelve en un array
		File f = new File(direccion);
		Scanner s;
		obj_array = new Objeto[contadorObj()];
		int cont = 0;
		try {
			s = new Scanner(f);
			boolean tipo = false;
			
			while (s.hasNextLine()) {									
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("[><(),]");
				String intento = sl.next();
				if (intento.equals("Objetos")) {
					tipo = true;
				}else if(intento.equals("Localizaciones") || intento.equals("Personajes")) {
					tipo = false;
				}else if(tipo) {
					
					obj_array[cont] = new Objeto(intento,null,null);				
					String ubi = sl.next();
					for(int i = 0; i< per_array.length; i++) {
						if (ubi.equals(per_array[i].getNombre())) {			
							obj_array[cont].setDueño(per_array[i]);		
							per_array[i].setPosesion(obj_array[cont]);
						}
					}
					for(int i=0; i< loc_array.length; i++) {
						if(ubi.equals(loc_array[i].getNombre())) {
							obj_array[cont].setActual(loc_array[i]);
							loc_array[i].setX(obj_array[cont]);
						}
					}
					cont ++;
					
				}
				
				
			}
			s.close();
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		
		return obj_array;
		
	}
	 
	
	public int contadorLocPers() {    		//metodo que contabiliza las localizaciones finales de los personajes encontradas en el fichero de objetivos generales
        String tipo="Localización Personajes";
        boolean t = false;
        File f = new File(direccion2);
        Scanner s;
        int cont = 0;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("[><]");
                String intento = sl.next();
                if (intento.equals(tipo)) {			//si el texto extraido es de tipo Localizacion Personajes se contabilizaran los elementos de este grupo
                    t = true;
                }else if(intento.equals("Posesión Objetos")) {	//si el texto extraido es distinto no se contabilizaran
                    t=false;

                }else if(t) {
                    cont++;
                }
            }

            s.close();
            return cont;
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }
        return cont;
    }

    public int contadorPosObj() {    	//metodo que contabiliza las posiciones finales de los objetos encontrados en el fichero de objetivos generales
        String tipo="Posesión Objetos";
        boolean t = false;
        File f = new File(direccion2);
        Scanner s;
        int cont = 0;
        try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("[><]");
                String intento = sl.next();
                if (intento.equals(tipo)) {			//si el texto extraido es de tipo Posesion Objetos se contabilizaran los elementos de este grupo
                    t = true;
                }else if(intento.equals("Localización Personajes")) {	//si el texto extraido es distinto no se contabilizaran
                    t=false;

                }else if(t) {
                    cont++;
                }
            }

            s.close();
            return cont;
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }
        return cont;
    }
	
    													//crea los nuevos objetivos de localizacion de los personajes y lo devuelve en un formato array
    public Objetivo[] nuevosLocPer() {
        File f = new File(direccion2);
        Scanner s;
        locPer_array = new Objetivo[contadorLocPers()];
        int cont = 0;
        try {
            s = new Scanner(f);
            boolean tipo = false;
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("[><(),]");
                String intento = sl.next();
                if (intento.equals("Localización Personajes")) {
                    tipo = true;
                }else if(intento.equals("Posesión Objetos")) {
                    tipo = false;
                }else if(tipo) {
                    String ubi = sl.next();

                    for(int i = 0; i < per_array.length; i++) {
                        if(per_array[i].getNombre().equals(intento)) {
                            locPer_array[cont] = new Objetivo(per_array[i],ubi,null);
                        }

                    }
                    cont++;

                }

            }
            s.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }

        return locPer_array;

    }
    

    													//crea los nuevos objetivos de posesion de un objeto y los devuelve en formato array
    public void nuevosPosObj() {
        File f = new File(direccion);
        Scanner s;
        int cont = 0;
        try {
            s = new Scanner(f);
            boolean tipo = false;
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("[><(),]");
                String intento = sl.next();
                if (intento.equals("Posesión Objetos")) {
                    tipo = true;
                }else if(intento.equals("Localización Personajes")) {
                    tipo = false;
                }else if(tipo) {
                    String ubi = sl.next();

                    for(int i = 0; i < locPer_array.length; i++) {
                        if(locPer_array[i].getNombre().equals(ubi)) {
                        	locPer_array[i].setObj(intento);
                        }
                    }
                    cont++;
                 }

            }
            s.close();
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        }

    }

	//comprueba que los campos estan correctos
    private  void errorTipo1() throws ExceptionFichero {
		File f = new File(direccion);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				if(linea.equals("<Localizaciones>") || linea.equals("<Objetos>") || linea.equals("<Personajes>"))
					count++;
			}
			s.close();
			if(count != 3)
				throw new ExceptionFichero("Error en la declaracion de campos del fichero estado_inicial");			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	//comprueba que los campos estan correctos
    private  void errorTipo2() throws ExceptionFichero {
		File f = new File(direccion2);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				if(linea.equals("<Localizacion Personajes>") || linea.equals("<Posesion Objetos>"))
					count++;
			}
			s.close();
			if(count != 2)
				throw new ExceptionFichero("Error en la declaracion de campos del fichero objetivos_generales");			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
	
	//comprueba que los parentesis estan correctamente puestos
    private  void errorTipo3(String fich) throws ExceptionFichero{
		File f = new File(fich);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				for(int i=0;linea.length()>i;i++) {
					char aux= linea.charAt(i);
					if (aux=='(' || aux==')')
						count++;
				}
			}
			s.close();
			if(count%2!=0)									
				throw new ExceptionFichero("Error en la declaracion de parentesis de fichero");			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
	}
		
	
	//cuenta el numero de filas totales de un fichero
    private int contadorLineas(String fich) {
		File f = new File(fich);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				count++;
			}
			s.close();	
			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}		
		
		return count;
	}
	
	//comprueba que solo existen el tipo de lineas deseadas
    private void errorTipo4() throws ExceptionFichero {
		int numTotal = contadorLineas(direccion);
		File f = new File(direccion);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				for(int i=0;linea.length()>i;i++) {
					char aux= linea.charAt(i);
					if (aux=='(')
						count++;
				}
									
			}
			s.close();	
			if((count+=3)!= numTotal)
				throw new ExceptionFichero("Error, caracteres  no deseados en el fichero estado_inicial");			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}		
		
	}
		
	//comprueba que no existan caracteres no deseados
    private void errorTipo5(String fich) throws ExceptionFichero{
		char[] caracteresInvalidos = {'.','{','}',';','_','-','\\'};
		File f = new File(fich);
		Scanner s;
		int count =0;	
		try {
			s = new Scanner(f);		
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				
				for(int j=0;linea.length()>j;j++) {
					for(int i=0; caracteresInvalidos.length>i;i++) {
						if(linea.charAt(j)==caracteresInvalidos[i])
							count++;
					}
				}
					
				
			}
			s.close();
			if(count!=0)									
				throw new ExceptionFichero("Error, caracteres  no deseados en el fichero objetivos_generales");			
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		
	}
	
	public void comprobadorFichero() throws Exception {
		errorTipo1();
		errorTipo2();
		errorTipo3(direccion);
		errorTipo3(direccion2);
		errorTipo4();
		errorTipo5(direccion);
		errorTipo5(direccion2);
	}

}	
