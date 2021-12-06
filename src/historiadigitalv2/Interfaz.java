package historiadigitalv2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;


public class Interfaz implements ActionListener{	
	int count; 
	private Localizacion []localizacion;
	private Objetivo []objetivos;
	private Objeto []objeto;
	private Jugador jugador;
	private Personaje []personaje;
	
	
	// definimos todos los elementos de la interfaz
	private JFrame jFrame= new JFrame();
	private JPanel jPanel= new JPanel();		
	private JPanel jPanel2= new JPanel();	
	private JPanel jPanel3= new JPanel();	
	private JLabel jLabel= new JLabel("Informacion Actual");
	private JLabel jLabel2= new JLabel("Que desea hacer");
	private JLabel jLabel3= new JLabel("Creencias");
    private JLabel jLabel4= new JLabel(" Localizaciones alcanzables: ");
    private	JLabel jLabel5= new JLabel(" Objetos presentes en su localización:");
    private	JLabel jLabel6= new JLabel(" Personajes presentes en su localización: ");
    private JLabel jLabel7= new JLabel(" Objetos que posee cada personaje de su localización: ");
    private JLabel Creencia= new JLabel(" Localizacion actual del resto de personajes: ");
    private JTextArea CreenciaA= new JTextArea("");
    private JLabel Creencia2= new JLabel(" Localizacion actual de cada objeto: ");
    private JTextArea CreenciaA2= new JTextArea("");
    private JLabel Creencia3= new JLabel(" Posesion actual de objetos de cada personaje: ");
    private JTextArea CreenciaA3= new JTextArea("");
	private JButton jButton= new JButton("Moverme !");
	private JButton jButton2= new JButton("Coger objeto !");
	private JButton jButton3= new JButton("Dar objeto !");
	private JButton jButton4= new JButton("Dejar objeto !");
	private JButton jButton5= new JButton("Pedir objeto !");
	private JButton jButton6= new JButton("Nada :(");
	private JButton jButton7= new JButton("Siguiente Ronda !");
	private JLabel Objetivos= new JLabel("  Sus objetivos son: ");
	private JLabel Objetivosob= new JLabel("  Ir al Dormitorio y tener el Ordenador");
	private JButton jButtonpregunt= new JButton("Envio");
	private JTextField textField=new JTextField(20);
	private JButton jButtonpregunt2= new JButton("Envio");
	private JTextField textField2=new JTextField(20);
	private JButton jButtonpregunt3= new JButton("Envio");
	private JTextField textField3=new JTextField(20);
	

	public Interfaz(Localizacion[] localizacion, Objetivo[] objetivo, Objeto[] objeto, Jugador jugador,				// constructor de la clase interfaz 
			Personaje[] personaje) {
		super();
		this.localizacion = localizacion;
		this.objetivos = objetivo;
		this.jugador = jugador;
		this.personaje = personaje;
		this.objeto = objeto;
		Recarga();
	      for(int x=0; x<personaje.length; x++) {
	            if(personaje[x].getNombre().equals("Jugador")) {
	             jugador = new Jugador("Zelda", personaje[x].getActual(),null);
	            }
	        }
	}
	
	//devuelve un string que muestra los objetivos del jugador 
	public String printObjetivo() {
		return ("  Conseguir el objeto: "+jugador.getPropio().getObj()+" y llegar con el objeto a: "+jugador.getPropio().getUbi());
	}
	
	//devuelve un String con el nombre de los personajes que comparten ubicacion con el jugador
	public String printPersonajes() {
        String x="";
        for(int i=0; personaje.length > i; i++) {
            if(personaje[i].getActual().equals(jugador.getActual()))
                x+=" "+personaje[i].getNombre();
        }
        return x;
    }
	
