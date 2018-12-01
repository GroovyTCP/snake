package entidades.test;
import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import viborita.entidades.Cabeza;
import viborita.entidades.Mapa;
import viborita.entidades.Punto;
import viborita.entidades.Vibora;


public class ChoqueTest {
	
	@Test
	public void choqueDosViboras () {
		
		///Dos viboras que vienen enfrentadas v1 se mueve a la derecha y v2 a la izquierda, estan a un bloque de distancia
		Vibora v1 = new Vibora(new Cabeza(new Punto(1,2)),Color.RED,new Punto(1,0));
		Vibora v2 = new Vibora(new Cabeza(new Punto(3,2)),Color.BLUE,new Punto(-1,0));
		
		Vibora[] viboritas = new Vibora[2];
		viboritas[0] = v1;
		viboritas[1] = v2;
		
		Mapa mapa = new Mapa(viboritas,600,500,100);
		
		///Antes vivas
		Assert.assertFalse(v1.isMuerta()); ///v1 viva
		Assert.assertFalse(v2.isMuerta()); ///v2 viva	
		
		//Las viboras se mueven y se encuentran x lo que mueren
		mapa.evaluarMovimientoViborita();
			
		///Ahora mueren ambas
		Assert.assertTrue(v1.isMuerta()); ///v1 muere
		Assert.assertTrue(v2.isMuerta()); ///v2 muere
	}
	
	@Test
	public void choqueBorde () {
		
		Vibora v1 = new Vibora(new Cabeza(new Punto(0,2)),Color.RED,new Punto(-1,0));
		
		Vibora[] viboritas = new Vibora[1];
		viboritas[0] = v1;
		
		Mapa mapa = new Mapa(viboritas,600,500,100);
		
		///Antes esta viva
		Assert.assertFalse(v1.isMuerta()); ///v1 viva
		
		//La vibora se mueve hacia la izquierda y choca contra el borde
		mapa.evaluarMovimientoViborita();
		
		///Por lo que muere
		Assert.assertTrue(v1.isMuerta()); ///v1 muere
	}
	
}
