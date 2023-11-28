package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bullet {
	private double x;
	private double y;
	private Image bala;

	private double altura;
	private double ancho;
	
	public Bullet (double x, double y) {
		this.x = x;
		this.y = y;
		this.bala=Herramientas.cargarImagen("Imagenes/bullet.png");
		this.altura=8;
		this.ancho=22;
	}
	
	public void moverse() {
		x+=3;
	}
	
	public void dibujarse (Entorno entorno) {
		entorno.dibujarImagen(bala, this.x, this.y, 0, 1);
	}
	

//-----metodos getters-----------	
	public double getY () {
		return y;
	}
	public double getX() {
		return x;
	}
	
	public double getAncho () {
		return ancho;
	}
	
	public double getAlto () {
		return altura;
	}
	


}
