package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	// ...
	private Pajaro pajaro;
	private java.awt.Image fondo;
	private int puntaje;
	private int t;
	

	private java.awt.Image inicio;
	
	private ArrayList <Hamburguesa> hamburguesas= new ArrayList<Hamburguesa>();
	private ArrayList <Vegetal> vegetales= new ArrayList<Vegetal>();
	
	List<Vegetal> toRemoveV = new ArrayList<Vegetal>(); 		//lista que elimina los vegetales
	List<Hamburguesa> toRemoveH = new ArrayList<Hamburguesa>(); //lista elimina hamburguesas

	
	private ArrayList <Tubo> tubos = new  ArrayList<Tubo>();
	
	private Controlador c; 
	
	private boolean gameOver; 
	private boolean win;
	private boolean started;

	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TorreMágica - Grupo Veron - Reinich - Alderete - V0.01", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		
		this.fondo = Herramientas.cargarImagen("Imagenes/Flappy fondo.jpg");
		this.puntaje=0;
		this.pajaro= new Pajaro (200,300, 0, 0.3);
		this.inicio = Herramientas.cargarImagen("Imagenes/comienzo.jpg");
		this.c=new Controlador ();
		
		// Inicia el juego!
		this.entorno.iniciar();

	}


	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */

	
	public void tick()
	{		
		// Procesamiento de un instante de tiempo
		// ...
		
		empezar ();
		
		if (started) {
			t++;
			dibujarFiguras();
			Colisiones();
			Movimiento();
			c.tick();
		}
			
		}
		

//-----inicio-------------
	private void empezar () {
		if (!started) {
			this.entorno.dibujarImagen(inicio, 405, 305, 0, 1.2);
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				started=true;
			}
		}
	}
	
	
	
	private void Colisiones () {
		
//-------colision del pajaro con tubos o bordes de la pantalla---------
	
		for (Tubo tubo: tubos) {
			if (pajaro!=null && tubo!=null && tubo.loToca(pajaro) || tubo.loToca2(pajaro) || pajaro.tocaBordes()) {
				
				gameOver= true;
			}
		}
	
		
		
//---------colision del pajaro con vegetales o hamburguesas------------
		for (Vegetal vegetal:vegetales) {
			
			if (pajaro!=null && vegetal.colicionaConPajaro(pajaro)) {
				this.puntaje=this.puntaje+5;			
				toRemoveV.add(vegetal);
			}
			
			else if (pajaro!=null && vegetal.tocaElBorde()) {
				toRemoveV.add(vegetal);
			}
		}
		
		vegetales.removeAll( toRemoveV );
		
		
		
		for (Hamburguesa hamburguesa: hamburguesas) {
			if (pajaro!=null && hamburguesa.colisionaConPajaro(pajaro)) {
				this.puntaje=this.puntaje-5;
				toRemoveH.add(hamburguesa);
			}

			else if (pajaro!=null  && hamburguesa.tocaElBorde()) {
				toRemoveH.add(hamburguesa);
			}
			
//-------- colision de la bala con la hamburguesa--------			
			
				for( Bullet bullet:c.getBullets()) {
					if(hamburguesa.colisionaConLaser(bullet)) {
						this.puntaje+=3;
						vegetales.add(new Vegetal(hamburguesa.getX(),hamburguesa.getY()));
						c.removeBullet(bullet);
						toRemoveH.add(hamburguesa);
						
					}
		}
			
		}
		hamburguesas.removeAll( toRemoveH );
		
		
//------colision de la bala con los tubos y remover los tubos---------------		
		for (int i=0; i<tubos.size();i++) {
			Tubo tubo = tubos.get(i);
			if (tubo.getX()+tubo.getAncho()<0) {
				tubos.remove(tubo);
			}
			
			for (Bullet bullet:c.getBullets()) {
				if (tubo.tocaBala(bullet)) {
					c.removeBullet (bullet);
				}
				if (tubo.tocaBala2(bullet)) {
					c.removeBullet(bullet);
				}
			}
		}


			
		
		
// -----colision de la bala con el vegetal------------
	for (Vegetal vegetal: vegetales) {
		for (Bullet bullet:c.getBullets()) {
			if (vegetal.colicionaConBala(bullet)) {
				c.removeBullet(bullet);

			}
		}
	}
	
	for (Vegetal vegetal:vegetales) {
		for (int i=0; i<tubos.size();i++) {
			Tubo tubo = tubos.get(i);
			
			if (vegetal.estaDentroDelTubo(tubo) || vegetal.estaDentroDelTubo2(tubo)) {
				toRemoveV.add(vegetal);
			}
	}
}
	
	for (Hamburguesa hamburguesa: hamburguesas) {
		for (int i=0; i<tubos.size();i++) {
			Tubo tubo = tubos.get(i);
			
			if (hamburguesa.estaDentroDelTubo(tubo) || hamburguesa.estaDentroDelTubo2(tubo)) {
				toRemoveH.add(hamburguesa);
			}
	}
	}
		
		
	
	}
	
		


	//-----a�ade vegetales, hamburguesas y tubos al arraylist--------
	private void addVegetales() {
		Vegetal vegetal = new Vegetal();
		vegetales.add(vegetal);
		
	}
	
	
	private void addHamburguesas() {
		Hamburguesa hamburguesa= new Hamburguesa ();
		hamburguesas.add(hamburguesa);

	}
	
	private void addTubos () {
		Tubo tubo = new Tubo ();
		tubos.add(tubo);
	}
	
	

	
	private void Movimiento() {
		
//-------movimiento del pajaro------------------		
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			pajaro.moverArriba();
		}
		else {
			pajaro.caer();
		}
		
