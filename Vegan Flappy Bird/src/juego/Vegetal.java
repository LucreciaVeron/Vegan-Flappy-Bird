package juego;


import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Vegetal {
	private double x;
	private double y;
	private double diametro;
	private Image vegetal;
	Random xR = new Random();
	
	
	public Vegetal () {
		

		this.x=850+400 + xR.nextInt(100);
		this.y=200+ (int) (Math.random()*300);
		this.diametro=80;
		this.vegetal=Herramientas.cargarImagen("Imagenes/Vegetal.png");
	
	}
	
	
	//CONSTRUCTOR ESPECIFICO
	public Vegetal(double x, double y) {
		this.x=x;
		this.y=y;
		this.diametro=80;
		this.vegetal=Herramientas.cargarImagen("Imagenes/Vegetal.png");
	}
	
	void dibujarse (Entorno entorno) {
		entorno.dibujarImagen(vegetal, this.x, this.y, this.diametro);
		
	}
	public boolean colicionaConPajaro(Pajaro pajaro)
	{
	    double xx = this.x - pajaro.getX();
	    double yy = this.y - pajaro.getY();
	    double distancia = Math.sqrt(xx*xx + yy*yy);
	    return distancia < this.diametro/2;
	}
	
	public boolean colicionaConBala(Bullet bullet)
	{
	    double xx = this.x - bullet.getX();
	    double yy = this.y - bullet.getY();
	    double distancia = Math.sqrt(xx*xx + yy*yy);
	    return distancia < this.diametro/2;
	}
	
	public boolean estaDentroDelTubo (Tubo tubo) {
		double xx = this.x - tubo.getX();
	    double yy = this.y - tubo.getY1();
	    double distancia = Math.sqrt(xx*xx + yy*yy);
	    return distancia < this.diametro/2;
		
	}
	
	public boolean estaDentroDelTubo2 (Tubo tubo) {
		double xx = this.x - tubo.getX();
	    double yy = this.y - tubo.getY2();
	    double distancia = Math.sqrt(xx*xx + yy*yy);
	    return distancia < this.diametro/2;
		
	}
	
	public boolean tocaElBorde() {
		if (this.x<-50) {
			return true;
		}
		return false;
	}
	
	void movimiento() {
		this.y=y+0;
		this.x=x-1;
	}

	


}
