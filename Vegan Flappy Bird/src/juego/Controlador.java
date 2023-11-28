package juego;

import java.util.ArrayList;
import java.util.LinkedList;

import entorno.Entorno;

public class Controlador {
	
	private LinkedList<Bullet> bullets = new LinkedList <Bullet>();
	Bullet bullet1;
	private ArrayList <Tubo> tubos= new ArrayList <Tubo>();
	
	public void tick () {
		for (int i=0; i<bullets.size();i++) {
			bullet1=bullets.get(i);
			
			if (bullet1.getX()>800 ) {
				removeBullet (bullet1);      //elimina las balas que toquen el final de la pantalla
			}
			colicion ();

			bullet1.moverse();
		}
	}
	
	public void dibujarse (Entorno entorno) {
		for (int i=0; i<bullets.size();i++) {
			bullet1=bullets.get(i);
			
			bullet1.dibujarse(entorno);
		}
	}
	
	public void colicion () {
		for (Tubo tubo: tubos) {
			for (int i=0; i<bullets.size();i++) {
				bullet1=bullets.get(i);
				
				if (tubo.tocaBala(bullet1) || tubo.tocaBala2(bullet1)) {	//cuando toca un tubo se elimina
					removeBullet(bullet1);
				}
			}
		}
	}
	
	public LinkedList<Bullet> getBullets(){
		LinkedList<Bullet> aBullets=new LinkedList<Bullet>();
		if(bullets.size()!=0) {
			for(int i=0;i<bullets.indexOf(bullets.getLast());i++) {		//devuelve cada una de las balas
				aBullets.add(bullets.get(i));
			}
		}
		return aBullets;
	}
	
	public void addBullet (Bullet b) {
		bullets.add(b);
	}
	
	public void removeBullet (Bullet b) {
		bullets.remove(b);
	}

	public void clear() {
		
		bullets.clear();

	}

}