//-------a�ade una nueva bala en donde se encuentra el pajaro-------------		
		if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
			c.addBullet(new Bullet (pajaro.getX()+5, pajaro.getY()-20 ));
		}

//----------movimiento de los alimentos-------------		
		for (Vegetal vegetal:vegetales) {
			vegetal.movimiento();
		}
		
		for (Hamburguesa hamburguesa: hamburguesas) {
			hamburguesa.movimiento();
		}
		

//--------------movimiento de los tubos----------		
		for (Tubo tubo: tubos) {
			tubo.mover();
		}
			

	}

	private void dibujarFiguras() {
		

		
//-------------*dibujar pajaro, fondo y puntaje*----------------------
		
		
			
			
			this.entorno.dibujarImagen(fondo, 405, 305, 0, 1.2);
			entorno.cambiarFont("Helvetica", 18, Color.white);
			this.entorno.escribirTexto("Puntaje:"+puntaje, 30, 40);
			
//-----dibuja las balas-------
			c.dibujarse(entorno);
			
//------- dibuja pajaro-------------
			
			pajaro.dibujarse(this.entorno);
			
//-----dibuja tubos, hamburguesas y vegetales------			
			for (Vegetal vegetal:vegetales) {
				vegetal.dibujarse(entorno);
			}
			for (Hamburguesa hamburguesa: hamburguesas) {
				hamburguesa.dibujarse(entorno);
			}
			for (Tubo tubo: tubos) {
				tubo.dibujarseTubos(entorno);

			}

//------a�ade tubos, vegetales y hamburguesas por cada 5 segundos--------------			
			t++;
			if (t%500==0) {
				addVegetales();
				addHamburguesas();
				addTubos();
			}
		
		
//------cuando el pajaro toque algun borde de la pantalla o tubo se va a mostrar que perdio y su puntaje final-------
		if (gameOver) {
			
			c.clear();
			tubos.clear();
			vegetales.clear();
			hamburguesas.clear();
			
			entorno.dibujarRectangulo(400, 300, 900, 700, 0, Color.black);

			entorno.cambiarFont("Arial", 30, Color.white);
			entorno.escribirTexto("GAME OVER", 400, 300);
			entorno.cambiarFont("Arial", 20, Color.white);
			entorno.escribirTexto("apreta espacio enter para volver a intentarlo", 200, 350);
			entorno.escribirTexto("Tu Puntaje Fue: "+this.puntaje, 400, 450);
			
			
//-----------para volver a jugar----------------
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				gameOver=false;
				this.pajaro= new Pajaro (200,300, 0, 0.3);
				this.puntaje=0;
				
				
				
			}
		}
		
		
//-----------------cuando se gana el juego-----------------		
		if (this.puntaje >= 100) {
			this.win=true;
		}
		if (win) {
			c.clear();
			tubos.clear();
			vegetales.clear();
			hamburguesas.clear();
			
			entorno.dibujarRectangulo(400, 300, 900, 700, 0, Color.black);
			entorno.cambiarFont("Arial", 30, Color.white);
			entorno.escribirTexto("Ganaste", 400, 300);
			entorno.cambiarFont("Arial", 20, Color.white);
			entorno.escribirTexto("apreta enter para volver a jugar", 200, 350);
			entorno.escribirTexto("Tu Puntaje Fue: "+this.puntaje, 400, 450);
			
//---------para volver a jugar------------			
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				win=false;
				this.pajaro= new Pajaro (200,300, 0, 0.3);
				this.puntaje=0;
				
				
				
			}
		}
		
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
