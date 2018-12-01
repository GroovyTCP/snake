package entidades.test;

import org.junit.Assert;
import org.junit.Test;

import viborita.entidades.Cabeza;
import viborita.entidades.Punto;

public class CabezaTest {

	@Test
	public void testmMoverCabezaIzquierda () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(-1,0));
		
		Assert.assertEquals(new Punto(3,1),cabeza.getPosicion());
	}
	
	@Test
	public void testmMoverCabezaDerecha () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(1,0));
		
		Assert.assertEquals(new Punto(5,1),cabeza.getPosicion());
	}
	
	@Test
	public void testmMoverCabezaArriba () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(0,1));
		
		Assert.assertEquals(new Punto(4,2),cabeza.getPosicion());
	}
	
	@Test
	public void testmMoverCabezaAbajo () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(0,-1));
		
		Assert.assertEquals(new Punto(4,0),cabeza.getPosicion());
	}
}