	//devuelve un string con la posesion de los personajes que comparten ubicacion con el jugador
	public String printPersonajesobj() {
        String x="";
        for(int i=0; personaje.length > i; i++) {
            if(personaje[i].getActual().equals(jugador.getActual()))
            	if(personaje[i].getPosesion()==null) {
            		 x+=" "+personaje[i].getPosesion();
            	}else {
                   x+=" "+personaje[i].getPosesion().getNombre();
            	}
        }
        return x;
    }
	
	// carga las creencias en el jugador
    public void cargarCreenciaLoc() {
        for(int i=0; personaje.length > i; i++) {
               if(personaje[i].getActual().equals(jugador.getActual())) {		//este es el primer tipo de creencia(personaje-localizacion)
                   Creencia x = new Creencia(personaje[i],null,null,jugador.getActual(),1);
                   jugador.nuevaCreencia(x);
                   if(personaje[i].getPosesion() != null) {
                	   Creencia j = new Creencia(personaje[i],personaje[i].getPosesion(),null,null,2);   //segundo tipo de creencia (personaje-objeto)
                	   jugador.nuevaCreencia(j);
                   }
               }
               
        }
        if (!jugador.getActual().getX().isEmpty()) {
        	ArrayList<Objeto> objetos = jugador.getActual().getX();
        	for(Objeto objeto: objetos ) {
        	Creencia x = new Creencia(null,objeto,null,jugador.getActual(),3);			//tercer tipo de arraylist (objeto-localizacion
        	jugador.nuevaCreencia(x);
        	}
        }
    }
	    
    // devuelve un string con las creencias del jugador, en funcion del tipo que le des te imprime unas creencias u otras
    public String printCreencia(int tipo) {
    	if(jugador.getCreencia2() != null && !jugador.getCreencia2().isEmpty()) {
    		ArrayList<Creencia> creencia2= jugador.getCreencia2();
        	String x="";
        	String y="";
        	String z="";
	    	for(int i=0; i<creencia2.size();i++) {
	    		if(creencia2.get(i).getTipo() == 1) {
	    			x+="El personaje "+creencia2.get(i).getX().getNombre()+" Se encuentra en "
	    					+creencia2.get(i).getL().getNombre()+"\n";
	    		}else if(creencia2.get(i).getTipo() == 2) {
	    			y+="El personaje "+creencia2.get(i).getX().getNombre()+" posee el objeto "
	    					+ creencia2.get(i).getO().getNombre()+"\n";
	    			
	    		}else if(creencia2.get(i).getTipo() == 3) {
	    			z+= "En la localizacion"+creencia2.get(i).getL().getNombre() +"se encuentra el objeto"
	    					+ creencia2.get(i).getO().getNombre()+"\n";
	    			
	    		}
	    		
	    	}
		    	switch (tipo){
		    		case 1:	
		    			return x;
		    					    			
		    		case 2: 
		    			return y;
		    				    		
		    		case 3: 
		    			return z;
		    					    	
	  	    	}
		    	return"";
    	}else {
    		return "";
    	}
    }
    
    //metodo que actualiza la informacion mostrada en la interfaz
	public void Recarga() {
		jLabel4.setText(" Localizaciones alcanzables: "+jugador.getActual().printad());
		jLabel5.setText(" Objetos presentes en su localización:"+jugador.getActual().printobj());
		jLabel6.setText(" Personajes presentes en su localización: "+printPersonajes());
		jLabel7.setText(" Objetos que poseen los personajes: "+printPersonajes()+ "	 de su localización: "+printPersonajesobj());
		CreenciaA.replaceRange(printCreencia(1),CreenciaA.getSelectionStart(),CreenciaA.getSelectionEnd());
		CreenciaA2.replaceRange(printCreencia(3),CreenciaA2.getSelectionStart(),CreenciaA2.getSelectionEnd());
		CreenciaA3.replaceRange(printCreencia(2),CreenciaA3.getSelectionStart(),CreenciaA3.getSelectionEnd());
	//	Creencia2.setText(" Localizacion actual de cada objeto: "+jugador.getO());
	//	Creencia3.setText(" Posesion actual de objetos de cada personaje: "+jugador.crenciaOP(o2));
	}
	
