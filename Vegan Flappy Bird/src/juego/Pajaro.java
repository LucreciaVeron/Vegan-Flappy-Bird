package juego;
//import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Pajaro {
	
	//variables de instancia
	private double x;
	private double y;
	private double angulo;
	private double tamaño;
	private Image bird;
	private double ancho;
	private double alto;
	private Image pistola;
	

	//Metodos para la creacion del pajaro
	public Pajaro(double x, double y, double angulo, double tamaño) {
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.angulo=angulo;
		this.tamaño=tamaño;
		this.bird = Herramientas.cargarImagen("Imagenes/bird.png");
		this.pistola= Herramientas.cargarImagen("Imagenes/pistola.png");	
		this.ancho=20;
		this.alto=20;



	}
	
	
//-----------------otros metodos-------------
	void dibujarse (Entorno entorno) {
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			entorno.dibujarImagen(bird,this.x, this.y, this.angulo, this.tamaño);
			entorno.dibujarImagen(pistola,this.x, this.y-20,0, 0.8);
		}
		else {
			
			entorno.dibujarImagen(bird,this.x, this.y, this.angulo, this.tamaño);
			entorno.dibujarImagen(pistola,this.x, this.y-20,0, 0.8);
		}
		

	}
	
		
	
	void moverArriba () {
		this.y = y - 2;
		this.x = x + 0;
	}

	
	void caer (){
		this.y=y+2;
	
		this.x= x+0;
	}
	
//--------------Colision con los bordes de la pantalla------------
	public boolean tocaBordes() {
		if (this.y+20>=600) {
			this.y=y+0;
			return true;
		}
		if (this.y-20<=20) {
			this.y=y+0;
			return true;
		}
		else {
			return false;
		}
	}
	
	// ----------Metodo getters-------------
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAncho() {
		return ancho;
	}
	
	public double getAlto() {
		return alto;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
