package entidades.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import viborita.entidades.Cabeza;
import viborita.entidades.Punto;

public class CabezaTest {

	@Test
	public void testmMoverCabezaIzquierda () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(-1,0));
		
		Assert.assertEquals(3, cabeza.getCabeza().getX());
		Assert.assertEquals(1, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaDerecha () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(1,0));
		
		Assert.assertEquals(5, cabeza.getCabeza().getX());
		Assert.assertEquals(1, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaArriba () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(0,1));
		
		Assert.assertEquals(4, cabeza.getCabeza().getX());
		Assert.assertEquals(2, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaAbajo () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza(new Punto(0,-1));
		
		Assert.assertEquals(4, cabeza.getCabeza().getX());
		Assert.assertEquals(0, cabeza.getCabeza().getY());
		
	}
}