	//Metodo que inicializa y carga la interfaz
	public void Comienzo() {
		final int FW= 1200;
		final int FH= 839;
		final int FX= 400;
		final int FY= 150;
		final int x=15;
		
		jFrame.setLocation(FX,FY);
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setSize(FW,FH);
		jFrame.setLayout(null);
		
		// panel previo
		jPanel.setBounds(0,0,800,400);
        jPanel.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("objetivos.png");
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
		jLabel.setBounds(205, 20, 900, 40);
		jLabel.setFont(new Font("Comic Sans",Font.BOLD,40));	
		jLabel4.setBounds(20, 100, 900, 35);
		jLabel4.setFont(new Font("Comic Sans",Font.BOLD,15));
		jLabel5.setBounds(20, 158, 900, 35);
		jLabel5.setFont(new Font("Comic Sans",Font.BOLD,15));
		jLabel6.setBounds(20, 158+68, 900, 35);
		jLabel6.setFont(new Font("Comic Sans",Font.BOLD,15));
		jLabel7.setBounds(20,158+68*2, 900, 35);
		jLabel7.setFont(new Font("Comic Sans",Font.BOLD,15));
		
		// panel accion
        ImageIcon icon2 = new ImageIcon("objetivos.png");
        JLabel thumb2 = new JLabel();
        thumb2.setIcon(icon2);	
		jPanel2.setBounds(0,400,800,400);
		jPanel2.setLayout(new BorderLayout());
		jLabel2.setBounds(205,0,500,100);
		jLabel2.setFont(new Font("Comic Sans",Font.BOLD,45));		
		jButton.setBounds(x, 515, 125, 50);
		jButton2.setBounds(x*x, 515, 135, 50);
		jButton3.setBounds(x*x*2, 515, 125, 50);
		jButton4.setBounds(x*x*3-2*x, 515, 135, 50);
		jButton5.setBounds(x*x*2, 615, 135, 50);
		jButton6.setBounds(x*x, 615, 125, 50);
		jButton7.setBounds(x*x+350, 705, 200, 50);
		
		jButton.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton2.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton3.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton4.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton5.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton6.setFont(new Font("Comic Sans",Font.BOLD,15));
		jButton7.setFont(new Font("Comic Sans",Font.BOLD,15));
		
		jButton.addActionListener(this);
		jButton2.addActionListener(this);
		jButton3.addActionListener(this);
		jButton4.addActionListener(this);
		jButton5.addActionListener(this);
		jButton6.addActionListener(this);
		jButton7.addActionListener(this);
		
		Objetivos.setBounds(x, 705, 325, 50);
		Objetivos.setFont(new Font("Comic Sans",Font.BOLD,15));
		Objetivosob.setBounds(x, 725, 525, 50);
		Objetivosob.setFont(new Font("Comic Sans",Font.BOLD,15));

	
		// panel creencias	
        ImageIcon icon3 = new ImageIcon("creencias.png");
        JLabel thumb3 = new JLabel();
        thumb3.setIcon(icon3);		
		jPanel3.setBounds(800,0,400,800);
		jPanel3.setLayout(new BorderLayout());
		jLabel3.setBounds(100,0,500,100);
		jLabel3.setFont(new Font("Comic Sans",Font.BOLD,45));
		Creencia.setBounds(10, 150, 400, 20);
		Creencia.setFont(new Font("Comic Sans",Font.BOLD,15));
		CreenciaA.setColumns(30);
		CreenciaA.setRows(15);
		CreenciaA.setBounds(10, 170, 350, 150);
		CreenciaA.setFont(new Font("Comic Sans",Font.BOLD,10));
		CreenciaA.setEditable(false);
		Creencia2.setBounds(10, 320, 500, 20);
		Creencia2.setFont(new Font("Comic Sans",Font.BOLD,15));
		CreenciaA2.setColumns(30);
		CreenciaA2.setRows(15);
		CreenciaA2.setBounds(10, 340, 350, 150);
		CreenciaA2.setFont(new Font("Comic Sans",Font.BOLD,10));
		CreenciaA2.setEditable(false);
		Creencia3.setBounds(10, 520, 500, 20);
		Creencia3.setFont(new Font("Comic Sans",Font.BOLD,15));
		CreenciaA3.setColumns(30);
		CreenciaA3.setRows(15);
		CreenciaA3.setBounds(10, 540, 350, 150);
		CreenciaA3.setFont(new Font("Comic Sans",Font.BOLD,10));
		CreenciaA3.setEditable(false);
		
		jFrame.setVisible(true);
		
		jPanel.add(jLabel);
		jPanel.add(jLabel4);
		jPanel.add(jLabel5);
		jPanel.add(jLabel6);
		jPanel.add(jLabel7);
		jPanel.add(thumb);
		
		jPanel2.add(jLabel2);
		jFrame.add(Objetivos);
		jFrame.add(Objetivosob);
		jPanel2.add(thumb2);
		
		jPanel3.add(jLabel3);
		jPanel3.add(Creencia);
		jPanel3.add(Creencia2);
		jPanel3.add(Creencia3);
		jPanel3.add(CreenciaA);
		jPanel3.add(CreenciaA2);
		jPanel3.add(CreenciaA3);
		jPanel3.add(thumb3);

		jFrame.add(jPanel);
		jFrame.add(jPanel2);
		jFrame.add(jPanel3);
		jFrame.add(jButton);
		jFrame.add(jButton2);
		jFrame.add(jButton3);
		jFrame.add(jButton4);
		jFrame.add(jButton5);
		jFrame.add(jButton6);
		jFrame.add(jButton7);
		
		
	}
	
