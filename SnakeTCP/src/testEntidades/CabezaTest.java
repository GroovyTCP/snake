package testEntidades;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import entidades.Cabeza;
import entidades.Punto;

public class CabezaTest {

	@Test
	public void testmMoverCabezaIzquierda () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza("Izquierda");
		
		Assert.assertEquals(3, cabeza.getCabeza().getX());
		Assert.assertEquals(1, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaDerecha () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza("Derecha");
		
		Assert.assertEquals(5, cabeza.getCabeza().getX());
		Assert.assertEquals(1, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaArriba () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza("Arriba");
		
		Assert.assertEquals(4, cabeza.getCabeza().getX());
		Assert.assertEquals(2, cabeza.getCabeza().getY());
		
	}
	
	@Test
	public void testmMoverCabezaAbajo () {
		Cabeza cabeza = new Cabeza(new Punto(4,1));
		
		cabeza.moverCabeza("Abajo");
		
		Assert.assertEquals(4, cabeza.getCabeza().getX());
		Assert.assertEquals(0, cabeza.getCabeza().getY());
		
	}
}
