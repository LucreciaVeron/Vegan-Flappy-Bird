package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Tubo {
	
	private double x;
	private double y1;
	private double y2;
	private double ancho;
	private double alto;
	private Image tubo;
	private Image tubo2;
	private int distancia;

	public Tubo () {
		
		
		Random aleatorio = new Random();
		this.ancho=150;
		this.alto=490;
		this.x= 865;
		
		
		Random distancia_aleatoria= new Random();
		this.distancia= 70 + distancia_aleatoria.nextInt(10);
		
		this.y1= 400 + aleatorio.nextInt(100) + (this.alto/2); //genera un y aleatorio para el tubo de abajo
		
		this.y2= aleatorio.nextInt(230)-this.distancia; // genera un y aleatorio para el tubo de arriba
		
		this.tubo=Herramientas.cargarImagen("Imagenes/flappy tube1.png");
		this.tubo2=Herramientas.cargarImagen("Imagenes/flappy tube2.png");
		
		
	}
	
	
	
	void dibujarseTubos(Entorno entorno) {
			entorno.dibujarImagen(this.tubo, this.x, this.y1, 0,4);
			entorno.dibujarImagen(this.tubo2, this.x, this.y2, 0,4);
	}

	
	void mover () {
		this.x=x-1;

	}
	
	
	 public boolean loToca(Pajaro pajaro) {
		 return ( (pajaro.getX() -pajaro.getAncho()/2) >= this.x - this.ancho/2) &&
				( (pajaro.getX() +pajaro.getAncho()/2) <= this.x + this.ancho/2) && 
				( (pajaro.getY() -pajaro.getAlto()/2) >= this.y1 - this.alto/2) &&
				( (pajaro.getY() +pajaro.getAlto()-50/2) <= this.y1 + this.alto/2); 
		 }
	 
	 public boolean loToca2(Pajaro pajaro) {
		 return ( (pajaro.getX() -pajaro.getAncho()/2) >= this.x - this.ancho/2) &&
				( (pajaro.getX() +pajaro.getAncho()/2) <= this.x + this.ancho/2) && 
				( (pajaro.getY() -pajaro.getAlto()/2) >= this.y2 - this.alto/2) &&
				( (pajaro.getY() +pajaro.getAlto()-50/2) <= this.y2 + this.alto/2); 
		 }
	 
	 public boolean tocaBala(Bullet b) {
		 return ( (b.getX() -b.getAncho()/2) >= this.x - this.ancho/2) &&
				( (b.getX() +b.getAncho()/2) <= this.x + this.ancho/2) && 
				( (b.getY() -b.getAlto()/2) >= this.y2 - this.alto/2) &&
				( (b.getY() +b.getAlto()/2) <= this.y2 + this.alto/2) ; 
		 }
	 
	 public boolean tocaBala2(Bullet b) {
		 return ( (b.getX() -b.getAncho()/2) >= this.x - this.ancho/2) &&
				( (b.getX() +b.getAncho()/2) <= this.x + this.ancho/2) && 
				( (b.getY() -b.getAlto()/2) >= this.y2 - this.alto/2) &&
				( (b.getY() +b.getAlto()/2) <= this.y2 + this.alto/2); 
		 }
	 
	 
	
	//metodo getters
	
	public double getX() {
		return x;
	}
	
	public double getY1() {
		return y1;
	}
	
	public double getY2() {
		return y2;
	}
	
	public double getAncho () {
		return ancho;
	}
	
	public double getAlto() {
		return alto;
	}
	


}