	//metodo que de forma aleatoria decide que hace cada personaje
	public void haganAlgo() {
        for (int i=0; personaje.length>i;i++) {
            int valorDado = (int) Math.floor(Math.random()*6-1);
            if(!(personaje[i].getNombre().equals("Jugador"))){			
	            switch(valorDado) {
	            case 0:													// PEDIR
	            	if(personaje[i].getPosesion()==null) {
		            	for(int x=0; x<personaje.length; x++) {		
		            		if(personaje[i].getActual().equals(personaje[x].getActual()))
		            		personaje[x].pedir(personaje[i]);
		            	}
	            	}
	                break;
	            case 1:													// COGER
	            	ArrayList<Objeto> x;
	            	x= personaje[i].getActual().getX();
	            	if(!x.isEmpty()) {
	            		personaje[i].coger(x.get(0));
	            	}
	                break;
		           
	            case 2:																				// IR
	                personaje[i].ir(personaje[i].getActual().getPrimerAdyacencia());      
	                break;
	                
	            case 3:															// DEJAR
	                if(personaje[i].getPosesion()!=null) {
	                    personaje[i].dejar();
	                }else {
	                    i--;
	                }
	                break;
	
	            case 4:
	                personaje[i].nada();
	                break;
	            
	            case 5:														// DAR
	            	if(personaje[i].getPosesion() != null) {
		            	personaje[i].dar();			
		            	break;
	            	}
            }
         }
       }
    }
	
	//metodo que devuelve el primer personaje que encuentre con la localizacion pasada
	public Personaje objPersonaje(Localizacion l) {
        for(int i=0; personaje.length > i; i++) {
            if(personaje[i].getActual().getNombre().equals(l.getNombre()))
                return personaje[i];
        }
        return null;
    }

	//metodo que devuelve el objeto del string dado
    public Objeto objObjeto(String x) {
        for(int i=0; objeto.length > i; i++) {
            if(objeto[i].getNombre().equals(x))
                return objeto[i];
        }
        return null;
    }
	
	

	
	@Override											
	public void actionPerformed(ActionEvent e) {			// Accionador
		jButton.setVisible(false);
		jButton2.setVisible(false);
		jButton3.setVisible(false);
		jButton4.setVisible(false);
		jButton5.setVisible(false);
		jButton6.setVisible(false);       
        
		if(e.getSource()==jButton || e.getSource()==jButtonpregunt) {	 // MOVERSE
			jButtonpregunt.setVisible(true);  
			textField.setVisible(true);  
			jButtonpregunt.setBounds(230,670,300,45);
			textField.setBounds(230,600,300,45);
			textField.setFont(new Font("Comic Sans",Font.BOLD,15));
			String p=textField.getText();
			jButtonpregunt.addActionListener(this);			// cambiar o ver que no funciona el textfield
			if (e.getSource()==jButtonpregunt) {
				 for(int x=0; x<localizacion.length; x++) {
						if(localizacion[x].getNombre().equals(p)) {	// comprobacion
							jugador.ir(localizacion[x]);	
							Recarga();
							jButtonpregunt.setVisible(false);  
							textField.setVisible(false);  
						} 
				 }
			}
			jFrame.add(textField);
			jFrame.add(jButtonpregunt);
			
		}else if(e.getSource()==jButton2 ||  e.getSource()==jButtonpregunt2) {	// COGER OBJETO 
			jButtonpregunt2.setVisible(true);  
			textField2.setVisible(true);  
			jButtonpregunt2.setBounds(230,670,300,45);
			textField2.setBounds(230,600,300,45);
			textField2.setFont(new Font("Comic Sans",Font.BOLD,15));
			String p=textField2.getText();
			jButtonpregunt2.addActionListener(this);			// cambiar o ver que no funciona el textfield
			if (e.getSource()==jButtonpregunt2) {
				 for(int x=0; x<objeto.length; x++) {
						if(objeto[x].getNombre().equals(p)) {
							jugador.coger(objeto[x]);
							Recarga();
							jButtonpregunt2.setVisible(false);  
							textField2.setVisible(false);  
						}
					}
			}
			jFrame.add(textField2);
			jFrame.add(jButtonpregunt2);
			
		}else if(e.getSource()==jButton3) {					// DAR OBJETO 
			for(int x=0; x<personaje.length; x++) {
		
			}
			jugador.dar();
		}else if(e.getSource()==jButton4) {				// DEJAR OBJETO 
			if (jugador.getPosesion()==null) {
				System.out.println("No tiene ningun objeto");
			}else {
			System.out.print("Va a dejar su objeto ! "+jugador.getPosesion().getNombre());	
			jugador.dejar();
			}
		}else if(e.getSource()==jButton5 || e.getSource()==jButtonpregunt3) {	// PEDIR OBJETO
			jButtonpregunt2.setVisible(true);  
			textField2.setVisible(true);  
			jButtonpregunt3.setBounds(230,670,300,45);
			textField3.setBounds(230,600,300,45);
			textField3.setFont(new Font("Comic Sans",Font.BOLD,15));
			String p=textField3.getText();
			jButtonpregunt3.addActionListener(this);			// cambiar o ver que no funciona el textfield
			if (e.getSource()==jButtonpregunt3) {
				 for(int x=0; x<personaje.length; x++) {
						if(personaje[x].getNombre().equals(p)) {
							if(personaje[x].getNombre().equals(p) && personaje[x].getPosesion()==null) {
								System.out.println("No tiene ningun objeto ese personaje");
							}else {
								jugador.pedir(personaje[x]);
								Recarga();
								jButtonpregunt2.setVisible(false);  
								textField2.setVisible(false);  
							}
						}
					}
			}
			jFrame.add(textField3);
			jFrame.add(jButtonpregunt3);		
		}else if(e.getSource()==jButton6) {			// NADA 
			// nada
		}else if(e.getSource()==jButton7) {			// SIGUIENTE RONDA
			count ++;
			cargarCreenciaLoc();
			haganAlgo();
			Recarga();
			jButton.setVisible(true);
			jButton2.setVisible(true);
			jButton3.setVisible(true);
			jButton4.setVisible(true);
			jButton5.setVisible(true);
			jButton6.setVisible(true);   
			if ( count == 10) {						// ronda 10 fin del juego
				final int FW= 1200;
				final int FH= 839;
				final int FX= 400;
				final int FY= 150;
				JFrame jFrame2= new JFrame();
				jFrame2.setLocation(FX,FY);
				jFrame2.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
				jFrame2.setResizable(false);
				jFrame2.setSize(FW,FH);
				jFrame2.setLayout(null);
				jFrame2.setVisible(true);
				jFrame.setVisible(false);
				JLabel jLabel2= new JLabel("FIN DEL JUEGO");
				jLabel2.setBounds(405, 20, 900, 40);
				jLabel2.setFont(new Font("Comic Sans",Font.BOLD,40));
				

				Objeto o5= new Objeto ("nada", jugador.getActual(), jugador);
				Objeto o3= new Objeto ("nada", personaje[1].getActual(), personaje[0]);
				Objeto o4= new Objeto ("nada", personaje[2].getActual(), personaje[0]);
				Objeto o2= new Objeto ("nada", personaje[0].getActual(), personaje[0]);
				
				for(int x=0; x<personaje.length; x++) {					// interfaz de fin del juego
					if(personaje[x].getPosesion() == null) {
							JLabel jLabel3= new JLabel("El personaje: "+personaje[0].getNombre()+" se encuentra en: "+personaje[0].getActual().getNombre()+" y posee: "+personaje[0].getPosesion());
			                JLabel jLabel4= new JLabel("El personaje: "+personaje[1].getNombre()+" se encuentra en: "+personaje[1].getActual().getNombre()+" y posee: "+personaje[1].getPosesion());
			                JLabel jLabel5= new JLabel("El personaje: "+personaje[2].getNombre()+" se encuentra en: "+personaje[2].getActual().getNombre()+" y posee: "+personaje[2].getPosesion());
							jLabel3.setBounds(405, 20, 900, 40);
							jLabel3.setFont(new Font("Comic Sans",Font.BOLD,20));
							jLabel3.setBounds(20, 158+68, 900, 35);
							jLabel4.setBounds(405, 20, 900, 40);
							jLabel4.setFont(new Font("Comic Sans",Font.BOLD,20));
							jLabel4.setBounds(20, 158+68+68, 900, 35);
							jLabel5.setBounds(405, 20, 900, 40);
							jLabel5.setFont(new Font("Comic Sans",Font.BOLD,20));
							jLabel5.setBounds(20, 158+68+68+68, 900, 35);
			    			jFrame2.add(jLabel2);
							jFrame2.add(jLabel3);
							jFrame2.add(jLabel4);
							jFrame2.add(jLabel5);
			                
					} 
				}
		
				if(jugador.getPosesion()==null) {
					jugador.setPosesion(o5);
				}
		
				JLabel jLabel6= new JLabel("Usted se encuentra en: "+jugador.getActual().getNombre()+" y posee: "+jugador.getPosesion().getNombre());
				if(jugador.getActual().getNombre().equals("Dormitorio") && jugador.getPosesion().getNombre().equals("Ordenador")) {
					jLabel6.setText("Usted se encuentra en: "+jugador.getActual().getNombre()+", posee: "+jugador.getPosesion().getNombre()+", por tanto ha cumplido sus objetivos !!!");
				}
				jLabel6.setFont(new Font("Comic Sans",Font.BOLD,20));
				jLabel6.setBounds(20, 158+68+68+68+68, 900, 35);
				jFrame2.add(jLabel6);
			} 
		} 
	} 	
}